package Operadora;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Plano implements Serializable{
	
	private String nome;

	private Double valorPorMinuto;
	
	public Plano(String nome, Double valorPorMinuto) {
		super();
		this.nome = nome;
		this.valorPorMinuto = valorPorMinuto;
	}
	
	public Plano (Plano plano) {
		super();
		this.nome = plano.nome;
		this.valorPorMinuto = plano.valorPorMinuto;
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValorPorMinuto() {
		return valorPorMinuto;
	}
	void setValorPorMinuto(Double valorPorMinuto) {
		this.valorPorMinuto = valorPorMinuto;
	}

	
	
	
}
