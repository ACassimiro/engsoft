package App;

public class MensagemDTO {
	String Campo1;
	String Campo2;
	byte[] chavePublica;

	public MensagemDTO(String c1, String c2, byte[]chavePublica){
		this.Campo1 = c1;
		this.Campo2 = c2;
		this.chavePublica = chavePublica;
	}
	
	public String getCampo1() {
		return Campo1;
	}

	public void setCampo1(String campo1) {
		Campo1 = campo1;
	}

	public String getCampo2() {
		return Campo2;
	}

	public void setCampo2(String campo2) {
		Campo2 = campo2;
	}

	public byte[] getChavePublica() {
		return chavePublica;
	}

	public void setChavePublica(byte[] chavePublica) {
		this.chavePublica = chavePublica;
	}
}
