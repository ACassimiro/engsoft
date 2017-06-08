package testesUnitarios;

import static org.junit.Assert.*;

import java.security.KeyPairGenerator;

import org.junit.Test;

public class TesteMensagemDTO {

	@Test
	public void test() {
		KeyPairGenerator gerador ;


		try{
		gerador = KeyPairGenerator.getInstance("RSA");
		gerador.initialize(1024);

		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}

	}

}
