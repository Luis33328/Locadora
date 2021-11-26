
public class Veiculo {

	private double valorL;
	private String modelo;
	private String desc;
	private String placa;
	private boolean isLocado = false;
	
	public Veiculo(String modelo, double valor, String desc, String placa) {
		this.modelo = modelo;
		this.valorL = valor;
		this.desc = desc;
		this.placa = placa;
	}
	
	
	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public boolean isLocado() {
		return isLocado;
	}
	public void setLocado(boolean isLocado) {
		this.isLocado = isLocado;
	}
	public double getValorL() {
		return valorL;
	}
	public void setValorL(double valorL) {
		this.valorL = valorL;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	
}
