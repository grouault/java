package eni.jse.tp.io.filter;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FilterOuputStream {
	
	public static void main(String[] args) {
		
		String filePath = "d:/devs/tp-eni-jse/io/io.txt";
		InputStream in = null;
		try {
			in = new BufferedInputStream (new FileInputStream(filePath));
			for (int i = in.read(); i != -1; i=in.read()) {
				System.out.println(i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
