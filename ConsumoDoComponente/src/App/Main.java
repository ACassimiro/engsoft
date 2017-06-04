package App;

import java.util.ArrayList;

import com.google.gson.Gson;

import ControleDeCriptografia.NucleoDeCriptografia;


public class Main {

	public static void main(String[] args) {
		Gson g = new Gson();

		Server conductor = new Server();
		Cliente cliente = new Cliente();
		NucleoDeCriptografia cripto = NucleoDeCriptografia.getInstancia();

		// Montando o DTO de envio de mensagem com a chave da conductor como
		// parametro
		MensagemDTO m = new MensagemDTO("DadoCampo1", "DadoCampo2", conductor.getChavePublica().getEncoded());

		System.out.println("Chave Conductor: " + new String(m.getChavePublica()));
		String Mensagem = g.toJson(m);

		// Json de saida
		System.out.println("DTO de Saida da CDT: " + Mensagem + "\n*\n*\n*");

		// criptografa a mensagem em partes de tamanho 100 pra não estourar o
		// limite de 117 da chave
		ArrayList<String> ListaDeSaida = cripto.encriptarMensagem(Mensagem, cliente.getChavePublica());
		Mensagem = g.toJson(ListaDeSaida);

		// Mensagem enviada
		System.out.println("Mensagem de saida da CDT: " + Mensagem + "\n*\n*\n*");

		// ------enviando mensagem via web---------//

		System.out.println("Mensagem recebida no cliente:" + Mensagem + "\n*\n*\n*");

		ArrayList<String> ListaEntrada = g.fromJson(Mensagem, ArrayList.class);

		// mensagem recebida pelo cliente, que usa a chave privada dele para
		// abrir a mensagem
		Mensagem = cripto.decriptarMensagem(ListaEntrada, cliente.getChavePrivada());

		System.out.println("DTO recebido no cliente:" + Mensagem + "\n*\n*\n*");

		MensagemDTO m2 = g.fromJson(Mensagem, MensagemDTO.class);

		String a = new String(m.getChavePublica());
		String b = new String(m2.getChavePublica());
		cliente.setChavePublicaExterna(m2.getChavePublica());

		if (a.equals(b))
			System.out.println("Chave Correta!!!\n\n");
		System.out.println("Chave Conductor Recebida: " + new String(m2.getChavePublica())+ "\n\n-------");

		// FLUXO DE RETORNO PARA A CONDCUCTOR USANDO A CHAVE RECEBIDA

		MensagemDTO m3 = new MensagemDTO("RETORNO CAMPO 1", "RETORNO CAMPO 2", cliente.getChavePublica().getEncoded());
		String MensagemFinal = g.toJson(m3);

		// Json de saida
		System.out.println("DTO de Saida do Cliente: " + MensagemFinal + "\n*\n*\n*");

		// criptografa a mensagem em partes de tamanho 100 pra não estourar o
		// limite de 117 da chave
		ArrayList<String> ListaDeSaida2 = cripto.encriptarMensagem(MensagemFinal, cliente.getChavePublicaExterna());
		MensagemFinal = g.toJson(ListaDeSaida2);

		// Mensagem enviada
		System.out.println("Mensagem de saida do Cliente: " + MensagemFinal + "\n*\n*\n*");

		// ------enviando mensagem via web---------//
		
		ArrayList<String> ListaEntrada2 = g.fromJson(MensagemFinal, ArrayList.class);
		MensagemFinal = cripto.decriptarMensagem(ListaEntrada2, conductor.getChavePrivada());
		System.out.println("DTO recebido no cliente:" + MensagemFinal + "\n*\n*\n*");
	}

}
