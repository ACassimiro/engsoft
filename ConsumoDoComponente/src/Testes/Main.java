package Testes;

import java.util.ArrayList;

/**
 * @author Abraao, Aellison, Jose, Pedro
 * @since 0.2
 * @version 0.2
 *
 *	Classe que realiza todos os testes
 *          <p>
 *
 *
 *
 */

import com.google.gson.Gson;

import App.Cliente;
import App.Servidor;
import ControleDeCriptografia.MensagemDTO;
import ControleDeCriptografia.NucleoDeCriptografia;

public class Main {

	public static void main(String[] args) {
		try{
		Gson g = new Gson();

		Servidor server = new Servidor();
		Cliente cliente = new Cliente();
		cliente.setChavePublicaExterna(server.getChavePublica().getEncoded());
		NucleoDeCriptografia cripto = NucleoDeCriptografia.getInstancia();

		String enviado = "{DADOS SIGILOSOS}";

		System.out.println("Enviado [Cliente -> Servidor]: " + enviado);

		// metodo de encriptar mensagem ja manda a chave publica do cliente no
		// processo
		byte[] encriptado = cripto.encriptarMensagem(enviado, cliente);

		System.out.println("Mensagem Middle [Cliente -> Servidor]: " + new String(encriptado));
		// aqui o server ja recebe a chave publica do cliente, e pode
		// criptografar mensagens pra ele
		String recebido = cripto.decriptarMensagem(encriptado, server);


		//CANAL DE COMUNICA��O ENTRE OS ENDPOINTS
		System.out.print("Enviando mensagem ");
		for(int i = 0; i<5; i++){
			Thread.sleep(1000);
			System.out.print(".");
		}
		System.out.println();
		//

		System.out.println("Recebido [Cliente -> Servidor]: " + recebido);

		String enviado2 = "{RESPOSTA SIGILOSA}";
		System.out.println("Enviado [Servidor -> Cliente]: " + enviado2);

		// metodo de encriptar mensagem ja manda a chave publica do cliente no
		// processo
		byte[] encriptado2 = cripto.encriptarMensagem(enviado2, server);

		//CANAL DE COMUNICA��O ENTRE OS ENDPOINTS
		System.out.print("Enviando resposta ");
		for(int i = 0; i<5; i++){
			Thread.sleep(1000);
			System.out.print(".");
		}
		System.out.println();
		//

		System.out.println("Mensagem Middle [Servidor -> Cliente]: " + new String(encriptado2));
		// aqui o server ja recebe a chave publica do cliente, e pode
		// criptografar mensagens pra ele
		String recebido2 = cripto.decriptarMensagem(encriptado2, cliente);

		System.out.println("Recebido [Servidor -> Cliente]: " + recebido2);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
