package Operadora;

import java.io.Serializable;
import java.util.ArrayList;

import excecoes.ExcecaoCelular;

public class Cliente implements Serializable{
	private String nome;
	private String cpfOuCnpj;
	private String endereco;
	private ArrayList<Celular> listaCelulares;
	
	public Cliente(String nome, String endereco, String cpfOuCnpj){
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.cpfOuCnpj = cpfOuCnpj;
		listaCelulares = new ArrayList<Celular>();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}
	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public ArrayList<Celular> getListaCel() {
		return listaCelulares;
	}


	public void addCelular(Celular celular) {
		this.listaCelulares.add(celular);
	}
	
	public void removerCelular(Celular celular) throws ExcecaoCelular {
		if(!this.listaCelulares.remove(celular)) {
			throw new ExcecaoCelular("Cliente não possui este celular.");
		}
	}

	@Override
	public String toString() {
		return "Nome: " + nome + ", CPF ou CNPJ: " + cpfOuCnpj;
	}

}
