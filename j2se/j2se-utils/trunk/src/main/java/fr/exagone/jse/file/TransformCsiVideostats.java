package fr.exagone.jse.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TransformCsiVideostats {

	private static String SRC_FOLDER = "d:/devs/tp-eni-jse/file/csi/videostats/source";
	private static String DEST_FOLDER  = "d:/devs/tp-eni-jse/file/csi/videostats/destination/";
	
	public static void main(String[] args) {
		
		//récupératoin du répertoire source
		File cheminSource = new File(SRC_FOLDER);
		if (cheminSource!=null && cheminSource.isDirectory()) {
			//on liste les fichiers.
			File[] fichiers = cheminSource.listFiles();
			if (fichiers!=null && fichiers.length>0) {
				
				
				for (int i = 0; i < fichiers.length; i++) {

					File fichierCourant = fichiers[i];
					String destination = DEST_FOLDER + fichierCourant.getName();
					File fichierDestination = new File(destination);
					//connection aux fichiers
					BufferedReader fluxLecture = null;
					BufferedWriter fluxEcriture = null;
					try {
						fluxLecture = new BufferedReader(new FileReader(fichierCourant));
						fluxEcriture  = new BufferedWriter(new FileWriter(fichierDestination)); 
						String ligne = fluxLecture.readLine();
						while(ligne!=null){
							if(ligne.indexOf("2009-12-")!=-1 || ligne.indexOf("2010")!=-1){
								fluxEcriture.write(ligne);
								fluxEcriture.newLine();
								ligne = fluxLecture.readLine();
							}
							else{
								ligne = fluxLecture.readLine();
							}
						}
							
					} catch (IOException e) {
						System.out.println("erreur:" + e.getMessage());
						e.printStackTrace();
					} finally{
						try {
							if (fluxLecture!=null) {
								fluxLecture.close();
							}
							if (fluxEcriture!=null) {
								fluxEcriture.close();
							}
						} catch (IOException e2) {
							System.out.println("erreur:" + e2.getMessage());
							e2.printStackTrace();
						}
					}
					
					System.out.println(fichierCourant.getName());
				}
			}
		}
		
	}
	
}
