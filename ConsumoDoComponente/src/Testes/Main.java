//package Testes;
//
//import java.util.ArrayList;
//
//import com.google.gson.Gson;
//
//import App.Cliente;
//import App.Servidor;
//import ControleDeCriptografia.MensagemDTO;
//import ControleDeCriptografia.NucleoDeCriptografia;
//
//public class Main {
//
//	public static void main(String[] args) {
//		try{
//		Gson g = new Gson();
//
//		Servidor server = new Servidor();
//		Cliente cliente = new Cliente();
//		cliente.setChavePublicaExterna(server.getChavePublica().getEncoded());
//		NucleoDeCriptografia cripto = NucleoDeCriptografia.getInstancia();
//
//		String enviado = "Requisicao ao server...";
//
//		System.out.println("Enviado [Cliente -> Servidor]: " + enviado);
//
//		// metodo de encriptar mensagem ja manda a chave publica do cliente no
//		// processo
//		byte[] encriptado = cripto.encriptarMensagem(enviado, cliente);
//
//		System.out.println("Mensagem Middle [Cliente -> Servidor]: " + new String(encriptado));
//		// aqui o server ja recebe a chave publica do cliente, e pode
//		// criptografar mensagens pra ele
//		String recebido = cripto.decriptarMensagem(encriptado, server);
//
//		System.out.println("Recebido [Cliente -> Servidor]: " + recebido);
//
//		String enviado2 = "Resposta ao cliente...";
//		System.out.println("Enviado [Servidor -> Cliente]: " + enviado2);
//
//		// metodo de encriptar mensagem ja manda a chave publica do cliente no
//		// processo
//		byte[] encriptado2 = cripto.encriptarMensagem(enviado2, server);
//
//		System.out.println("Mensagem Middle [Servidor -> Cliente]: " + new String(encriptado2));
//		// aqui o server ja recebe a chave publica do cliente, e pode
//		// criptografar mensagens pra ele
//		String recebido2 = cripto.decriptarMensagem(encriptado2, cliente);
//
//		System.out.println("Recebido [Servidor -> Cliente]: " + recebido2);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//
//}
