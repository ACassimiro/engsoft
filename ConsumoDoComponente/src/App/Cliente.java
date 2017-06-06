package App;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Date;

import javax.swing.JOptionPane;

import ControleDeCriptografia.Criptografavel;
import ControleDeCriptografia.NucleoDeCriptografia;

public class Cliente extends Criptografavel {

	Socket socket;

	public Cliente(String ip, int porta) throws NoSuchAlgorithmException, UnknownHostException, IOException {
		super.gerarChaves();
		this.socket = new Socket(ip, porta);
	}

	public static void main(String[] args) {
		int porta = 8000;
		String ip = "localhost";
		NucleoDeCriptografia cripto = NucleoDeCriptografia.getInstancia();

		try {
			
			Cliente cliente = new Cliente(ip, porta);
			ObjectInputStream entrada = new ObjectInputStream(cliente.socket.getInputStream());
			ObjectOutputStream saida = new ObjectOutputStream(cliente.socket.getOutputStream());
			String mensagem = "Delação da lava jato.";
			byte[] mensagemEncriptada = cripto.encriptarMensagem(mensagem, cliente);

			saida.flush();
			saida.writeObject(mensagemEncriptada);
			
			byte[] retornoEncriptado = (byte[])entrada.readObject();

			String retorno = cripto.decriptarMensagem(retornoEncriptado, cliente);
			
			System.out.println(mensagem);
			System.out.println(retorno);
			
			entrada.close();
			saida.close();
			System.out.println("Conexão encerrada");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

}
