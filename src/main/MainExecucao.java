package main;

import Interface.Interface;
import Operadora.*;

public class MainExecucao {

	public static void main(String[] args) {
		
		Interface in = new Interface(Operadora.readFile());
		in.menu();
	}

}
