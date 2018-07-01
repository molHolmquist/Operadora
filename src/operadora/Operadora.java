package operadora;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import excecoes.ExcecaoPlano;
import excecoes.ExcecaoCelular;
import excecoes.ExcecaoCliente;

public class Operadora {
	

	private ArrayList<Cliente> clientes;
	private ArrayList<Celular> celulares;
	private ArrayList<Plano> planos;
	
	public Operadora(ArrayList<Cliente> clientes, ArrayList<Celular> celulares, ArrayList<Plano> planos) {
		super();
		this.clientes = clientes;
		this.celulares = celulares;
		this.planos = planos;
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
	public ArrayList<Ligacao> listarExtratoLig(int numeroCelular, GregorianCalendar data) throws ExcecaoCelular{
		
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
		return celulares;
	}

	private Celular buscarCelular(int numeroCelular) throws ExcecaoCelular {
		
		for(Celular cel: celulares) {
			if(cel.getNumero() == numeroCelular) {
				return cel;
			}
		}
		throw new ExcecaoCelular("Celular inexistente.");
		
	}
}
