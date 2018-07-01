package Operadora;

import java.util.GregorianCalendar;

public class ValorData {


	double valor;
	GregorianCalendar data;
	char tipo;
	
	public ValorData() {
		super();
	}
	
	public ValorData(double valor, GregorianCalendar data, char tipo) {
		super();
		this.valor = valor;
		this.data = data;
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public GregorianCalendar getData() {
		return data;
	}

	public void setData(GregorianCalendar data) {
		this.data = data;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	
	
}
