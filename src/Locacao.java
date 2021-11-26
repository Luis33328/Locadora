
public class Locacao {

	private Cliente cliente;
	private int dias;
	private String data;
	private boolean isSeguro;
	private double desconto;
	
	private Veiculo vec;
	private double valor;
	
	
	public Locacao(Cliente cliente, int dias,  String data, boolean isSeguro, double desconto, Veiculo vec) {
		this.cliente = cliente;
		this.dias = dias;
		this.data = data;
		this.isSeguro = isSeguro;
		this.desconto = desconto;
		//this.valor = valor;
		this.vec = vec;
	}
	
	

	public  double calcDesc(double percent) {
		double valorFinal = vec.getValorL() * dias;
		
		if(desconto > 0) {
			valorFinal = valorFinal * (percent/100);
		}
		
		return valorFinal;
	}
	
	public  double calcseguro() {
		double seguro = 0.0;
		
		if(vec instanceof Carro) {
			seguro = ((0.05 * vec.getValorL()) * (1 + ((Carro) vec).getPass() / 20)) * dias;
		}
		else if(vec instanceof Moto) {
			seguro = (0.09 * vec.getValorL()) * dias;
		}
		
		return seguro;
	}
	
	public void print() {
		System.out.println("---Detalhes da compra---");
		System.out.println("Cliente: " + cliente.getNome());
		System.out.println("Dias de locação: " + dias);
		System.out.println("Data de locação: " + data);
		
		if(desconto > 0) {
			System.out.println("Desconto:" + desconto);
		}
		else {
			System.out.println("Desconto: " + "Não");
		}
		if(isSeguro) {
			System.out.println("Seguro: " + calcseguro());
			
			System.out.println("Valor total:" + calcDesc(desconto) + calcseguro());
		}
		else {
			System.out.println("Seguro: Não");
			
			System.out.println("Valor total:" + calcDesc(desconto));
		}
		
		
	}

	public  Veiculo getVec() {
		return vec;
	}

	public  void setVec(Veiculo vec) {
		this.vec = vec;
	}
	
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public int getDias() {
		return dias;
	}


	public void setDias(int dias) {
		this.dias = dias;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public boolean isSeguro() {
		return isSeguro;
	}


	public void setSeguro(boolean isSeguro) {
		this.isSeguro = isSeguro;
	}


	public double getDesconto() {
		return desconto;
	}


	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	
	
	
}