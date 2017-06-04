package ControleDeCriptografia;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;

public abstract class Criptografavel {
	private PrivateKey chavePublica;
	private PublicKey chavePrivada;
	private PrivateKey chavePublicaExterna;

	protected void gerarChaves() {
		KeyPairGenerator geradorDeChave;
		KeyPair parDeChaves;
		try {
			geradorDeChave = KeyPairGenerator.getInstance("RSA");
			geradorDeChave.initialize(1024);
			parDeChaves = geradorDeChave.generateKeyPair();
			this.chavePublica = parDeChaves.getPrivate();
			this.chavePrivada = parDeChaves.getPublic();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public PrivateKey getChavePublica() {
		return chavePublica;
	}

	public void setChavePublica(PrivateKey chavePublica) {
		this.chavePublica = chavePublica;
	}

	public PublicKey getChavePrivada() {
		return chavePrivada;
	}

	public void setChavePrivada(PublicKey chavePrivada) {
		this.chavePrivada = chavePrivada;
	}

	public PrivateKey getChavePublicaExterna() {
		return chavePublicaExterna;
	}

	public void setChavePublicaExterna(byte[] chavePublicaExterna) {
		try {
			PKCS8EncodedKeySpec reestrutuDaChave = new PKCS8EncodedKeySpec(chavePublicaExterna);
			KeyFactory keyfactory = KeyFactory.getInstance("RSA");
			this.chavePublicaExterna = keyfactory.generatePrivate(reestrutuDaChave);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
