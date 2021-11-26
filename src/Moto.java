
public class Moto extends Veiculo{
	
	private boolean isElectric;

	public Moto(String modelo, double valor, String desc, String placa, boolean isElectric) {
		super(modelo, valor, desc, placa);
		this.setElectric(isElectric);
	}

	public boolean isElectric() {
		return isElectric;
	}

	public void setElectric(boolean isElectric) {
		this.isElectric = isElectric;
	}
	
}
