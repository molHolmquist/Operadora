package excecoes;

import Operadora.*;

@SuppressWarnings("serial")
public class ExcecaoCelular extends Exception{

	private Celular celular;
	
	public ExcecaoCelular() {
		super("Erro relacionado a dados de celular.");
		// TODO Auto-generated constructor stub
	}
	
	public ExcecaoCelular(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public ExcecaoCelular(String message, Celular celular) {
		super(message);
		this.celular = celular;
	}

	public Celular getCelular() {
		return celular;
	}
	
}
