package App;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import ControleDeCriptografia.Criptografavel;

public class Servidor extends Criptografavel {

	public Servidor() throws NoSuchAlgorithmException {
		super.gerarChaves();
	}

}
