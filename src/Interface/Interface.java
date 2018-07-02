package Interface;

import java.text.SimpleDateFormat;
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
			    System.out.println("============================");
				System.out.println("==========MENU==========");
				System.out.println("1: Cadastrar novo cliente");
				System.out.println("2: Cadastrar novo plano");
				System.out.println("3: Habilitar novo celular");
				System.out.println("4: Excluir um celular");
				System.out.println("5: Adicionar creditos");
				System.out.println("6: Registrar Ligacao");
				System.out.println("7: Imprimir valor a pagar de conta de celular/Imprimir crédito total do celular");
				System.out.println("8: Listar extrato de ligacoes");
				System.out.println("9: Listar todos os clientes");
				System.out.println("10: Listar todos os celulares");
				System.out.println("11: Listar todos os planos");
				System.out.println("12: Informativo de vencimento dos celulares");
				System.out.println("13: Quitar conta");
				System.out.println("14: Zerar creditos");
				System.out.println("q : Sair do programa");
			    System.out.println("============================");
	
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
			    	fecharMenu = true;
			    	operadora.escreverArquivo();;
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
			} catch(ExcecaoCelular ecel) {
				System.out.println(ecel.getMessage());
				if(!(ecel.getCelular()==null))
					System.out.println(ecel.getCelular().getNumero());
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
		System.out.println("Habilitando novo celular");
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
				c = operadora.buscarCliente(cpfOuCnpj);
				System.out.println("Cliente cadastrado.");
				break;
			default:
				System.out.println("Entrada inválida, opções: 1 ou 2. Para voltar ao menu digite (q)");
				if(s.equals("q")||s.equals("Q"))
					return;
				
		}
		
		System.out.println("-----------------------------");
		System.out.println("Celular com plano pós pago ou pré pago?");
		System.out.println("1: Pré-pago");
		System.out.println("2: Pós-pago");
		
		String nomePlano = null;
		
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
				Plano p = operadora.buscarPlano(nomePlano); // checa se o plano existe
				System.out.println("Digite a data de vencimento da fartura no formato dd/mm/aaaa:");
				GregorianCalendar data = 
						GetStringToGregorianCalData("val", "Digite a data de vencimento da fartura no formato dd/mm/aaaa:");
				if(data == null)
					return;
				operadora.habilitarCelularPosPago(c, nomePlano, data);
				System.out.println("CELULAR HABILITADO!");
				System.out.println("Plano selecionado: " + nomePlano);
				System.out.println("Vencimento da fatura: " + DateToString(data));
				break;
			default:
				System.out.println("Entrada inválida, opções: 1 ou 2. Para voltar ao menu digite (q)");
				if(s.equals("q")||s.equals("Q"))
					return;
				
		}
		


	}
	void excluirCelular() throws ExcecaoCelular {
		Scanner scan = new Scanner(System.in);
		System.out.println("-----------------------------");
		System.out.println("Excluíndo celular");
		System.out.println("-----------------------------");
		System.out.println("Digite o número do celular a ser excluído: ");
		String numero = scan.nextLine();
		operadora.excluirCelular(Integer.parseInt(numero));
		System.out.println("Celular de número " + numero +  " excluído.");
		System.out.println("-----------------------------");
	}
	void adicionarCreditos() throws ExcecaoCelular{ //AINDA NAO TESTADO
		
		Scanner scan = new Scanner(System.in);
		System.out.println("-----------------------------");
		System.out.println("Adicionando créditos");
		System.out.println("-----------------------------");
		System.out.println("Digite o número do celular: ");
		String numeroCelular = scan.nextLine();
		System.out.println("Digite o valor do crédito a ser adicionado: ");
		String valor = scan.nextLine();
		operadora.adicionarCredito(Integer.parseInt(numeroCelular), Double.parseDouble(valor));
		
		System.out.println("Credito de " + valor
		+ " reais adicionados ao número " + numeroCelular);
		System.out.println("-----------------------------");
	}
	void registrarLigacao() throws ExcecaoCelular{ //AINDA NAO TESTADO
		
		Scanner scan = new Scanner(System.in);
		System.out.println("-----------------------------");
		System.out.println("Registrando Ligação");
		System.out.println("-----------------------------");
		System.out.println("Digite o número do celular: ");
		String numeroCelular = scan.nextLine();
		System.out.println("Digite a duração da ligação em minutos: ");
		String duracao = scan.nextLine();
		System.out.println("Digite a data da ligação no formato dd/mm/aaaa: ");
		
		GregorianCalendar dataLigacao = 
				GetStringToGregorianCalData("horario", "Digite a data da ligação no formato dd/mm/aaaa: ");
		if(dataLigacao == null)
			return;
		operadora.registrarLigacao(Integer.parseInt(numeroCelular), Double.parseDouble(duracao), dataLigacao);
		
		System.out.println("Ligacação registrada no número " + numeroCelular + " em "
				+ DateToString(dataLigacao)+ ".");
		System.out.println("-----------------------------");
	}
	
	
	void listarValorContaCredito() throws ExcecaoCelular { 
		
		System.out.println("-----------------------------");
		System.out.println("Acessando valor total a pagar/ Acessar credíto total de celular.");
		System.out.println("-----------------------------");
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite o número do celular: ");
		String numeroCelular = scan.nextLine();
		ValorData valordata = operadora.listarValorContaCredito(Integer.parseInt(numeroCelular));
		if(valordata.getTipo()=='c') {
			System.out.println("Crédito total disponível no celular: "
		+ String.format("%.2f", valordata.getValor()) + " reais.");
		    System.out.println("Data de validade dos créditos: " + DateToString(valordata.getData()));
		}else {
			System.out.println("Valor total a pagar até a validade:  "
		+ String.format("%.2f", valordata.getValor()) + " reais.");
		    System.out.println("Validade do faturamento: " + DateToString(valordata.getData()));
		}
		System.out.println("-----------------------------");
		
	}
	void extratoLigacoes() throws ExcecaoCelular {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("-----------------------------");
		System.out.println("Extrato de ligações do celular.");
		System.out.println("-----------------------------");
		System.out.println("Digite o número do celular do qual se deseja tirar o extrato: ");
		String numeroCelular = scan.nextLine();
		System.out.println("Extrato desde o dia(formato dd/mm/aaaa): ");
	
		GregorianCalendar dataExtrato = 
				GetStringToGregorianCalData("data","Extrato desde o dia(formato dd/mm/aaaa): ");
		
		if(dataExtrato==null)
			return;
		
	    String dateFormattedT = DateToString(dataExtrato);
	    
		System.out.println("-----------------------------");
		System.out.println("EXTRATO DO CELULAR "+ numeroCelular);
		System.out.println("DESDE " + dateFormattedT);
		System.out.println("-----------------------------");
		
		ArrayList<Ligacao> ligacoesExtrato = operadora.listarExtratoLigacoes(Integer.parseInt(numeroCelular), dataExtrato);
		for(Ligacao l: ligacoesExtrato) {
			

			System.out.println("===========");
			System.out.println("Data da ligação: " + DateToString(l.getDataLigacao()));
			System.out.println("Duração: " + l.getDuracaoMinutos());
			System.out.println("Valor cobrado pela ligação: " + String.format("%.2f", l.getValorCobrado()));
			System.out.println("===========");
			//lembrar de chegar comparações entre dias como por exemplo: 31/06/2018 e 01/06/2018
			// nao sei porque mas está dando como se fosse a mesma data.
		}
	}
	void listarClientes() throws ExcecaoCelular {

		System.out.println("LISTA DE CLIENTES");
		
		int numeroDoCliente = 0;
		for(Cliente c: operadora.listarClientes()) {
			numeroDoCliente+=1;
			System.out.println("-------------CLIENTE("+numeroDoCliente+")----------------");
			System.out.println(c.getNome());
			System.out.println(c.getCpfOuCnpj());
			System.out.println(c.getEndereco());
			ArrayList<Celular> celulares = c.getListaCel();
			if(celulares.isEmpty()) {
				System.out.println("Nenhum celular registrado.");
			}else {
				System.out.println("Celulares: ");
				int numeroCelularDoCliente = 0;
				for(Celular cel: celulares) {
					numeroCelularDoCliente +=1;
					System.out.println("====Celular("+numeroCelularDoCliente+")=====" );
					System.out.println(cel.getNumero());
					ValorData valordata = operadora.listarValorContaCredito(cel.getNumero());
					if(cel.getConta().getTipo()=='c') {
						System.out.println("Plano pré-pago.");
						System.out.println("Crédito total disponível no celular: " + valordata.getValor());
						
						if(valordata.getData() != null) {
						    System.out.println("Data de validade dos créditos: " + DateToString(valordata.getData()));
						}else {
							System.out.println("Vencimento ainda não estabelecido porque o celular não tem crédito.");
						}
					}
					else {
						System.out.println("Plano pós-pago.");
						System.out.println("Valor total a pagar até o vencimento da fartura:  " 
						+ valordata.getValor());
						
					    System.out.println("Validade do faturamento: " + DateToString(valordata.getData()));
					}
					System.out.println("Nome do plano: " + cel.getConta().getPlano().getNome());
					System.out.println("===================");
				}
			}
			System.out.println("--------------------------------------------------");
		}
	}
	void listarCelulares() {
		System.out.println("LISTA DE CELULARES");
		for(Celular c: operadora.listarCelulares()) {
			System.out.println("-----------------------------");
			System.out.println("Número: " + c.getNumero());
			System.out.println("CPf ou CNPJ do cliente: " + c.getCliente().getCpfOuCnpj());
			System.out.println("Plano: " + c.getConta().getPlano().getNome());
			if(c.getConta().getTipo() == 'a') {
				System.out.println("Celular pós-pago");
			}
			else
				System.out.println("Celular pré-pago");
		}
	}
	void listarPlanos() {
		
		System.out.println("LISTA DE PLANOS");
		for(Plano p: operadora.listarPlanos()) {
			System.out.println("-----------------------------");
			System.out.println("Nome do plano: " + p.getNome());
			System.out.println("Valor por minuto: " + p.getValorPorMinuto());
		}
		
	}
	void informativoVencimento() throws ExcecaoCelular {
		
		System.out.println("-----------------------------");
		System.out.println("Lista de celulares com conta/crédito vencido");
		System.out.println("-----------------------------");
		ArrayList<Celular> celularesContaVencida = operadora.informativoDeVencimento();
		if(celularesContaVencida.isEmpty())
			System.out.println("Nenhum celular com conta/crédito vencido");
		for(Celular cel: celularesContaVencida){
			
			
			
			System.out.println("====================");
			System.out.println("Número do celular: " + cel.getNumero());
			ValorData valordata = operadora.listarValorContaCredito(cel.getNumero());
			if(cel.getConta().getTipo()=='c') {
				System.out.println("Plano pré-pago.");
				System.out.println("Crédito total disponível no celular: " + valordata.getValor());
				
				if(valordata.getData() != null) {
					SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
				    fmt.setCalendar(valordata.getData());
				    String dateFormatted = fmt.format(valordata.getData().getTime());
				    System.out.println("Data de validade dos créditos: " + dateFormatted);
				}else {
					System.out.println("Vencimento ainda não estabelecido porque o celular não tem crédito.");
				}
			}
			else {
				System.out.println("Plano pós-pago.");
				System.out.println("Valor total a pagar até o vencimento da fartura:  " 
				+ valordata.getValor());
				
				SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
			    fmt.setCalendar(valordata.getData());
			    String dateFormatted = fmt.format(valordata.getData().getTime());
			    System.out.println("Validade do faturamento: " + dateFormatted);
			}
			System.out.println("Nome do plano: " + cel.getConta().getPlano().getNome());
			Cliente c = cel.getCliente();
			System.out.println("Cliente: ");
			System.out.println(c.getNome());
			System.out.println(c.getCpfOuCnpj());
			System.out.println(c.getEndereco());
			System.out.println("====================");
			
		}
		
		
	}
	void quitarConta() {}

	void zerarCredito() throws ExcecaoCelular {
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite o número do celular do qual se deseja zerar os créditos: ");
		String numeroCelular = scan.nextLine();
		operadora.zerarCreditoConta(Integer.parseInt(numeroCelular));
		
		System.out.println("Crédito do número "+ numeroCelular + " zerado.");
		
	}
	
	private void jumpSpace() {
		for (int i = 0; i < 3; ++i)  
		       System.out.println();
	}
	
	private String DateToString(GregorianCalendar Data) {
		SimpleDateFormat fmtT = new SimpleDateFormat("dd/MM/yyyy 'às' h:mm:ss");
	    fmtT.setCalendar(Data);
	    String dateFormattedT = fmtT.format(Data.getTime());
	    return dateFormattedT;
		
	}
	private GregorianCalendar GetStringToGregorianCalData(String caso, String repeticao) {
		
		Scanner scan = new Scanner(System.in);
		String dia = null;String  mes = null;String ano = null;
		String hora = null;String minuto = null; String segundo = null;
		GregorianCalendar data = null;
		boolean tentativaDigitarData = false;
		while(!tentativaDigitarData) {
			try {
				String Data =  scan.nextLine();
				dia = Data.substring(0, Data.indexOf("/"));
				mes = Data.substring(Data.indexOf("/")+1, Data.indexOf("/",Data.indexOf("/")+1));
				ano = Data.substring(1+Data.indexOf("/",Data.indexOf("/")+1), Data.length());
				
				if(caso.equals("horario")) {
					
					System.out.println("Digite o horário da ligação no formato hh:mm:ss");
					String horarioLigacao=  scan.nextLine();
					hora = horarioLigacao.substring(0, horarioLigacao.indexOf(":"));
					minuto = horarioLigacao.substring(horarioLigacao.indexOf(":")+1, horarioLigacao.indexOf(":",horarioLigacao.indexOf(":")+1));
					segundo = horarioLigacao.substring(1+horarioLigacao.indexOf(":",horarioLigacao.indexOf(":")+1), horarioLigacao.length());
					
					data = new GregorianCalendar(Integer.parseInt(ano), Integer.parseInt(mes)-1, Integer.parseInt(dia),
								Integer.parseInt(hora),Integer.parseInt(minuto),Integer.parseInt(segundo));
					return data;
				}else if (caso.equals("data")){
					
					data = new GregorianCalendar(Integer.parseInt(ano),
							Integer.parseInt(mes)-1, Integer.parseInt(dia));
					return data;
				}else if (caso.equals("val")) {
					String horarioLigacao = "23:59:59";
					hora = horarioLigacao.substring(0, horarioLigacao.indexOf(":"));
					minuto = horarioLigacao.substring(horarioLigacao.indexOf(":")+1, horarioLigacao.indexOf(":",horarioLigacao.indexOf(":")+1));
					segundo = horarioLigacao.substring(1+horarioLigacao.indexOf(":",horarioLigacao.indexOf(":")+1), horarioLigacao.length());
					data = new GregorianCalendar(Integer.parseInt(ano), Integer.parseInt(mes)-1, Integer.parseInt(dia),
							Integer.parseInt(hora),Integer.parseInt(minuto),Integer.parseInt(segundo));
					return data;
				}
				
				
				tentativaDigitarData = true;
				}catch(Exception e){
					System.out.println("Data deve ser digitada no formato indicado (dd/mm/aaaa)");
					System.out.println("Por exemplo: 03/04/2018.");
					System.out.println("Tente novamente (aperte qualquer botao) ou volte para o menu (q).");
					String strError =  scan.nextLine();
					if(strError.equals("q")||strError.equals("Q"))
						return null;
					System.out.println(repeticao);

				}
		}

		return null;
	}
}
