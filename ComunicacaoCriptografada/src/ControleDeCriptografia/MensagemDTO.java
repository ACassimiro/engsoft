package ControleDeCriptografia;

import java.security.PrivateKey;

public class MensagemDTO {
	String mensagem;
	byte[] chavePublica;
	
	public MensagemDTO(String mensagem, PrivateKey chavePublica){
		this.mensagem = mensagem;
		this.chavePublica = chavePublica.getEncoded();
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public byte[] getChavePublica() {
		return chavePublica;
	}

	public void setChavePublica(byte[] chavePublica) {
		this.chavePublica = chavePublica;
	}

}
