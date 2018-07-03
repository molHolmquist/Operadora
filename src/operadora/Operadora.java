package Operadora;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import excecoes.ExcecaoPlano;
import excecoes.ExcecaoCelular;
import excecoes.ExcecaoCliente;

public class Operadora implements Serializable{
	
	private String nomeOperadora;
	private ArrayList<Cliente> clientes;
	private ArrayList<Celular> celulares;
	private ArrayList<Plano> planos;
	
	
	public Operadora(ArrayList<Cliente> clientes, ArrayList<Celular> celulares, ArrayList<Plano> planos) {
		super();
		this.clientes = clientes;
		this.celulares = celulares;
		this.planos = planos;
	}
	
	public Operadora(String nomeOperadora) {
		this.nomeOperadora = nomeOperadora;
		clientes = new ArrayList<Cliente>();
		celulares = new ArrayList<Celular>();
		planos = new ArrayList<Plano>();
	}

	public void cadastrarCliente(String nome, String endereco, String cpfOuCnpj) throws ExcecaoCliente {

		//if(clientes != null) {
		for(Cliente c: clientes) {
			
			if(c.getCpfOuCnpj().equals(cpfOuCnpj)) {
				throw new ExcecaoCliente("Cliente já registrado.", c);
			}
		}
		//}
		Cliente cliente = new Cliente(nome,endereco,cpfOuCnpj);
		clientes.add(cliente);
		
	}
	
	
	public void cadastrarPlano(String nome, double valorPorMinuto) throws ExcecaoPlano {
		
		for(Plano p: planos) {	
			if(p.getNome().equals(nome)) {
				throw new ExcecaoPlano("Plano já registrado.", p);
			}
		}
		Plano plano = new Plano(nome, valorPorMinuto);
		planos.add(plano);
		
	}
	
	
	public void habilitarCelularPosPago(Cliente cliente, String nomePlano, GregorianCalendar vencimentoFartura) 
			throws ExcecaoPlano, ExcecaoCliente {
		
		Plano plano = buscarPlano(nomePlano); //Joga ExcecaoPlano caso nao encontre um plano
		Cliente buscaCliente = buscarCliente(cliente.getCpfOuCnpj()); //Joga ExcecaoCliente caso nao encontre cliente
		
		//Talvez adicionar excecao de vencimento incoerente.
		Celular celular = new Celular(cliente, plano, vencimentoFartura);
		celulares.add(celular);
		cliente.addCelular(celular);
		
		
	}
	
	
	public void habilitarCelularPrePago(Cliente cliente, String nomePlano) 
			throws ExcecaoPlano, ExcecaoCliente {
		
		Plano plano = buscarPlano(nomePlano); //Joga ExcecaoPlano caso nao encontre um plano
		Cliente buscaCliente = buscarCliente(cliente.getCpfOuCnpj()); //Joga ExcecaoCliente caso nao encontre cliente
		Celular celular = new Celular(cliente, plano);
		celulares.add(celular);
		cliente.addCelular(celular);
		
	}
	public void excluirCelular(int numeroCelular) throws ExcecaoCelular{
		
		Celular celular = buscarCelular(numeroCelular);
		if(celular.getConta().checarPendencia()) {
			throw new ExcecaoCelular("Celular ainda tem pendencias.",celular);
		}
		 for(Cliente cliente: clientes) {
			 
			 if(celular.getCliente().getCpfOuCnpj().equals(cliente.getCpfOuCnpj())) {
				 cliente.removerCelular(celular);
			 }
		 }
		celulares.remove(celular);
	}
	public void adicionarCredito(int numeroCelular, double valor) throws ExcecaoCelular {
		
		Celular celular = buscarCelular(numeroCelular);
		if(celular.getConta().getTipo() == 'a') {
			throw new ExcecaoCelular("Não possível adicionar crédito a conta do tipo assinatura.", celular);
		}
		GregorianCalendar validade = new GregorianCalendar();
		validade.set(GregorianCalendar.DAY_OF_YEAR, validade.get(GregorianCalendar.DAY_OF_YEAR) + 180);
		
		((ContaCartao) celular.getConta()).adicionarCredito(valor,validade);
		
		
	}
	public void registrarLigacao(int numeroCelular, double duracao, GregorianCalendar dataLigacao) throws ExcecaoCelular {
		Celular celular = buscarCelular(numeroCelular);
		celular.getConta().registrarLigacao(dataLigacao, duracao);
		
	}
	public ValorData listarValorContaCredito(int numeroCelular)  throws ExcecaoCelular  {
		Celular celular = buscarCelular(numeroCelular);
		return celular.getConta().listarValorContaCredito();
		
	}
	public ArrayList<Ligacao> listarExtratoLigacoes(int numeroCelular, GregorianCalendar data) throws ExcecaoCelular{
		
		Celular celular = buscarCelular(numeroCelular);
		return celular.getConta().extratoLigacoes(data);
		
	}
	public ArrayList<Cliente> listarClientes() {
		return clientes;
	}
	public ArrayList<Plano> listarPlanos() {
		return planos;
	}
	public ArrayList<Celular> listarCelulares() {
		return this.celulares;
	}
	
	public ArrayList<Celular> informativoDeVencimento(){
		
		ArrayList<Celular> celularesVencidos = new ArrayList<Celular>();
		for(Celular cel: celulares) {
			if(cel.getConta().checarVencimento()) {
				celularesVencidos.add(cel);
			}
		}
		return celularesVencidos;
	}
	
	public void quitarConta(int numeroCelular) throws ExcecaoCelular { // Apenas para celular tipo assinatura
		
		Celular celular = buscarCelular(numeroCelular);
		if(celular.getConta().getTipo() == 'c') {
			throw new ExcecaoCelular("Celular do tipo cartao não é compatível com pedido de quitar conta.", celular);
		}
		((ContaAssinatura) celular.getConta()).quitarConta();
	}
	
	public void quitarParteConta(int numeroCelular, double valor) throws ExcecaoCelular { //Apenas para celular tipo assinatura
		
		Celular celular = buscarCelular(numeroCelular);
		if(celular.getConta().getTipo() == 'c') {
			throw new ExcecaoCelular("Celular do tipo cartao não é compatível com pedido de quitar conta.", celular);
		}
		((ContaAssinatura) celular.getConta()).quitarValorDeConta(valor);
	}
	
	public void zerarCreditoConta(int numeroCelular) throws ExcecaoCelular{
		Celular celular = buscarCelular(numeroCelular);
		if(celular.getConta().getTipo() == 'a') {
			throw new ExcecaoCelular("Celular do tipo assinatura nao compativel com pedido de zerar creditos.", celular);
		}
		((ContaCartao) celular.getConta()).zerarCredito();
	}

	public Celular buscarCelular(int numeroCelular) throws ExcecaoCelular {
		
		for(Celular cel: celulares) {
			if(cel.getNumero() == numeroCelular) {
				return cel;
			}
		}
		throw new ExcecaoCelular("Celular inexistente.");
		
	}
	public Plano buscarPlano(String nomePlano) throws ExcecaoPlano {
		
		
		for(Plano p: planos) {
			
			if(p.getNome().equals(nomePlano)) {
				return p;
			}
		}
		throw new ExcecaoPlano("Plano de nome " + nomePlano + " inexistente.");
	}
	public Cliente buscarCliente(String cpfOuCnpj) throws ExcecaoCliente{
		
		for(Cliente c: clientes) {
			
			if(c.getCpfOuCnpj().equals(cpfOuCnpj)) {
				return c;
			}
		}
		throw new ExcecaoCliente("Cliente com CPF ou CNPJ '" + cpfOuCnpj +   "' não encontrado.");
		
	}
	public void renovarDataFatura(int numeroCelular) throws ExcecaoCelular{
		
		Celular cel = buscarCelular(numeroCelular);
		if(cel.getConta().getTipo() == 'c')
			throw new ExcecaoCelular("Não se renova data de fatura de celular do tipo cartão", cel);
		((ContaAssinatura)cel.getConta()).renovarValidadeFatura();
		
	}
	
	
	
	public void escreverArquivo() {

		try {
			FileOutputStream fileoutput = new FileOutputStream(new File("DadosOperadora.txt"));
			ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);

			// Write objects to file
			objectoutput.writeObject(this);

			objectoutput.close();
			fileoutput.close();
			System.out.println("Dados de operadora salvos no arquivo DadosOperadora.txt");

		} catch (IOException excecao) {
			System.out.println("Erro ao inicializar stream.");
			System.out.println("Não foi possível salvar os dados.");
		}
	}

	public static Operadora lerArquivo() {

		try {
			FileInputStream fileinput = new FileInputStream(new File("DadosOperadora.txt"));
			ObjectInputStream objectinput = new ObjectInputStream(fileinput);

			// Read objects
			Operadora operadora = (Operadora) objectinput.readObject();

			objectinput.close();
			fileinput.close();

			if (operadora.listarCelulares().size() >= 1)
				Celular.setProximoNumeroCelular(operadora.listarCelulares().get(operadora.listarCelulares().size()-1).getNumero()+1);

			return operadora;

		} catch (FileNotFoundException excecao) {
			System.out.println("Arquivo não encontrado.");
			return operadoraPreDefinida();
		} catch (IOException excecao) {
			System.out.println("Erro ao inicializar stream");
			return operadoraPreDefinida();
		} catch (ClassNotFoundException excecao) {
			excecao.printStackTrace();
		}
		return null;
	}
	public static Operadora operadoraPreDefinida() {

		Operadora operadora = new Operadora("Tim");

		try {
			FileOutputStream fileoutput = new FileOutputStream(new File("DadosOperadora.txt"));
			ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);

			objectoutput.writeObject(operadora);

			objectoutput.close();
			fileoutput.close();
			return operadora;
		} catch (IOException excecao) {
			System.out.println("Erro ao inicializar stream");
		}
		return null;
	}
	
}
