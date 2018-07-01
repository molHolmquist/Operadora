package excecoes;

import operadora.Cliente;

public class ExcecaoCliente extends Exception {
	
	private Cliente cliente;

	public ExcecaoCliente() {
		super("Excecao relacionado a dados de cliente.");
		// TODO Auto-generated constructor stub
	}
	
	public ExcecaoCliente(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public ExcecaoCliente(String message, Cliente cliente) {
		super(message);
		this.cliente = cliente;
	}

	public ExcecaoCliente(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ExcecaoCliente(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}



	public ExcecaoCliente(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	


}
