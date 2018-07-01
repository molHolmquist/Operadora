package excecoes;

import Operadora.*;

public class ExcecaoPlano extends Exception{
	
	private Plano plano;
	
	public ExcecaoPlano() {
		super("Excecao relacionado a dados de plano.");
		// TODO Auto-generated constructor stub
	}
	
	public ExcecaoPlano(String mensagem){
		super(mensagem);
	}
	
	public ExcecaoPlano(String mensagem, Plano plano){
		super(mensagem);
		this.plano = plano;
	}

	public ExcecaoPlano(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ExcecaoPlano(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ExcecaoPlano(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public Plano getPlano() {
		return plano;
	}

}
