package eni.jse.tp.io;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GenerateurFic {

	/**
	 * Ecriture des donnees dans un fichier.
	 * @param pNomFichier
	 * @param tabEntier
	 * @throws IOException 
	 */
	public void stockerNombres(String pNomFichier, int[] tabEntier) {

		DataOutputStream dos = null;
		File objFichier = null;
		
		try{
			
			objFichier = new File(pNomFichier);
			
			//creation du fichier s il n existe pas.
			if(!objFichier.exists()){
				objFichier.createNewFile();
			}
			
			//creation du flux d'écriture vers le fichier
			dos = new DataOutputStream(new FileOutputStream(objFichier));
			
			if(tabEntier.length>0){
				for (int i = 0; i < tabEntier.length; i++) {
					dos.writeInt(tabEntier[i]);//ecriture de la valeur dans le fichier.
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("erreur d'accès au fichier: ");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("erreur d'entrée sortie: ");
			e.printStackTrace();
		} finally {	
			try {
				if (dos!=null){
					dos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	
	/**
	 * Ecriture des donnes dans un fichier
	 * @param pNomFichier
	 * @param tab
	 * @throws IOException 
	 */
	public void stockerCar(String pNomFichier, char[] tabChar) {
		
		OutputStream os = null;
		File objFichier = null;
		
		try{
			objFichier = new File(pNomFichier);
	
			//creation du fichier s il n existe pas.
			if(!objFichier.exists()){
				objFichier.createNewFile();
			}
			
			//creation d'un flux d'écriture vers le fichier.
			os = new FileOutputStream(objFichier);
			DataOutputStream dos = new DataOutputStream(os);
			
			if(tabChar.length>0){
				for (int i = 0; i < tabChar.length; i++) {
					dos.writeChar(tabChar[i]);//ecriture de la valeur dans le fichier.
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("erreur d'accès au fichier: ");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("erreur d'entrée sortie: ");
			e.printStackTrace();
		} finally {	
			try {
				if (os!=null){
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Pour test
	 * @param args
	 */
	public static void main(String [] args){
	
			/**
			 * test de la méthode StockerNombre
			 */
			GenerateurFic gf = new GenerateurFic();
			int[] tabOperande1 = {1, 12, 24, 25, 26};
			String fichierOperande1 = "d:/devs/tp-eni-jse/io/operande1.dat";
			gf.stockerNombres(fichierOperande1, tabOperande1);
			
			String fichierOperateur = "d:/devs/tp-eni-jse/io/operateur.dat";
			char[] tabChar = {'+', '-', '*', '/', '+'}; 
			gf.stockerCar(fichierOperateur, tabChar);
			
			int[] tabOperande2 = {10, 10, 10, 10, 10};
			String fichierOperande2 = "d:/devs/tp-eni-jse/io/operande2.dat";
			gf.stockerNombres(fichierOperande2, tabOperande2);
			
			System.out.println("Création des fichiers avec succès!");
			

	}
}
