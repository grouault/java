package fr.exagone.jse.io.inputstream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Lecture du fichier operande via un BufferInputStream. Ici, on a pas besoin de
 * passer par l'objet file. La méthode du buffer available, nous donne la taille
 * du buffer en nombre d'octets pour récupérer les données du fichier.
 * 
 * @author Gildas
 * 
 */
public class BufferedIS {
	public static void main(String[] args) {
		String strFile = "d:/devs/tp-eni-jse/io/test.txt";
		try {
			InputStream is = new FileInputStream(strFile);
			BufferedInputStream bis = new BufferedInputStream(is);
			int nByte = bis.available();

			// lecture du flots d'octets.
			byte[] buffer = new byte[nByte];
			is.read(buffer);

			// lecture du tableau de byte
			System.out.println("Lecture du flots d'octets: ");
			for (int i = 0; i < buffer.length; i++) {
				byte b = (byte) buffer[i];
				System.out.print(b + ": ");
				System.out.println((char)b);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
