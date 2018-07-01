package operadora;

import java.util.GregorianCalendar;

public class Ligacao {
	

	private GregorianCalendar dataLigacao;
	private double duracaoMinutos;
	
	public Ligacao(GregorianCalendar dataLigacao, double duracaoMinutos) {
		super();
		this.dataLigacao = dataLigacao;
		this.duracaoMinutos = duracaoMinutos;
	}
	public GregorianCalendar getDataLigacao() {
		return dataLigacao;
	}
	public void setDataLigacao(GregorianCalendar dataInicioLigacao) {
		this.dataLigacao = dataInicioLigacao;
	}
	public double getDuracaoMinutos() {
		return duracaoMinutos;
	}
	public void setDuracaoMinutos(double duracaoMinutos) {
		this.duracaoMinutos = duracaoMinutos;
	}
}
