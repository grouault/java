package eni.jse.tp.io;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CalculFic {

	/**
	 * methode permettant de faire les calculs � partir des fichiers.
	 * @param pOperande1
	 * @param pOperateur
	 * @param pOperande2
	 */

	public void operationFichiers(String pOperande1, String pOperateur, String pOperande2){
				
		
		DataInputStream disOperande1= null;
		DataInputStream disOperande2 = null;
		DataInputStream disOperateur = null;
		
		File fileOperande1 = new File(pOperande1);
		File fileOperande2 = new File(pOperande2);
		File fileOperateur = new File(pOperateur);
		
		if(fileOperande1.exists() && fileOperande2.exists() && fileOperateur.exists()){
			try {
				
//				filtre
				disOperande1 = new DataInputStream(new FileInputStream(fileOperande1));
				disOperande2 = new DataInputStream(new FileInputStream(fileOperande2));
				disOperateur = new DataInputStream(new FileInputStream(fileOperateur));
				
//				lecture en parallèle
				while (true){
//					lecture de l'op�rateur
					char operateur  = disOperateur.readChar();
//					lecture de l'op�rande1
					int operande1 = disOperande1.readInt();
//					lecture de l'op�rande
					int operande2 = disOperande2.readInt();
					
					float res;
					switch (operateur) {
					case '+':
						res = operande1 + operande2;
						break;
					case '-':
						res = operande1 - operande2;
						break;
					case '*':
						res = operande1 * operande2;
						break;
					case '/':
						res  = (float)operande1/(float)operande2;
						break;
					default:
						res = 0;
						break;
					}
					//afficahge des resultats
					System.out.printf("%d\t%c\t%d\t=\t%.3f\n", operande1, operateur, operande2, res);
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EOFException e){
				// Traitement normal - Fin de fichier.
				System.out.println("Fin de fichier");
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					disOperande1.close();
					disOperande2.close();
					disOperateur.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		} else {
			System.out.println("Les fichiers n'existent pas.");
		}

	}
	
	/**
	 * pour test
	 * @param args
	 */
	public static void main(String[] args){
		
		String strFileOperande1 = GenerateurFic.buildPathFileName("operande1.dat") ;
		String strFileOperande2 = GenerateurFic.buildPathFileName("operande2.dat");
		String strFileOperateur = GenerateurFic.buildPathFileName("operateur.dat");
		
		CalculFic objCalculFic = new CalculFic();
		System.out.println("Debut");
		objCalculFic.operationFichiers(strFileOperande1, strFileOperateur, strFileOperande2);
		System.out.println("fin");
		
	}
	
}
