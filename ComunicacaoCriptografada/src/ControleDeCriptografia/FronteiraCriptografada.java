package ControleDeCriptografia;

import java.security.PrivateKey;
import java.security.PublicKey;

import com.google.gson.Gson;

/**
 * @version 0.2
 * @since 0.1
 * @author Abraao, Aellison, Jose, Pedro
 *
 */
public abstract class FronteiraCriptografada {

	/**
	 * @version 0.2
	 * @since 0.1
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
	public final String gerarMensagemParaEnvio(String mensagem, PublicKey chavePublica) {
		Gson gson = new Gson();
		MensagemDTO mensagemSaida = new MensagemDTO(mensagem, chavePublica);

		return gson.toJson(mensagemSaida);
	}

}
