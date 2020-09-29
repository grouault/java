package eni.jse.tp.io;

import java.io.BufferedOutputStream;
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
		BufferedOutputStream bos = null;
		
		try{
			
			objFichier = new File(pNomFichier);
			
			//creation du fichier s il n existe pas.
			if(!objFichier.exists()){
				objFichier.createNewFile();
			}
			
			//creation du flux d'�criture vers le fichier
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(objFichier)));
			
			if(tabEntier.length>0){
				for (int value : tabEntier) {
					dos.writeInt(value);//ecriture de la valeur dans le fichier.
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
				for (int value: tabChar) {
					dos.writeChar(value);//ecriture de la valeur dans le fichier.
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("erreur d'acc�s au fichier: ");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("erreur d'entr�e sortie: ");
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
			String fichierOperande1 = buildPathFileName("operande1.dat");
			gf.stockerNombres(fichierOperande1, tabOperande1);
			
			String fichierOperateur = buildPathFileName("operateur.dat");
			char[] tabChar = {'+', '-', '*', '/', '+'}; 
			gf.stockerCar(fichierOperateur, tabChar);
			
			int[] tabOperande2 = {10, 10, 10, 10, 10};
			String fichierOperande2 = buildPathFileName("operande2.dat");
			gf.stockerNombres(fichierOperande2, tabOperande2);
			
			System.out.println("Création des fichiers avec succès!");
			

	}
	
	public static String buildPathFileName(String fileName){
		StringBuilder toReturn = new StringBuilder();
		File basedir = new File(System.getProperty("basedir", "")).getAbsoluteFile();
		toReturn.append(basedir.getPath()).append(File.separator)
			.append("files").append(File.separator)
			.append("io").append(File.separator)
			.append("eni").append(File.separator)
			.append(fileName);
		System.out.println("[buildPathFileName = ] " + toReturn.toString());
		return toReturn.toString();
		
	}
}
