package App;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Date;

import ControleDeCriptografia.Criptografavel;
import ControleDeCriptografia.NucleoDeCriptografia;

public class Servidor extends Criptografavel{

	ServerSocket serversocket;
	
	public Servidor(int porta) throws NoSuchAlgorithmException, IOException {
		super.gerarChaves();
		this.serversocket = new ServerSocket(porta);
		System.out.println("Servidor ouvindo a porta " + porta);
	}

	
	public static void main(String args[]) {
		try {
			NucleoDeCriptografia cripto = NucleoDeCriptografia.getInstancia();
			Servidor servidor = new Servidor(8000);
			
			while (true) {

				Socket cliente = servidor.serversocket.accept();
				System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
				ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
				ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
				
				byte[] entradaEncriptada = (byte[])entrada.readObject();
				
				String mensagem = cripto.decriptarMensagem(entradaEncriptada, servidor);
				String retorno = "O Temer ainda é presidente!";
				
				if(mensagem.equals("Delação da lava jato.")){
					retorno = "O Temer foi preso, mas o Aécio não tem jeito de ser.";
				}
				
				saida.flush();
				saida.writeObject(cripto.encriptarMensagem(retorno, servidor));
				saida.close();
				cliente.close();
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}



}
