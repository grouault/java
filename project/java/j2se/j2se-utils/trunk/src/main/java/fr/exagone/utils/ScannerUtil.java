package fr.exagone.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class ScannerUtil {

	private final static String PATH_FILE = "d:/devs/workspaces/helios/content-server/util-project/files-resources/";
	private final static String CSV_FILE = "name.csv";
	private static Logger log = Logger.getLogger(ScannerUtil.class.getName());
	
	public static void main(String[] args) {
	}
	
	/**
	 * permet de parser un fichier CSV.
	 */
	public static String parseCsvFile() {
		File fileCSV = new File(PATH_FILE + CSV_FILE);
		log.debug("file - name : " + fileCSV);
		Scanner scFile = null;
		StringBuffer names = new StringBuffer();
		try {
			scFile = new Scanner(fileCSV);
			String currentLine;
			int cptLine = 0;
			while (scFile.hasNextLine()) {
				currentLine = scFile.nextLine();  // recuperation de la ligne courante du fichier.
				cptLine ++;
				if (cptLine == 1)
				  continue; // on ne tient pas compte de la premiere ligne.
				scanLineName(names, currentLine); // permet de recuperer dans le buffer, le nom de la personne dans la ligne.
			} 
		} catch (FileNotFoundException e) {
			log.error("[Error] - No file : " + e.getMessage());
		} finally {
			if (scFile != null) 
				scFile.close();
		}
		log.info("[names] = " + names.toString());
		return names.toString();
	}

	
	/**
	 * permet de recuperer le nom de la personne
	 * 	 en scannant une ligne.
	 * @param names
	 * @param line
	 */
	private static void scanLineName(StringBuffer names, final String line) {
		String currentValue;
		int cptWord = 0;
		Scanner scLine = null;
		try {
			scLine = new Scanner(line).useDelimiter("#");
			while (scLine.hasNext()) {
				currentValue = scLine.next(); cptWord ++;
				if (cptWord != 1) // recupere le noame.
					continue;
				names.append(currentValue).append(" - ");
			}			
		} finally {
			if (scLine != null) 
				scLine.close();
		}
	}
	
}
