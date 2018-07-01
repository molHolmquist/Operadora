package operadora;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable{
	private String nome;
	private String cpfOuCnpj;
	private String endereco;
	private ArrayList<Celular> listaCelulares;
	
	Cliente(String nome, String endereco, String cpfOuCnpj){
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.cpfOuCnpj = cpfOuCnpj;
	}
	
	String getNome() {
		return nome;
	}
	void setNome(String nome) {
		this.nome = nome;
	}
	String getCpfOuCnpj() {
		return cpfOuCnpj;
	}
	void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}
	String getEndereco() {
		return endereco;
	}

	void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	ArrayList<Celular> getListaCel() {
		return listaCelulares;
	}


	void setListaCel(ArrayList<Celular> listaCelulares) {
		this.listaCelulares = listaCelulares;
	}

	@Override
	public String toString() {
		return "Cliente [nome= " + nome + ", cpfOuCnpj= " + cpfOuCnpj;
	}

}
