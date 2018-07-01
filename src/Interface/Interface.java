package Interface;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

import Operadora.*;
import excecoes.*;

public class Interface {
	
	private Operadora operadora;
	private static boolean menuAberto = false;
	
	public Interface(Operadora operadora){
		this.operadora = operadora;
	}
	
	public void menu() {
		

		boolean fecharMenu = false;

		while(!fecharMenu) {
			Scanner scan = null;
			scan = new Scanner(System.in);
			try {
	
				if(menuAberto) {
					System.out.println();
				    System.out.println("Aperte enter para voltar ao menu");
				    String a = scan.nextLine();
				}
			    menuAberto = true;
				jumpSpace();
				System.out.println("----------MENU----------");
				System.out.println("1: Cadastrar novo cliente");
				System.out.println("2: Cadastrar novo plano");
				System.out.println("3: Habilitar novo celular");
				System.out.println("4: Excluir um celular");
				System.out.println("5: Adicionar creditos");
				System.out.println("6: Registrar Ligacao");
				System.out.println("7: Listar valor a pagar conta/ Listar crédito");
				System.out.println("8: Listar extrato de ligacoes");
				System.out.println("9: Listar todos os clientes");
				System.out.println("10: Listar todos os celulares");
				System.out.println("11: Listar todos os planos");
				System.out.println("12: Informativo de vencimento dos celulares");
				System.out.println("13: Quitar conta");
				System.out.println("14: Zerar creditos");
				System.out.println("q : Sair do programa");
	
	
			    String s = scan.nextLine();
			    switch(s) {
			    case "1":
			    	cadastrarCliente();
			    	break;
			    case "2":
			    	cadastrarPlano();
			    	break;
			    case "3":
			    	habilitarCelular();
			    	break;
			    case "4":
			    	excluirCelular();
			    	break;
			    case "5":
			    	adicionarCreditos();
			    	break;
			    case "6":
			    	registrarLigacao();
			    	break;
			    case "7":
			    	listarValorContaCredito();
			    	break;
			    case "8":
			    	extratoLigacoes();
			    	break;
			    case "9":
			    	listarClientes();
			    	break;
			    case "10":
			    	listarCelulares();
			    	break;
			    case "11":
			    	listarPlanos();
			    	break;
			    case "12":
			    	informativoVencimento();
			    	break;
			    case "13":
			    	quitarConta();
			    	break;
			    case "14":
			    	zerarCredito();
			    	break;
			    case "15":
			    	zerarCredito();
			    	break;
			    case "q":
			    	System.out.println();
			    	System.out.println("Programa fechado");
			    	gravarDadosArquivo();
			    	fecharMenu = true;
			    	operadora.writeFile();
				    if(scan!=null)
				        scan.close();
			    	break;
			    default:
			    	System.out.println("Entrada inválida. Digite um valor entre 1 e 15,"
			    			+ " ou então saia do programa e grave os dados com q.");
			    
			    }
			} catch(ExcecaoCliente ec) {
				System.out.println(ec.getMessage());
				if(!(ec.getCliente()==null))
					System.out.println(ec.getCliente().toString());
			} catch(ExcecaoPlano ep) {
				System.out.println(ep.getMessage());
			}
			finally {
				
			}
		
		}
	    
	}

	void cadastrarCliente() throws ExcecaoCliente {
		Scanner scan = new Scanner(System.in);
		System.out.println("Insira o nome do Cliente:");
		String nome = scan.nextLine();
		System.out.println("Insira o cpf ou o cnpj do Cliente:");
		String cpfOuCnpj = scan.nextLine();
		System.out.println("Insira o endereco do Cliente:");
		String end = scan.nextLine();
		operadora.cadastrarCliente(nome, end, cpfOuCnpj);
		System.out.println("Cliente cadastrado.");
		
	}
	void cadastrarPlano() throws ExcecaoPlano {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Insira o nome do novo plano:");
		String nome = scan.nextLine();
		System.out.println("Insira o valor por minuto deste novo plano:");
		String valorPorMinuto = scan.nextLine();
		operadora.cadastrarPlano(nome, Double.parseDouble(valorPorMinuto));
		System.out.println("Plano cadastrado.");
		
	}
	void habilitarCelular() throws ExcecaoCliente, ExcecaoPlano {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("-----------------------------");
		System.out.println("Habilitando novo celular.");
		System.out.println("-----------------------------");
		System.out.println("Cliente já está registrado?");
		System.out.println("1: Cliente já registrado. ");
		System.out.println("2: Novo cliente. ");
		String s = scan.nextLine();
		Cliente c = null;
		switch(s) {
		
			case "1":
				System.out.println("Digite o número de seu CPF ou CNPJ:");
				s = scan.nextLine();
				c = operadora.buscarCliente(s);
				break;
			case "2":
				System.out.println("Insira o nome do Cliente:");
				String nome = scan.nextLine();
				System.out.println("Insira o cpf ou o cnpj do Cliente:");
				String cpfOuCnpj = scan.nextLine();
				System.out.println("Insira o endereco do Cliente:");
				String end = scan.nextLine();
				operadora.cadastrarCliente(nome, end, cpfOuCnpj);
				c = new Cliente(nome, end, cpfOuCnpj);
				System.out.println("Cliente cadastrado.");
				break;
			default:
				System.out.println("Entrada inválida, opções: 1 ou 2.");
				
		}
		
		System.out.println("-----------------------------");
		System.out.println("Celular com plano pós pago ou pré pago?");
		System.out.println("1: Pré-pago");
		System.out.println("2: Pós-pago");
		
		String nomePlano = null;
		GregorianCalendar data = null;
		s = scan.nextLine();
		switch(s) {
		
			case "1":
				System.out.println("Plano pré-pago selecionado.");
				System.out.println("Digite o nome do plano:");
				nomePlano = scan.nextLine();
				
				operadora.habilitarCelularPrePago(c, nomePlano);
				break;
			case "2":
				System.out.println("Plano pós-pago selecionado.");
				System.out.println("Digite o nome do plano:");
				nomePlano = scan.nextLine();
				System.out.println("Digite a data de vencimento da fartura:");
				String Data = new String();
				Data = scan.nextLine();
				
				String dia = null; String mes = null; String ano = null;
				dia = Data.substring(0, Data.indexOf("/"));
				mes = Data.substring(Data.indexOf("/")+1, Data.indexOf("/",Data.indexOf("/")+1));
				ano = Data.substring(1+Data.indexOf("/",Data.indexOf("/")+1), Data.length());
				data = new GregorianCalendar(Integer.parseInt(ano), Integer.parseInt(mes)-1, Integer.parseInt(dia));
				
				operadora.habilitarCelularPosPago(c, nomePlano, data);
				break;
			default:
				System.out.println("Entrada inválida, opções: 1 ou 2.");
				
		}
		


	}
	void excluirCelular() {}
	void adicionarCreditos() {}
	void registrarLigacao() {}
	void listarValorContaCredito() {}
	void extratoLigacoes() {
		
		Scanner scan = new Scanner(System.in);
		String dia = null; String mes = null; String ano = null;
		GregorianCalendar dataInicial = null;
		String Data = new String();
		System.out.println("Digite a data inicial no formato d/m/aaaa:");
		Data = scan.nextLine();
		dia = Data.substring(0, Data.indexOf("/"));
		mes = Data.substring(Data.indexOf("/")+1, Data.indexOf("/",Data.indexOf("/")+1));
		ano = Data.substring(1+Data.indexOf("/",Data.indexOf("/")+1), Data.length());
		dataInicial = new GregorianCalendar(Integer.parseInt(ano), Integer.parseInt(mes)-1, Integer.parseInt(dia));
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println("EXTRATO DESDE " + dia + "/" + mes + "/" + ano);
	}
	void listarClientes() {

		System.out.println("LISTA DE CLIENTES");
		
		for(Cliente c: operadora.listarClientes()) {
			System.out.println("-----------------------------");
			System.out.println(c.getNome());
			System.out.println(c.getCpfOuCnpj());
			System.out.println(c.getEndereco());
			ArrayList<Celular> celulares = c.getListaCel();
			if(celulares.isEmpty()) {
				System.out.println("Nenhum celular registrado.");
			}else {
				System.out.println("Celulares: ");
				for(Celular cel: celulares) {
					System.out.println(cel.getNumero());
				}
			}

		}
	}
	void listarCelulares() {}
	void listarPlanos() {
		
		System.out.println("LISTA DE PLANOS");
		for(Plano p: operadora.listarPlanos()) {
			System.out.println("-----------------------------");
			System.out.println("Nome do plano: " + p.getNome());
			System.out.println("Valor por minuto: " + p.getValorPorMinuto());
		}
		
	}
	void informativoVencimento() {}
	void quitarConta() {}
	void zerarCredito() {}
	void gravarDadosArquivo() {}
	
	private void jumpSpace() {
		for (int i = 0; i < 3; ++i)  
		       System.out.println();
	}
	
	
	
}
