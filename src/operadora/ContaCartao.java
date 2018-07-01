package Operadora;

import java.util.Date;
import java.util.GregorianCalendar;

import excecoes.ExcecaoCelular;

public class ContaCartao extends ContaCelular {
	
	double credito;

	public ContaCartao(Plano plano) {
		super(plano);
	}
	
	public boolean checarPendencia() {
		if(credito > 0) {			
			return true;
		}
		return false;
	}
	public char getTipo() {
		return 'c';
	}
	public void adicionarCredito(double creditoAdicionado, GregorianCalendar validade) {
		this.credito += creditoAdicionado;
		this.validade = validade;
	}
	public void registrarLigacao(GregorianCalendar dataLigacao, double duracao) throws ExcecaoCelular {
		
		double custoLigacao = duracao*this.plano.getValorPorMinuto();
		
		if(credito - custoLigacao < 0) {
			throw new ExcecaoCelular("Creditos insuficientes para ligação.");
		}else if(this.validade.getTime().getTime() < new Date().getTime()){
			throw new ExcecaoCelular("Data de validade dos creditos atingida.");
		}

		credito -= custoLigacao;
		Ligacao ligacao = new Ligacao(dataLigacao, duracao, custoLigacao);
		this.ligacoes.add(ligacao);
	}
	public ValorData listarValorContaCredito() {
		return new ValorData(this.credito, this.validade, 'c');
	}
	public void zerarCredito() {
		this.credito = 0;
	}
	

}
