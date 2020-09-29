package eni.jse.tp.io.filter;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;


/**
 * @author Gildas
 * Avec Buffer
 */
public class NouvelleConsole2 {
	
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
		BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(tab));
		int c;
		int j = 1;
		System.out.print(j + ":");
		try {
			while ((c=bis.read())!=-1){
				if('\n'==c){
					if(bis.available()>0){
						j++;
						System.out.print(j + ":");
					}
				}
				else{
					System.out.print((char)c);
				}	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		
		NouvelleConsole2 console = new NouvelleConsole2();
		System.out.println("Initialiser nouvelle console:");
		console.demarrer();
		System.out.println("Test console");
		System.out.println("Test console 2");
		System.out.println("Test console 3");
		console.arreter();
			

	}
}
