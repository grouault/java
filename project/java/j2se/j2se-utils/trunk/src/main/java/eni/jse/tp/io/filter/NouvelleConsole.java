package eni.jse.tp.io.filter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class NouvelleConsole {
	
	private PrintStream console = null;
	private ByteArrayOutputStream nconsole = null;
	
	public void demarrer(){
		console = System.out;
		nconsole = new ByteArrayOutputStream();
		System.setOut(new PrintStream(nconsole));
	}
	
	public void arreter(){
		System.setOut(console);
		byte[] tab = nconsole.toByteArray();
		int j = 1;
		System.out.print(j + ":");
		for (int i = 0; i < tab.length; i++) {
			if('\n'==((char)tab[i])){
				j++;
				System.out.print(j + ":");
			}
			else{
				System.out.print((char)tab[i]);
			}
		}
		
	}

	public static void main(String[] args) {
		
		NouvelleConsole console = new NouvelleConsole();
		System.out.println("Initialiser nouvelle console:");
		console.demarrer();
		System.out.println("Test console");
		System.out.println("Test console 2");
		System.out.println("Test console 3");
		console.arreter();
			

	}
}
