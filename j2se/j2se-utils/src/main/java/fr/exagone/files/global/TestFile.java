package fr.exagone.files.global;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class TestFile {

	private static String FILE_TEST = "C:/Temp/devs/denise.jpg";
	private static String FILE_SOURCE = "/home/rouault/informatique/workspaces/indigo/J2SE/files/default/default-projet/cfg/resources/files/SrcFile.txt";
	private static String FIle_DEST = "/home/rouault/informatique/workspaces/indigo/J2SE/files/default/default-projet/cfg/resources/files/archive/SrcFile.txt";
	
	/**
	 * main.
	 * @param args
	 */
	public static void main(String[] args) {
//	  TestFile.renameTo(FILE_SOURCE, FIle_DEST);	
	  TestFile.transformToUrl();
	}
	
	public static void transformToUrl() {
		try {
			File srcFile = new File(FILE_TEST);
			System.out.println("srcFile = " + srcFile);
			
			URI uri = srcFile.toURI();
			System.out.println("uri = " + uri);
			
			URL url = srcFile.toURL();
			System.out.println("url = " + url);
			
			File newFile = new File(uri);
			System.out.println("NewFile = " + newFile);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * permet de renommer un fichier.
	 * @param pathSource
	 * @param pathDest
	 */
	public static void renameTo (final String pathSource, final String pathDest) {
	  File srcFile = new File(pathSource);
	  File srcDest = new File(pathDest);
	  System.out.println("FileExist = " + srcFile.exists());
	  boolean bRename = srcFile.renameTo(srcDest);
	  System.out.println("rename = " + bRename);
	  
	}
	
}
