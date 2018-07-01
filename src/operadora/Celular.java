package Operadora;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Celular implements Serializable{

	static int proximoNumeroCelular = 999000001;
	
	private int numero;
	private Cliente cliente;
	private ContaCelular conta;
	
	
	public Celular(Cliente cliente, Plano plano, GregorianCalendar validade) { // Assinatura
		super();
		this.numero = proximoNumeroCelular;
		this.cliente = cliente;
		this.conta = new ContaAssinatura(plano,validade);
		proximoNumeroCelular++;
	}
	public Celular(Cliente cliente, Plano plano) { // Cartao
		super();
		this.numero = proximoNumeroCelular;
		this.cliente = cliente;
		this.conta = new ContaCartao(plano);
		proximoNumeroCelular++;
	}
	
	
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ContaCelular getConta() {
		return conta;
	}
	public void setConta(ContaCelular conta) {
		this.conta = conta;
	}
	public static void setProximoNumeroCelular(int proximoNumeroCelular) {
		Celular.proximoNumeroCelular = proximoNumeroCelular;
	}

	
	
}
