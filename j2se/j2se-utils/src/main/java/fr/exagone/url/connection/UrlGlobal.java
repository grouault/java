package fr.exagone.url.connection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UrlGlobal {

	private static Log logger = LogFactory.getLog(UrlGlobal.class);
	
	private static String FOLDER_STOCKAGE = "c:/temp/devs/";
	private static String TYPE_MIME_IMAGE = "image/";
	private static Integer POID_IMAGE = 2000000;
	
	
	private static String TEST_URL = "http://www.test.gildas.fr/";
	private static String VOILA_URL_IMAGE = "http://static.voila.fr/Images/blocs/Magic/140544.jpg";
	
	private static String WEBDEV_URL = "http://webdev.citepro.cite-sciences.fr:8888/tests/estim/test-referer-carrousel-ressource.html";
	
	public static void main(String[] args) {
		
//		TEST de validité d'une URL
//		System.out.println("IsValidUrl = " + isValidUrl(TEST_URL));
//		System.out.println("IsValidUrl = " + isValidUrl(VOILA_URL_IMAGE));
		
		URL urlWebdev = getUrlFromString(WEBDEV_URL);
		System.out.println(urlWebdev);
		/*
		if (isValidUrl(VOILA_URL_IMAGE)) {
			if (checkCriteresImage(VOILA_URL_IMAGE, POID_IMAGE, TYPE_MIME_IMAGE)) {
				File file = getFileImageByUrl(VOILA_URL_IMAGE, FOLDER_STOCKAGE);
				System.out.println("File = " + file.getName());				
			}
		}
		*/
		
		/*
		InputStream insImage = getInsImageByUrl(VOILA_URL_IMAGE);
		if (insImage != null) {
			System.out.println("insImage = " + insImage);
			try {
				insImage.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/
		
		
	}
	

	/*  
	 *  Recuperation du referer : ==> propriété 'referer dans le Header'
	 *  Recuperation du REMOTE HOST
	 * 		//recuperation du RemoteHost
		// Definition d'une IP par defaut
		String strHostName = "IPInconnue";
		try {
			strHostName=java.net.InetAddress.getByName(request.getHeader("X-Forwarded-For")).getHostName(); 
		}catch (java.net.UnknownHostException e){
%>
			<ics:logmsg msg='<%="Videotheque: Erreur lors de la rsolution de "+request.getHeader("X-Forwarded-For")%>'/>
<%
		}
	 * 
	 */
	
	private static URL getUrlFromString (String urlTest) {
		URL url = null;
		try {
			url = new URL(urlTest);
			System.out.println("url = " + url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	/**
	 * Permet de verifier la validite d'une url.
	 * @param urlTest
	 * @return
	 */
	private static boolean isValidUrl (final String urlTest) {
		
		boolean isValidUrl = false;
		try {
			URLConnection conn = new URL(urlTest).openConnection();
			conn.setConnectTimeout(2000);
			conn.setReadTimeout(3000);
			conn.connect();
			isValidUrl = true;
		} catch (MalformedURLException e) {
			isValidUrl = false;
		} catch (IOException e) {
			isValidUrl = false;
		}
		return isValidUrl;
		
	}
	
	
	/**
	 * Permet de verifier la validite d'une image 
	 * 	- selon son poid.
	 *  - son type mime.
	 * @param urlTest
	 * @param poidImage
	 * @param mimeTypeImage
	 * @return
	 */
	private static boolean checkCriteresImage (final String urlTest, 
			final Integer poidImage, final String mimeTypeImage) {
		boolean isCriteresImageValid = false;
		try {
			URLConnection conn = new URL(urlTest).openConnection();
			conn.setConnectTimeout(2000);
			conn.setReadTimeout(3000);
			conn.connect();
			if (conn.getContentLength() > poidImage) {
				isCriteresImageValid = false; // poid de l image trop fort.
			} else if (!conn.getContentType().contains(mimeTypeImage)) {
				isCriteresImageValid = false; // type MIME incorrect				
			} else {
				isCriteresImageValid = true;
			}
		} catch (MalformedURLException e) {
			isCriteresImageValid = false;
		} catch (IOException e) {
			isCriteresImageValid = false;
		}
		return isCriteresImageValid;
	}
	
	
	private static InputStream getInsImageByUrl (final String urlImage) {
		
		InputStream insImage = null;
		try {
			URLConnection conn = new URL(urlImage).openConnection();
			conn.setConnectTimeout(2000);
			conn.setReadTimeout(3000);
			insImage = conn.getInputStream(); // recuperation du flux.
			
		} catch (MalformedURLException e) {
			logger.error("[getFileImageByUrl] - Erreur : " + e.getMessage());
			e.printStackTrace();
			
		} catch (IOException e) {
			logger.error("[getFileImageByUrl] - Erreur : " + e.getMessage());
			e.printStackTrace();
		}
		return insImage;
	}
	
	/**
	 * Recuperation du fichier image.
	 * @param urlImage
	 * @return
	 */
	private static File getFileImageByUrl (final String urlImage, 
			final String folder) {
		
		File fileImage = null;
		
		FileOutputStream fos = null; // flux d'ecriture.
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null; 
		InputStream insImage = null; // flux de lecture de l'image http://
		InputStream ins = null; // flux de lecture de l'image resizer.
		
	    String imgExt 		= urlImage.substring(urlImage.lastIndexOf("/") + 1);
		String imageName 	= urlImage.substring(urlImage.lastIndexOf("/") + 1, urlImage.lastIndexOf("."));
		String extImg 		= urlImage.substring(urlImage.lastIndexOf(".") + 1, urlImage.length());
		
		String imageDbName = imageName + "." + extImg;
		
		try {
			
			URLConnection conn = new URL(urlImage).openConnection();
			conn.setConnectTimeout(2000);
			conn.setReadTimeout(3000);
			
			insImage = conn.getInputStream(); // recuperation du flux.
			//
			// stockage de l'image sur le serveur.
			//
			int imageSize = conn.getContentLength();
			bis = new BufferedInputStream(insImage);
			
			fileImage = new File(folder + imageDbName);
			fos = new FileOutputStream(fileImage);
			bos = new BufferedOutputStream(fos);
			
			byte[] imgByte = new byte[imageSize];
			int readCount;
			while ( (readCount = bis.read(imgByte)) > 0) {
			  bos.write(imgByte, 0, readCount);	
			}
			bos.flush();
			

		} catch (MalformedURLException e) {
			logger.error("[getFileImageByUrl] - Erreur : " + e.getMessage());
			e.printStackTrace();
			
		} catch (IOException e) {
			logger.error("[getFileImageByUrl] - Erreur : " + e.getMessage());
			e.printStackTrace();
		}
		
		return fileImage;
	}
	
}
