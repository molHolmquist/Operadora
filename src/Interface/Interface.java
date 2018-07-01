package Interface;

import java.util.Scanner;

import Operadora.*;
import excecoes.*;

public class Interface {
	
	private Operadora operadora;
	private static boolean menuAberto = false;
	
	public Interface(Operadora operadora){
		this.operadora = operadora;
	}
	
	public boolean menu() {
		
		Scanner scan = null;
		boolean fecharMenu = false;
		try {
			scan = new Scanner(System.in);
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
		    	//Apenas sai do menu
		    	gravarDadosArquivo();
		    	fecharMenu = true;
		    	break;
		    default:
		    	System.out.println("Entrada inválida. Digite um valor entre 1 e 15, ou então saia do programa e grave os dados com q.");
		    	menu();
		    	//throw new IllegalArgumentException("Função inválida. Digite um valor entre 1 e 13, ou então saia do programa com q.");
		    
		    }
		} catch(ExcecaoCliente ec) {
			System.out.println(ec.getMessage());
			System.out.println(ec.getCliente().toString());
		}
		finally {
		    if(scan!=null)
		        scan.close();
		}
	    return fecharMenu;
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
		
		
	}
	void cadastrarPlano() {}
	void habilitarCelular() {}
	void excluirCelular() {}
	void adicionarCreditos() {}
	void registrarLigacao() {}
	void listarValorContaCredito() {}
	void extratoLigacoes() {}
	void listarClientes() {}
	void listarCelulares() {}
	void listarPlanos() {}
	void informativoVencimento() {}
	void quitarConta() {}
	void zerarCredito() {}
	void gravarDadosArquivo() {}
	
	private void jumpSpace() {
		for (int i = 0; i < 3; ++i)  
		       System.out.println();
	}
	
	
	
}
