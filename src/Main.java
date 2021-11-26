import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	private static String fileClientes = "clientes.txt"; 
	private static String fileVeiculos = "veiculos.txt";
	private static String fileLocacoes = "locacoes.txt";
	
	public static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public static ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
	public static ArrayList<Locacao> locacoes = new ArrayList<Locacao>();

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		
		int option = 0;		
		
		/*try {
			
		} catch(InputMismatchException e) {
			System.out.println("Input inválido");
			sc.next();
			continue;
		}*/
		
		while(true) {
			menu();
			
			try {
				clientes.clear();
				veiculos.clear();
				locacoes.clear();
				readClients(fileClientes);
				readVehicles(fileVeiculos);
				readRents(fileLocacoes);
				
			} catch (FileNotFoundException e1) {

				System.out.println("Arquivo não encontrado.");
			}
			
			try {
				option = sc.nextInt();
				sc.nextLine();
			} catch(InputMismatchException e) {
				System.out.println("Input inválido");
				sc.next();
				continue;
			}
			switch(option) {
				case 1:
					try {
						String nome;
						int idade;
						
						System.out.println("Digite seu nome: ");
						nome = sc.next();
						
						System.out.println("Digite sua idade: ");
						idade = sc.nextInt();
						
						clientes.add(new Cliente(nome, idade));
						
						writeFile(clientes.get(clientes.size() - 1).getNome() + "|" + clientes.get(clientes.size() - 1).getIdade() + "\n", fileClientes);
						
						System.out.println("Cadastro realizado com sucesso!");
					}
					catch(InputMismatchException e) {
						System.out.println("Input inválido.");
						sc.next();
						continue;
					}
				break;
				
				case 2:
					System.out.println("---Clientes---");
					if(clientes.size() > 0) {
						for(int i = 0; i<clientes.size(); i++) {
							System.out.println(clientes.get(i).getNome() + " (" + clientes.get(i).getIdade() + ")");
						}
					}
					else {
						System.out.println("Não há clientes para listar.");
					}
				break;
				
				case 3:
					String tipo;
					String modelo;
					double valorL;
					String desc;
					String placa;
					int pass;
					String eStr;
					boolean isElectric;
					
					try {
						
						System.out.println("Digite o tipo do veículo a ser cadastrado (Carro/Moto): ");
						tipo = sc.nextLine();
						//System.out.println(tipo);
						
						if(tipo.toLowerCase().equals("carro") || tipo.toLowerCase().equals("moto")) {
							
						
							System.out.println("Digite o modelo do veículo: ");
							modelo = sc.nextLine();
							
							
							System.out.println("Digite o valor da diária: ");
							valorL = sc.nextDouble();
							sc.nextLine();
							
							System.out.println("Digite a descrição do veículo: ");
							desc = sc.nextLine();
							
							System.out.println("Digite a placa do veículo: ");
							placa = sc.nextLine();
							
							if(tipo.toLowerCase().contains("carro")) {
								System.out.println("Digite a quantidade de assentos: ");
								pass = sc.nextInt();
								
								veiculos.add(new Carro(modelo, valorL, desc, placa, pass));
								writeFile(veiculos.get(veiculos.size() - 1).getModelo() + "|" + veiculos.get(veiculos.size() - 1).getValorL() + "|" + veiculos.get(veiculos.size() - 1).getDesc() + "|" + veiculos.get(veiculos.size() - 1).getPlaca()  + "|" + ((Carro) veiculos.get(veiculos.size() - 1)).getPass() + "\n", fileVeiculos);
							}
							else if(tipo.toLowerCase().contains("moto")) {
								System.out.println("A moto possui partida elétrica? Sim/Não");
								eStr = sc.next();
								
								if(eStr.toLowerCase().contains("sim")) {
									isElectric = true;
									veiculos.add(new Moto(modelo, valorL, desc, placa, isElectric));
									writeFile(veiculos.get(veiculos.size() - 1).getModelo() + "|" + veiculos.get(veiculos.size() - 1).getValorL() + "|" + veiculos.get(veiculos.size() - 1).getDesc() + "|" + veiculos.get(veiculos.size() - 1).getPlaca()  + "|" + ((Moto) veiculos.get(veiculos.size() - 1)).isElectric() + "\n", fileVeiculos);
								}
								else if(eStr.toLowerCase().contains("não")) {
									isElectric = false;
									veiculos.add(new Moto(modelo, valorL, desc, placa, isElectric));
									
									writeFile(veiculos.get(veiculos.size() - 1).getModelo() + "|" + veiculos.get(veiculos.size() - 1).getValorL() + "|" + veiculos.get(veiculos.size() - 1).getDesc() + "|" + veiculos.get(veiculos.size() - 1).getPlaca()  + "|" + ((Moto) veiculos.get(veiculos.size() - 1)).isElectric() + "\n", fileVeiculos);
								}
								
								
							}
							
							System.out.println("Veículo cadastrado com sucesso!");
						}
						else {
							System.out.println("Tipo de veículo inválido.");
						}
					
						
					} catch(InputMismatchException e) {
						System.out.println("Input inválido");
						sc.next();
						continue;
					}
					
					
				break;
				
				case 4:
					System.out.println("---Veículos---");
					if(veiculos.size() > 0) {
						for(int i = 0; i<veiculos.size(); i++) {
							System.out.println(veiculos.get(i).getModelo() + " (" + veiculos.get(i).getClass().getName() + ")");
							System.out.println("   Valor de locação: " + veiculos.get(i).getValorL());
							System.out.println("   " + veiculos.get(i).getDesc());
							System.out.println("   Placa: " + veiculos.get(i).getPlaca());
							if(veiculos.get(i) instanceof Carro) {
								System.out.println("   Número de assentos: " + ((Carro) veiculos.get(i)).getPass());
							}
							
							else if(veiculos.get(i) instanceof Moto) {
								if(((Moto) veiculos.get(i)).isElectric() == true) {
									System.out.println("   Possui partida elétrica.");
									
								}
								
								else if(((Moto) veiculos.get(i)).isElectric() == false) {
									System.out.println("   Não possui partida elétrica.");
								}
							}
							System.out.println("   Alugado: " + (veiculos.get(i).isLocado() == true ? "Sim":"Não"));
							System.out.println("");
						}
					}
					else {
						System.out.println("Não há veículos para listar.");
					}
				break;
				
				case 5:
					String nome;
					int idade;
					Cliente cliente;
					int cIndex = 0;
					int vIndex = 0;
					
					String modeloL;
					Veiculo veh;
					
					int dias;
					String data;
					String segStr;
					boolean hasSeguro;
					double desconto;
					double valor;
					
					boolean logged = false;
					boolean hasCar = false;
					
					try {
						
						System.out.println("Digite seu nome: ");
						nome = sc.nextLine();
						
						System.out.println("Digite sua idade: ");
						idade = sc.nextInt();
						sc.nextLine();
						
						for(int i = 0; i<clientes.size(); i++) {
							if(clientes.get(i).getNome().toLowerCase().equals(nome) && clientes.get(i).getIdade() == idade) {
								logged = true;
								cIndex = i;
							}
							
						}
						
						if(logged == true) {
							System.out.println("Digite o modelo do veículo que deseja alugar: ");
							modeloL = sc.nextLine();
							
							for(int i = 0; i<veiculos.size(); i++) {
								if(veiculos.get(i).getModelo().toLowerCase().equals(modeloL)) {
									hasCar = true;
									vIndex = i;
								}
								
							}
							if(hasCar == true) {
								System.out.println("Digite a quantidade de dias da locação: ");
								dias = sc.nextInt();
								sc.nextLine();
								
								System.out.println("Digite a data da locação: ");
								data = sc.nextLine();
								
								System.out.println("Digite a porcentagem do desconto: ");
								desconto = sc.nextDouble();
								sc.nextLine();
								
								System.out.println("A locação possui seguro? Sim/Não: ");
								segStr = sc.nextLine();
								
								if(segStr.toLowerCase().contains("sim")) {
									hasSeguro = true;
									
									//Locacao.setVec(veiculos.get(vIndex));
									
									//valor = Locacao.calcDesc(desconto) + Locacao.calcseguro();
									
									locacoes.add(new Locacao(clientes.get(cIndex), dias, data, hasSeguro, desconto, veiculos.get(vIndex)));
									locacoes.get(locacoes.size()-1).print();
									
									veiculos.get(vIndex).setLocado(true);
									
									writeFile(cIndex + "|" + locacoes.get(locacoes.size() - 1).getDias() + "|" + locacoes.get(locacoes.size() - 1).getData() + "|" + locacoes.get(locacoes.size() - 1).isSeguro() + "|" + locacoes.get(locacoes.size() - 1).getDesconto() + "|" + locacoes.get(locacoes.size() - 1).getValor() + "|" + vIndex + "\n", fileLocacoes);
									
									//Locacao.print();
									
									
								}
								else if(segStr.toLowerCase().contains("não")) {
									hasSeguro = false;
									
									//valor = Locacao.calcDesc(desconto);
									
									locacoes.add(new Locacao(clientes.get(cIndex), dias, data, hasSeguro, desconto, veiculos.get(vIndex)));
									locacoes.get(locacoes.size()-1).print();
									
									veiculos.get(vIndex).setLocado(true);
									
									writeFile(cIndex + "|" + locacoes.get(locacoes.size() - 1).getDias() + "|" + locacoes.get(locacoes.size() - 1).getData() + "|" + locacoes.get(locacoes.size() - 1).isSeguro() + "|" + locacoes.get(locacoes.size() - 1).getDesconto() + "|" + locacoes.get(locacoes.size() - 1).getValor() + "|" + vIndex + "\n", fileLocacoes);
									
									
									
								}
								
							}
							else {
								System.out.println("Veículo não registrado.");
							}
						}
						else {
							System.out.println("Cliente não registrado.");
						}
					} catch(InputMismatchException e) {
						System.out.println("Input inválido");
						sc.next();
						continue;
					}
						
					
				break;
				
				case 6:
					System.out.println("---Locações---");
					if(locacoes.size() > 0) {
						for(int i = 0; i<locacoes.size(); i++) {
							System.out.println(locacoes.get(i).getCliente().getNome() + " (" + locacoes.get(i).getCliente().getIdade() + ") " + "- " + locacoes.get(i).getVec().getModelo());
							System.out.println("   Dias de aluguel: " + locacoes.get(i).getDias());
							System.out.println("   Data da locação: " + locacoes.get(i).getData());
							if(locacoes.get(i).isSeguro() == true) {
								System.out.println("   Possui seguro\n");
							}
							else if(locacoes.get(i).isSeguro() == false) {
								System.out.println("   Não possui seguro\n");
							}
						}
					}
					else {
						System.out.println("Não há locações para listar.");
					}
				break;
				
				case 7:
					System.out.println("---Veículos Disponíveis---");
					if(veiculos.size() > 0) {
						for(int i = 0; i<veiculos.size(); i++) {
							if(veiculos.get(i).isLocado() == false) {
								System.out.println(veiculos.get(i).getModelo() + " (" + veiculos.get(i).getClass().getName() + ")");
								System.out.println("   Valor de locação: " + veiculos.get(i).getValorL());
								System.out.println("   " + veiculos.get(i).getDesc());
								System.out.println("   Placa: " + veiculos.get(i).getPlaca());
								if(veiculos.get(i) instanceof Carro) {
									System.out.println("   Número de assentos: " + ((Carro) veiculos.get(i)).getPass());
								}
								
								else if(veiculos.get(i) instanceof Moto) {
									if(((Moto) veiculos.get(i)).isElectric() == true) {
										System.out.println("   Possui partida elétrica.");
										
									}
									
									else if(((Moto) veiculos.get(i)).isElectric() == false) {
										System.out.println("   Não possui partida elétrica.");
									}
								}
								System.out.println("   Alugado: " + (veiculos.get(i).isLocado() == true ? "Sim":"Não"));
								System.out.println("");
							}
						}
					}
					else {
						System.out.println("Não há veículos para listar.");
					}
				break;
				
				case 8:
					System.exit(1);
				break;
				
				default:
					System.out.println("Opção inválida.");
				break;
			}
		}
		
		
		
	}
	
	
	public static void menu() {
		System.out.println("\n---Menu---\n");
		System.out.println("1- Cadastrar cliente");
		System.out.println("2- Listar clientes");
		System.out.println("3- Cadastrar veículos");
		System.out.println("4- Listar veículos");
		System.out.println("5- Alugar veículos");
		System.out.println("6- Listar locações");
		System.out.println("7- Veículos disponíveis");
		System.out.println("8- Encerrar");
	}
	
	

	
	
	public static void writeFile(String conteudo, String nomeArquivo)

	{

		try {

			Writer bw;

			bw = new BufferedWriter(new FileWriter(nomeArquivo, true));			

			bw.write(conteudo);

			bw.close();

		} catch (UnsupportedEncodingException e) {

			System.out.println("Isso é quase impossível de acontecer, mas há algo errado com sua JVM.");

		} catch (Exception e) {

			System.out.println("Houve um erro inesperado.");

		}

	}
	
	
	public static ArrayList<Cliente> readClients(String filename) throws FileNotFoundException{
		
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		
		//ArrayList<Cliente> clientesGen = new ArrayList<Cliente>();
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			
			if(line.contains("|")) {
				String[] items = line.split("\\|");
				
				String nome = items[0];
				int idade = Integer.parseInt(items[1]);
				
				Cliente newClient = new Cliente(nome, idade);
				
				clientes.add(newClient);
			}
			else {
				System.out.println("Erro fatal no banco de dados. O programa será encerrado.");
				System.exit(1);
			}
		}
		
		
		return null;
		
	}
	
	public static ArrayList<Veiculo> readVehicles(String filename) throws FileNotFoundException{
		
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		
		//ArrayList<Cliente> clientesGen = new ArrayList<Cliente>();
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			
			if(line.contains("|")) {
				String[] items = line.split("\\|");
				
				if(items[4].contains("true") || items[4].contains("false")) {
					String modelo = items[0];
					double valor = Double.parseDouble(items[1]);
					String desc = items[2];
					String placa = items[3];
					boolean isElectric = Boolean.parseBoolean(items[4]);
					
					
					Moto newMoto = new Moto(modelo, valor, desc, placa, isElectric);
					
					veiculos.add(newMoto);
				}
				
				else {
					String modelo = items[0];
					double valor = Double.parseDouble(items[1]);
					String desc = items[2];
					String placa = items[3];
					int pass = Integer.parseInt(items[4]);
					
					
					Carro newCarro = new Carro(modelo, valor, desc, placa, pass);
					
					veiculos.add(newCarro);
				}
			}
			else {
				System.out.println("Erro fatal no banco de dados. O programa será encerrado.");
				System.exit(1);
			}
		}
		
		
		return null;
		
	}
	
	public static ArrayList<Locacao> readRents(String filename) throws FileNotFoundException{
		
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		
		//ArrayList<Cliente> clientesGen = new ArrayList<Cliente>();
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			
			if(line.contains("|")) {
				String[] items = line.split("\\|");
				
				Cliente cliente = clientes.get(Integer.parseInt(items[0]));
				int dias = Integer.parseInt(items[1]);
				String data = items[2];
				boolean isSeguro = Boolean.parseBoolean(items[3]);
				double desconto = Double.parseDouble(items[4]);
				double valor = Double.parseDouble(items[5]);
				Veiculo veiculo = veiculos.get(Integer.parseInt(items[6]));
				
				veiculo.setLocado(true);
				
				Locacao newRent = new Locacao(cliente, dias, data, isSeguro, desconto, veiculo);
				
				locacoes.add(newRent);
			}
			else {
				System.out.println("Erro fatal no banco de dados. O programa será encerrado.");
				System.exit(1);
			}
		}
		
		
		return null;
		
	}
	
	
}
