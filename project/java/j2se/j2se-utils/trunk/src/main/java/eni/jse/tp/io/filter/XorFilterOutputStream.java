package eni.jse.tp.io.filter;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Gildas
 *
 */
public class XorFilterOutputStream extends FilterOutputStream {

	private int keyCrypt; //cl� de cryptage
	
	public XorFilterOutputStream(OutputStream out, int key) {
		super(out);
		keyCrypt = key;
	}
	
	
	/**
	 * Cryptage des donn�es
	 */
	public void write(int v) throws IOException{
		v = v ^ keyCrypt;
		super.write(v);
	}

	/**
	 * Surcharge de la m�thode 
	 * write(byte[] b, int off,int len)
	 * pour cryptage des donn�es.
	 */
	public void write(byte[] b, int off,int len) throws IOException{
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte)(b[i]^keyCrypt);
		}
		super.write(b, off, len);
	}
	
	
	/**
	 * Surcharge de la m�thode
	 * write(byte[] b)
	 * pour le cryptage des donn�es.
	 */
	public void write(byte[] b) throws IOException{
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte)(b[i]^keyCrypt);
		}
		super.write(b);
	}
	
	/**
	 * Pour test
	 * @param args
	 */
	public static void main(String[] args) {
		
		OutputStream os = null;
		String filePath = "d:/devs/tp-eni-jse/io/filter/message.txt";
		File objFile = new File(filePath);
		try {
			if (!objFile.exists()) {
				objFile.createNewFile();
			}
			
			//initialisation du flux d'�criture
			os = new FileOutputStream(objFile);
			//cl� de cryptage: key
			int key = 12;
			
			XorFilterOutputStream xorfos= new XorFilterOutputStream(os, key); 
			DataOutputStream dos = new DataOutputStream(xorfos);
			System.out.println("Message encod�:");
			for (char c = 'A'; c < 'Z'; c++) {
				System.out.print(c);
				dos.writeChar(c);
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		
	}
}
