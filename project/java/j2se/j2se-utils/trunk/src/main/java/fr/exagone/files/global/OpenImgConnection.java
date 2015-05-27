package fr.exagone.files.global;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class OpenImgConnection {
	
//	public static final String URL_IMAGE = "http://sports.voila.fr/images/201206/nadal_eau_guignols.jpg";
//	public static final String URL_IMAGE = "http://test.fr/titi/140544.jpg";
	public static final String URL_IMAGE = "http://static.voila.fr/Images/blocs/Magic/140544.jpg";
	private static String FIle_DEST = "c:/temp/devs/test.jpg";
	
	public static void main(String[] args) {
		
		String urlImage = URL_IMAGE;
		FileOutputStream fos = null;
		try {
		  
		  URLConnection conn = new URL(urlImage).openConnection();
		  int[] paramsConn = getTimeOutConnexionParam();
		  conn.setConnectTimeout(paramsConn[0]);
		  conn.setReadTimeout(paramsConn[1]);
		   		  
		  // recuperation du type de l'image.
		  String typeImage = conn.getContentType();
		  // recuperation de la longueur de l'image.
		  int imgSize = conn.getContentLength();
		 
		  System.out.println("Content-type = " + typeImage);
		  System.out.println("Content-length = " + imgSize);
		  
		  Map mapHeader = conn.getHeaderFields();
		  
		  // recuperation de l'image.
		  URL url = conn.getURL();
		  URI uri = conn.getURL().toURI();
		  File newFile = new File(uri);
		  System.out.println("url = " + url);
		  System.out.println("uri = " + uri);
		  System.out.println("newFile = " + newFile);
		  InputStream is = conn.getInputStream();
		  Thread.sleep(100);
		  byte[] imgByte = new byte[imgSize];
		  is.read(imgByte);

		  fos = new FileOutputStream(FIle_DEST);
		  fos.write(imgByte);
		  fos.flush();
		  // image recupere.
		  System.out.println("image recuperee!");
			
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException : " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException : " + e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	/**
	 * permet de recuperer les parametres de connexions aux images.
	 * @return tableau
	 *  - index 0 : time-out de connexion.
	 *  - index 1 : time-out de lecture. 
	 */
	private static int[] getTimeOutConnexionParam() {
		int connectTimeOut = 1000;
		int readTimeOut = 3000;
		int[] params = {connectTimeOut, readTimeOut};
		return params;
	}
	

}
