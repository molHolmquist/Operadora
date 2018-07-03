package excecoes;

import Operadora.*;

@SuppressWarnings("serial")
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

	public Cliente getCliente() {
		return cliente;
	}
	


}
