package operadora;

import java.util.GregorianCalendar;

import excecoes.ExcecaoCelular;

public class ContaAssinatura extends ContaCelular{

	double TotalAPagar = 0;
	
	public ContaAssinatura(Plano plano, GregorianCalendar validade) {
		super(plano, validade);
	}
	
	public boolean checarPendencia() {
		if(TotalAPagar > 0) {			
			return true;
		}
		return false;
	}
	public char getTipo() {
		return 'a';
	}
	
	public void registrarLigacao( GregorianCalendar dataLigacao, double duracao) throws ExcecaoCelular {
		double custoLigacao = duracao*this.plano.getValorPorMinuto();
		TotalAPagar += custoLigacao;
		Ligacao ligacao = new Ligacao(dataLigacao, duracao);
		ligacoes.add(ligacao);
	}

	public double getTotalAPagar() {
		return this.TotalAPagar;
	}
	
	public ValorData listarValorContaCredito() {
		return new ValorData(this.TotalAPagar, this.validade,'a');
	}


}
