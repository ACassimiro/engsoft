package ControleDeCriptografia;

import java.security.PrivateKey;

import com.google.gson.Gson;

public abstract class FronteiraCriptografada {
	
	public final String gerarMensagemParaEnvio(String mensagem, PrivateKey chavePublica){
		Gson gson = new Gson();
		MensagemDTO mensagemSaida= new MensagemDTO(mensagem, chavePublica);
		
		return gson.toJson(mensagemSaida);		
	}
	
	
}
