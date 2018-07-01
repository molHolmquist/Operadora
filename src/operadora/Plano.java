package operadora;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Plano implements Serializable{
	
	private String nome;

	private Double valorPorMinuto;
	
	public Plano(String nome, GregorianCalendar validade, Double valorPorMinuto) {
		super();
		this.nome = nome;
		this.valorPorMinuto = valorPorMinuto;
	}
	
	public Plano (Plano plano) {
		super();
		this.nome = plano.nome;
		this.valorPorMinuto = plano.valorPorMinuto;
		
	}
	
	String getNome() {
		return nome;
	}
	void setNome(String nome) {
		this.nome = nome;
	}
	Double getValorPorMinuto() {
		return valorPorMinuto;
	}
	void setValorPorMinuto(Double valorPorMinuto) {
		this.valorPorMinuto = valorPorMinuto;
	}

	
	
	
}
