package main;

import Interface.Interface;
import Operadora.*;

public class MainExecucao {

	public static void main(String[] args) {
		Operadora op = Operadora.lerArquivo();
		if(op != null) {
			Interface in = new Interface(op);
			in.menu();
		}else {
			
			System.out.println("Nao foi possível criar operadora.");
			
		}
		
		
	}

}
