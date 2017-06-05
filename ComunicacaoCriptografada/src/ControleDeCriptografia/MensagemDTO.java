package ControleDeCriptografia;

import java.security.PrivateKey;

import com.google.gson.Gson;

/**
 * @author Abraao, Aellison, Jose, Pedro
 *
 */
public class MensagemDTO {
	private String mensagem;
	private byte[] chavePublica;

	/**
	 * @param mensagem
	 *            Mensagem contendo dados a serem criptografados
	 * @param chavePublica
	 *            Chave publica para envio ao endpoint de comunicacao
	 */
	public MensagemDTO(String mensagem, PrivateKey chavePublica) {
		this.mensagem = mensagem;
		this.chavePublica = chavePublica.getEncoded();
	}

	/**
	 * @return Retorna a mensagem do MensagemDTO.
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem
	 *            Define a mensagem do MensagemDTO.
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * @return Retorna a chave publica do MensagemDTO em um array de bytes.
	 */
	public byte[] getChavePublica() {
		return chavePublica;
	}

	/**
	 * @param chavePublica
	 *            Define o valor da chave publica do MensagemDTO em um array de
	 *            bytes.
	 */
	public void setChavePublica(byte[] chavePublica) {
		this.chavePublica = chavePublica;
	}

	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
