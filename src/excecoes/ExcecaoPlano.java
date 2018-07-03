package excecoes;

import Operadora.*;

@SuppressWarnings("serial")
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

	public Plano getPlano() {
		return plano;
	}

}
