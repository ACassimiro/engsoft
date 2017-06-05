package ControleDeCriptografia;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @author Abraao, Aellison, Jose, Pedro
 *
 *         Entidades de fronteiras de comunicacao devem extender essa classe,
 *         para gerarem chaves criptograficas.
 *
 *         *As chaves geradas e utilizadas nessa implementacao sao geradas com o
 *         algorimo RSA e possuem 1024 bits de comprimento.
 *
 */
public abstract class Criptografavel {
	private PrivateKey chavePublica;
	private PublicKey chavePrivada;
	private PrivateKey chavePublicaExterna;

	protected void gerarChaves() throws NoSuchAlgorithmException {
		KeyPairGenerator geradorDeChave;
		KeyPair parDeChaves;

		geradorDeChave = KeyPairGenerator.getInstance("RSA");
		geradorDeChave.initialize(1024);
		parDeChaves = geradorDeChave.generateKeyPair();
		this.chavePublica = parDeChaves.getPrivate();
		this.chavePrivada = parDeChaves.getPublic();

	}

	/**
	 * @return Retorna a chave publica do objeto criptografavel.
	 *
	 *         *As chaves geradas e utilizadas nessa implementacao sao geradas
	 *         com o algorimo RSA e possuem 1024 bits de comprimento.
	 */
	public PrivateKey getChavePublica() {
		return chavePublica;
	}

	/**
	 * @param chavePublica
	 *            Chave publica do objeto criptografavel.
	 *
	 *            *As chaves geradas e utilizadas nessa implementacao sao
	 *            geradas com o algorimo RSA e possuem 1024 bits de comprimento.
	 */
	public void setChavePublica(PrivateKey chavePublica) {
		this.chavePublica = chavePublica;
	}

	/**
	 * @return Retorna a chave privada do objeto criptografavel.
	 *
	 *         *As chaves geradas e utilizadas nessa implementacao sao geradas
	 *         com o algorimo RSA e possuem 1024 bits de comprimento.
	 */
	public PublicKey getChavePrivada() {
		return chavePrivada;
	}

	/**
	 * @param chavePrivada
	 *            Chave privada do objeto criptografavel.
	 *
	 *            *As chaves geradas e utilizadas nessa implementacao sao
	 *            geradas com o algorimo RSA e possuem 1024 bits de comprimento.
	 */
	public void setChavePrivada(PublicKey chavePrivada) {
		this.chavePrivada = chavePrivada;
	}

	/**
	 * @return Retorna a chave publica externa do objeto criptografavel. Obs.: A
	 *         chave publica externa refere-se a chave de crioptografia do
	 *         endpoint de comunicacao.
	 *
	 *         *As chaves geradas e utilizadas nessa implementacao sao geradas
	 *         com o algorimo RSA e possuem 1024 bits de comprimento.
	 */
	public PrivateKey getChavePublicaExterna() {
		return chavePublicaExterna;
	}

	/**
	 * @param chavePublicaExterna
	 *            Chave de criptografia publ�ica do endpoint de comunicacao.
	 *
	 *            *As chaves geradas e utilizadas nessa implementacao sao
	 *            geradas com o algorimo RSA e possuem 1024 bits de comprimento.
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public void setChavePublicaExterna(byte[] chavePublicaExterna) throws NoSuchAlgorithmException, InvalidKeySpecException {

		PKCS8EncodedKeySpec reestrutuDaChave = new PKCS8EncodedKeySpec(chavePublicaExterna);
		KeyFactory keyfactory = KeyFactory.getInstance("RSA");
		this.chavePublicaExterna = keyfactory.generatePrivate(reestrutuDaChave);

	}

}
