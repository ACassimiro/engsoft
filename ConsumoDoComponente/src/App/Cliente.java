package App;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Date;

import javax.swing.JOptionPane;

import ControleDeCriptografia.Criptografavel;
import ControleDeCriptografia.NucleoDeCriptografia;

public class Cliente extends Criptografavel {

	public Cliente() throws NoSuchAlgorithmException, UnknownHostException, IOException {
		super.gerarChaves();
	}

}
