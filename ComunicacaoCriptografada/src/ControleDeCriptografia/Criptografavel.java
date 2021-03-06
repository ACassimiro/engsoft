package ControleDeCriptografia;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author Abraao, Aellison, Jose, Pedro
 * @since 0.1
 * @version 0.2
 *
 *          <p>
 *          Entidades de fronteiras de comunicacao devem extender essa classe,
 *          para gerarem chaves criptograficas.
 *
 *
 */
public abstract class Criptografavel {
	private PrivateKey chavePrivada;
	private PublicKey chavePublica;
	private PublicKey chavePublicaExterna;

	/**
	 * @version 0.2
	 * @since 0.1
	 */
	protected void gerarChaves() throws NoSuchAlgorithmException {
		KeyPairGenerator geradorDeChave;
		KeyPair parDeChaves;

		geradorDeChave = KeyPairGenerator.getInstance("RSA");
		geradorDeChave.initialize(1024);
		parDeChaves = geradorDeChave.generateKeyPair();
		this.chavePrivada = parDeChaves.getPrivate();
		this.chavePublica = parDeChaves.getPublic();

	}

	/**
	 * @version 0.2
	 * @since 0.1
	 * @return Retorna a chave publica do objeto criptografavel.
	 *
	 *         *As chaves geradas e utilizadas nessa implementacao sao geradas
	 *         com o algorimo RSA e possuem 1024 bits de comprimento.
	 */
	public PublicKey getChavePublica() {
		return chavePublica;
	}

	/**
	 * @version 0.2
	 * @since 0.1
	 * @param chavePublica
	 *            Chave publica do objeto criptografavel.
	 *
	 *            *As chaves geradas e utilizadas nessa implementacao sao
	 *            geradas com o algorimo RSA e possuem 1024 bits de comprimento.
	 */
	public void setChavePublica(PublicKey chavePublica) {
		this.chavePublica = chavePublica;
	}

	/**
	 * @version 0.2
	 * @since 0.1
	 * @return Retorna a chave privada do objeto criptografavel.
	 *
	 *         *As chaves geradas e utilizadas nessa implementacao sao geradas
	 *         com o algorimo RSA e possuem 1024 bits de comprimento.
	 */
	public PrivateKey getChavePrivada() {
		return chavePrivada;
	}

	/**
	 * @version 0.2
	 * @since 0.1
	 * @param chavePrivada
	 *            Chave privada do objeto criptografavel.
	 *
	 *            *As chaves geradas e utilizadas nessa implementacao sao
	 *            geradas com o algorimo RSA e possuem 1024 bits de comprimento.
	 */
	public void setChavePrivada(PrivateKey chavePrivada) {
		this.chavePrivada = chavePrivada;
	}

	/**
	 * @version 0.2
	 * @since 0.1
	 * @return Retorna a chave publica externa do objeto criptografavel. Obs.: A
	 *         chave publica externa refere-se a chave de crioptografia do
	 *         endpoint de comunicacao.
	 *
	 *         *As chaves geradas e utilizadas nessa implementacao sao geradas
	 *         com o algorimo RSA e possuem 1024 bits de comprimento.
	 */
	public PublicKey getChavePublicaExterna() {
		return chavePublicaExterna;
	}

	/**
	 * @version 0.2
	 * @since 0.1
	 * @param chavePublicaExterna
	 *            Chave de criptografia publ�ica do endpoint de comunicacao.
	 *
	 *            *As chaves geradas e utilizadas nessa implementacao sao
	 *            geradas com o algorimo RSA e possuem 1024 bits de comprimento.
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public void setChavePublicaExterna(byte[] chavePublicaExterna)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		X509EncodedKeySpec reestrutuDaChave = new X509EncodedKeySpec(chavePublicaExterna);
		KeyFactory keyfactory = KeyFactory.getInstance("RSA");
		this.chavePublicaExterna = keyfactory.generatePublic(reestrutuDaChave);

	}

}
