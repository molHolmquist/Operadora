package excecoes;

import Operadora.*;

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

	public ExcecaoCelular(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ExcecaoCelular(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ExcecaoCelular(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public Celular getCelular() {
		return celular;
	}
	
}
