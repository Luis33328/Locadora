
public class Carro extends Veiculo {

	private int pass;
	
	
	public Carro(String modelo, double valor, String desc, String placa, int pass) {
		super(modelo, valor, desc, placa);
		this.setPass(pass);
	}


	public int getPass() {
		return pass;
	}


	public void setPass(int pass) {
		this.pass = pass;
	}
	
}
