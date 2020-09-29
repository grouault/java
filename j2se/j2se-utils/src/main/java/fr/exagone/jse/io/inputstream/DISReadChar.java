package fr.exagone.jse.io.inputstream;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Lecture du fichier operateur via un 
 * DataInputStream.
 * @author Gildas
 *
 */
public class DISReadChar {
	public static void main(String[] args) {
		String strFile = "d:/devs/tp-eni-jse/io/operateur.dat";
		File objFile = new File(strFile);
		try {
			InputStream is = new FileInputStream(objFile);
			DataInputStream dis = new DataInputStream(is);
			System.out.println("Lecture par caractères: ");
			for(char c=dis.readChar(); c!=-1; c=dis.readChar()){
				System.out.println((char)c);
			}			
		} catch (EOFException e) {
			System.out.println("Fin de fichier!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
