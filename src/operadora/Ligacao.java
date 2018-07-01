package Operadora;

import java.util.GregorianCalendar;

public class Ligacao {
	

	private GregorianCalendar dataLigacao;
	private double duracaoMinutos;
	private double valorCobrado;
	
	public Ligacao() {
		super();
	}
	
	public Ligacao(GregorianCalendar dataLigacao, double duracaoMinutos, double valorCobrado) {
		super();
		this.dataLigacao = dataLigacao;
		this.duracaoMinutos = duracaoMinutos;
		this.valorCobrado = valorCobrado;
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
	public double getValorCobrado() {
		return valorCobrado;
	}
	public void setValorCobrado(double valorCobrado) {
		this.valorCobrado = valorCobrado;
	}
}
