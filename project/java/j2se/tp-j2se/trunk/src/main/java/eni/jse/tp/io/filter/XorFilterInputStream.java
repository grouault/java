package eni.jse.tp.io.filter;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XorFilterInputStream extends FilterInputStream {

	private int key;
	
	public XorFilterInputStream(InputStream in, int key) {
		super(in);
		this.key = key;
	}

	public int read() throws IOException{
		int v = super.read();
		return (v ^ key);
	}
	
	public int read(byte[] b) throws IOException {
		int v = super.read(b);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte)(b[i]^key);
		}
		return v;
		
	}
	
	public int read(byte[] b, int off, int len) throws IOException {
		int v = super.read(b, off, len);
		for (int i = 0; i < off + v; i++) {
			b[i] = (byte)(b[i]^key);
		}
		return v;
	}
	
	public static void main(String[] args) {
		
		InputStream in = null;
		String filePath = "d:/devs/tp-eni-jse/io/filter/message.txt";
		File objFile = new File(filePath);
		
		//le fichier de lecture doit exister...
		if (objFile.exists()){
			try {
				int key = 12;;
				in = new FileInputStream(objFile);
				XorFilterInputStream xorfis = new XorFilterInputStream(in, key);
				DataInputStream dis = new DataInputStream(xorfis);
				System.out.println("Message décodé:");
				while(true){
					char val = dis.readChar();
					System.out.print(val);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (EOFException e) {
			}
			catch (IOException e) {
				e.printStackTrace();
			} finally{
				if (in!=null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
}
