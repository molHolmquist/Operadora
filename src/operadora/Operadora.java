package operadora;

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
	}

	public void cadastrarCliente(String nome, String endereco, String cpfOuCnpj) throws ExcecaoCliente {

		for(Cliente c: clientes) {
			
			if(c.getCpfOuCnpj().equals(cpfOuCnpj)) {
				throw new ExcecaoCliente("Cliente já registrado.", c);
			}
		}
		
		Cliente cliente = new Cliente(nome,endereco,cpfOuCnpj);
		clientes.add(cliente);
		
	}
	
	
	public void cadastrarPlano(String nome, GregorianCalendar validade, double valorPorMinuto) throws ExcecaoPlano {
		
		for(Plano p: planos) {
			
			if(p.getNome().equals(nome)) {
				throw new ExcecaoPlano("Plano já registrado.", p);
			}
		}
		Plano plano = new Plano(nome, validade, valorPorMinuto);
		planos.add(plano);
		
	}
	
	
	public void habilitarCelularPosPago(Cliente cliente, String nomePlano, String cpfOuCnpj, GregorianCalendar vencimentoFartura) throws ExcecaoPlano {
		
		Plano plano = null;
		
		for(Plano p: planos) {
			
			if(p.getNome().equals(nomePlano)) {
				plano = new Plano(p);
				break;
			}
		}
		if(plano == null) {
			throw new ExcecaoPlano("Plano de nome " + nomePlano + " inexistente.");
		}
		//Talvez adicionar excecao de vencimento incoerente.
		Celular celular = new Celular(cliente, plano, vencimentoFartura);
		celulares.add(celular);
		
		
	}
	
	
	public void habilitarCelularPrePago(Cliente cliente, String nomePlano, String cpfOuCnpj) throws ExcecaoPlano {
		
		Plano plano = null;
		
		for(Plano p: planos) {
			
			if(p.getNome().equals(nomePlano)) {
				plano = new Plano(p);
				break;
			}
		}
		if(plano == null) {
			throw new ExcecaoPlano("Plano de nome " + nomePlano + " inexistente.");
		}
		
		Celular celular = new Celular(cliente, plano);
		celulares.add(celular);
		
	}
	public void excluirCelular(int numeroCelular) throws ExcecaoCelular{
		
		Celular celular = buscarCelular(numeroCelular);
		if(celular.getConta().checarPendencia()) {
			throw new ExcecaoCelular("Celular ainda tem pendencias.",celular);
		}
		celulares.remove(celular);
		
		
	}
	public void adicionarCredito(int numeroCelular, double valor) throws ExcecaoCelular {
		
		Celular celular = buscarCelular(numeroCelular);
		if(celular.getConta().getTipo() == 'a') {
			throw new ExcecaoCelular("Não possível adicionar crédito a conta do tipo assinatura.", celular);
		}
		GregorianCalendar validade = new GregorianCalendar();
		validade.set(GregorianCalendar.DAY_OF_YEAR, validade.get(GregorianCalendar.DAY_OF_YEAR) + 179);
		
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

	private Celular buscarCelular(int numeroCelular) throws ExcecaoCelular {
		
		for(Celular cel: celulares) {
			if(cel.getNumero() == numeroCelular) {
				return cel;
			}
		}
		throw new ExcecaoCelular("Celular inexistente.");
		
	}
	public void writeFile() {

		try {
			FileOutputStream f = new FileOutputStream(new File("DadosOperadora.dat"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(this);

			o.close();
			f.close();
			System.out.println("Alterações salvas com sucesso!\n");

		} catch (IOException e) {
			System.out.println("Error initializing stream");
			System.out.println("\nNão foi possível salvar as Alterações!\n");
		}
	}

	public static Operadora readFile() {

		try {
			FileInputStream fi = new FileInputStream(new File("OperData.dat"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			Operadora operadora = (Operadora) oi.readObject();

			oi.close();
			fi.close();
			//operadora.listarCelulares().get(operadora.listarCelulares().size()-1
			if (operadora.listarCelulares().size() >= 1)
				Celular.setProximoNumeroCelular(operadora.listarCelulares().get(operadora.listarCelulares().size()-1).getNumero());
			return operadora;

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
			if (createOperadoraObjectWithDefaultName()) {
				JOptionPane.showMessageDialog(null, "Operadora padrão criada.", "Erro ao iniciar operadora", 1);
				System.out.println("Default Operator Created. Try again!");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Boolean createOperadoraObjectWithDefaultName() {

		Operadora op1 = new Operadora("Tim");

		try {
			FileOutputStream f = new FileOutputStream(new File("OperData.dat"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(op1);

			o.close();
			f.close();
			return true;
		} catch (IOException e) {
			System.out.println("Error initializing stream");
			return false;
		}
	}
	
}
