package Operadora;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import excecoes.ExcecaoCelular;

public class ContaCelular implements Serializable{ // Associado a um e apenas um celular
	
	protected ArrayList<Ligacao> ligacoes;
	protected GregorianCalendar validade;
	protected Plano plano;
	protected static char tipo;
	
	ContaCelular(Plano plano){
		this.plano = plano;
		ligacoes = new ArrayList<Ligacao>();
	}
	
	ContaCelular(Plano plano, GregorianCalendar validade){
		this.plano = plano;
		this.validade = validade;
		ligacoes = new ArrayList<Ligacao>();
	}
	
	public boolean checarPendencia() {
		return false;
	}
	public char getTipo() {
		return 'n';
	}
	
	public void registrarLigacao(GregorianCalendar dataLigacao, double duracao) throws ExcecaoCelular {
		Ligacao ligacao = new Ligacao();
		ligacoes.add(ligacao);
	}
	
	public ArrayList<Ligacao> extratoLigacoes(GregorianCalendar data){
		
		ArrayList<Ligacao> lista_ligacoes= new ArrayList<Ligacao>();
		for(Ligacao ligacao : ligacoes) {
			
			if(data.getTimeInMillis() < ligacao.getDataLigacao().getTimeInMillis()) {
				lista_ligacoes.add(ligacao);
			}
		}
		return lista_ligacoes;
	}
	
	public ValorData listarValorContaCredito() {
		return new ValorData();
	}

	public GregorianCalendar getValidade() {
		return validade;
	}

	public void setValidade(GregorianCalendar validade) {
		this.validade = validade;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}
	public boolean checarVencimento() { //Retorna true se vencido
		
		GregorianCalendar data = new GregorianCalendar();
		if(data.getTimeInMillis() < validade.getTimeInMillis()) {
			return true;
		}
		return false;
	}

}
