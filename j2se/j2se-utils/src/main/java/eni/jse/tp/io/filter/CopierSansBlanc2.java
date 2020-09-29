package eni.jse.tp.io.filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;


/**
 * @author Gildas
 * Permet de copier un fichier en remplaçant les espaces par des _
 * Iteration sur le flux de lecture avec une boucle for
 */
public class CopierSansBlanc2 {

	public static void main(String[] args) {
		
		String strFilePath = "d:/devs/tp-eni-jse/io/filter/copiersansblancR.txt";
		String strFilePathWriter = "d:/devs/tp-eni-jse/io/filter/copiersansblancW.txt";
		File file = new File(strFilePath);
		File fileWriter = new File(strFilePathWriter);
		
		if(file.exists()){
			Reader reader = null;
			Writer writer = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				if(!fileWriter.exists()){
					//creation du fichier de destination s'il n'existe pas.
					fileWriter.createNewFile();
				}
				writer = new BufferedWriter(new FileWriter(fileWriter));
				
				//lecture de la première valeur.
				char[] buffer = new char[512];
				int val = reader.read(buffer);
				for (int i = 0; i < val; i++) {
					if(Character.isWhitespace(buffer[i])){
						//subsitution de l'espace par un '_'
						buffer[i] = '_';
					}
				}
				
				//écriture du flux.
				writer.write(buffer);
				System.out.println("Opértion d'écriture effectuée!");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				if(reader!=null){
					try {
						reader.close();
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}		
		}
		else{
			System.out.println("Le fichier n'existe pas");
		}

		
	}
	
}
