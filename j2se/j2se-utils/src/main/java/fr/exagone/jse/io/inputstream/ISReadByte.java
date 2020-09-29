package fr.exagone.jse.io.inputstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Lecture d'un fichier operateur (lecture binaire) dans un tableau d'octet.
 * Pour obtenir la taille du tableau d'octets, on passe par l'objet File qui
 * permet d'obtenir la taille du fichier (length)
 * 
 * @author Gildas
 * 
 */
public class ISReadByte {

	public static void main(String[] args) {
		String strFile = "d:/devs/tp-eni-jse/io/operateur.dat";

		try {
			File objFile = new File(strFile);

			// lecture du tableau de byte
			InputStream is = new FileInputStream(objFile);
			byte[] buffer = new byte[(int) objFile.length()];
			is.read(buffer);
			System.out.println("Lecture du tableau de byte: ");
			for (int i = 0; i < buffer.length; i++) {
				byte b = (byte) buffer[i];
				if (b != 0) {
					System.out.print(b + ": ");
					System.out.println((char) b);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
