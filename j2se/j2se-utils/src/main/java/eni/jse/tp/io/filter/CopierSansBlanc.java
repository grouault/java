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
 * Iteration sur le flux de lecture avec une boucle while
 */
public class CopierSansBlanc {

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
				int val = reader.read();
			
				while ( val != -1){
					char carActif = (char)val;
					if(Character.isWhitespace(carActif)){
						writer.write("_");
						System.out.print('_');
					}
					else{
						writer.write(val);
						System.out.print(carActif);	
					}
					val = reader.read();	
				}
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
