package ControleDeCriptografia;

import java.security.PrivateKey;

import com.google.gson.Gson;

/**
 * @author Abraao, Aellison, Jose, Pedro
 *
 */
public abstract class FronteiraCriptografada {

	/**
	 * @param mensagem
	 *            Mensagem para envio ao endpoint de comunicacao.
	 * @param chavePublica
	 *            Chave publica do objeto criptografavel atual, para ser enviada
	 *            em endpoint de comunicacao.
	 * @return Retorna o json de um Data Transfer Object (DTO) contendo a
	 *         mensagem e a chave publica.
	 *
	 *         *As chaves geradas e utilizadas nessa implementacao sao geradas
	 *         com o algorimo RSA e possuem 1024 bits de comprimento.
	 */
	public final String gerarMensagemParaEnvio(String mensagem, PrivateKey chavePublica) {
		Gson gson = new Gson();
		MensagemDTO mensagemSaida = new MensagemDTO(mensagem, chavePublica);

		return gson.toJson(mensagemSaida);
	}

}
