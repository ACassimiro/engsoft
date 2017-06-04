package ControleDeCriptografia;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;

public class NucleoDeCriptografia {
	private static NucleoDeCriptografia instancia;
	private Cipher cipher;

	private NucleoDeCriptografia() {
		try {
			cipher = Cipher.getInstance("RSA");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static NucleoDeCriptografia getInstancia() {

		if (instancia == null)
			instancia = new NucleoDeCriptografia();

		return instancia;

	}

	public byte[] encriptarMensagem(String mensagem, Criptografavel criptografavel) {
		Gson gson = new Gson();
		MensagemDTO msgDTO = new MensagemDTO(mensagem, criptografavel.getChavePublica());
		mensagem = msgDTO.toString();
		
		ArrayList<String> retorno = new ArrayList<String>();
		String parcial;
		for (int i = 0; i < mensagem.length() - 1; i += 100) {
			parcial = mensagem.length() >= i + 100 ? mensagem.substring(i, i + 100) : mensagem.substring(i);
			try {
				this.cipher.init(Cipher.ENCRYPT_MODE, criptografavel.getChavePublicaExterna());

				retorno.add(Base64.encodeBase64String(cipher.doFinal(parcial.getBytes("UTF-8"))));

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return gson.toJson(retorno).getBytes();

	}

	public String decriptarMensagem(byte[] mensagem, Criptografavel criptografavel) {
		String retorno = "";
		Gson gson = new Gson();
		ArrayList<String> dados = gson.fromJson(new String(mensagem), ArrayList.class);

		for (String parcial : dados) {
			try {
				this.cipher.init(Cipher.DECRYPT_MODE, criptografavel.getChavePrivada());

				retorno += new String(cipher.doFinal(Base64.decodeBase64(parcial)), "UTF-8");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		MensagemDTO msgDTO = gson.fromJson(retorno, MensagemDTO.class);
		
		criptografavel.setChavePublicaExterna(msgDTO.getChavePublica());
		
		return msgDTO.getMensagem();
	}
}
