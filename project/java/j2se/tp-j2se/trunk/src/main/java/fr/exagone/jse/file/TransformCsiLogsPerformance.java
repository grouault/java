package fr.exagone.jse.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TransformCsiLogsPerformance {

	private static final String FILE_TO_ANALYSE = "HomeUniverscience.txt";
	private static final String FILE_RESULT = "newFile.csv";
	private static final String FILE_CONSOLIDATION = "fileConsolidation.csv"; 
	
	//private static String PATH_SRC_FILE = "d:/Services/application-cs/projets/site-web/projets/Performances/Travaux/Logs-Webdev/Unique/";
	//private static String PATH_NEW_FILE  = "d:/Services/application-cs/projets/site-web/projets/Performances/Travaux/Logs-Webdev/Unique/";	
	
	private static String PATH_SRC_FILE = "d:/Temp/java/EdudeDePerf/src/";
	private static String PATH_NEW_FILE  = "d:/Temp/java/EdudeDePerf/dest/";
	
	private static final String EXECUTE_PAGE = "Execute page";
	private static final String EXECUTE_ELEMENT = "Executed element";
	private static final String EXECUTE_QUERY = "Executed query";
	private static final String EXECUTE_CSI = "Execute csi";
	
	private static final String LOG_CSI = "log.csi.cs.time";
	
	private static final String IN = "in";
	private static final String SECONDS = "Seconds";
	private static final String  MILLISECOND = "ms";
	
	public static void main(String[] args) {
		
		System.out.println("-- Début du traitement --");
		File fileSource = new File(PATH_SRC_FILE + FILE_TO_ANALYSE);
		File newFile = new File(PATH_NEW_FILE + FILE_RESULT);
		File fileConsolidation = new File(PATH_NEW_FILE + FILE_CONSOLIDATION);
		//test de la présence d'un fichier que l'on peut analyser.
		if(fileSource.exists() && fileSource.isFile() && fileSource.length()>0){
			
			BufferedWriter fluxEcriture = null;
			BufferedReader fluxLecture = null;
			try {
				
				fluxEcriture = new BufferedWriter(new FileWriter(newFile));
				fluxLecture = new BufferedReader(new FileReader(fileSource));
				
				//Map contenant pour chaque élémént la liste de ses temps d'éxéctution.
				Map<String, List<Double>> mapTimeParElement = new HashMap<String, List<Double>>();
				
				//parcours du fichier de logs.
				String ligne = fluxLecture.readLine();
				while(ligne!=null){
					if(ligne.indexOf("Execute")!=-1){
						
						StringBuffer newLine = null;
						
						//
						//EXECUTE_PAGE
						//
						if(ligne.contains(EXECUTE_PAGE)){

							int indexExecute = ligne.indexOf(EXECUTE_PAGE) + EXECUTE_PAGE.length() + 1;
							int indexFinElement = ligne.lastIndexOf("Hours");
							int indexLast = ligne.length();
							
							//Creation de la nouvelle ligne a inserer.
							newLine = new StringBuffer();
							newLine.append("Execute page#");
							
							//recuperation du nom de l element.
							String elementName = ligne.substring(indexExecute, indexFinElement-1);
							Double elementTimeDuration = null;
							
							//time est au format suivant: Hours: 0 Minutes: 0 Seconds: 0:016
							String time = ligne.substring(indexFinElement, indexLast);		
							//traitement des secondes
							if(time.indexOf("Seconds")!=-1){
								double nSecond = 0;
								String seconde = time.substring(time.indexOf("Seconds") + SECONDS.length() + 1, time.length() );
								String[] tabSecond = seconde.split(":");
								if(tabSecond.length>0){
									nSecond = Double.parseDouble(tabSecond[0]) + (Double.parseDouble(tabSecond[1])/1000);
									newLine.append(nSecond*1000).append("#");//pour mettre le résultat en ms.
									elementTimeDuration = (nSecond*1000);
								}
							}
							else{
								newLine.append("no time#");
							}
							
							//ajout de l'élément dans la map
							/*
							if(elementTimeDuration!=null && elementName!=null){
								addMap(mapTimeParElement, elementName, elementTimeDuration);
							}
							*/
							
							//ajout du nom de l'élément
							newLine.append(elementName);
							
							fluxEcriture.write(newLine.toString());
							fluxEcriture.newLine();							
						}
						
						//
						//EXECUTE_ELEMENT
						//
						if(ligne.contains(EXECUTE_ELEMENT)){
							
							int indexExecute = ligne.indexOf(EXECUTE_ELEMENT) + EXECUTE_ELEMENT.length() + 1;
							int indexFinElement = ligne.lastIndexOf("in");
							int indexLast = ligne.length();
							
							//creation de la nouvelle ligne a inserer.
							newLine = new StringBuffer();
							newLine.append("Execute element#");
							
							String elementName = ligne.substring(indexExecute, indexFinElement-1);
							Double elementTimeDuration = null;
							
							//recuperation du time.
							String timeStr = ligne.substring(indexFinElement + IN.length() + 1, indexLast);
							elementTimeDuration = Double.parseDouble(timeStr.substring(0,timeStr.length()-3));
							newLine.append(elementTimeDuration).append("#");
							
							//recuperation du nom de l element.
							newLine.append(elementName);
							
							//ajout de l'élément dans la map
							if(elementTimeDuration!=null && elementName!=null){
								addMap(mapTimeParElement, elementName, elementTimeDuration);
							}							
							
							fluxEcriture.write(newLine.toString());
							fluxEcriture.newLine();
							
						}
						
						//
						//EXECUTE_QUERY
						//
						if(ligne.contains(EXECUTE_QUERY)){
							
							if(ligne.endsWith(MILLISECOND)){
							
								int indexExecute = ligne.indexOf(EXECUTE_QUERY) + EXECUTE_QUERY.length() + 1;
								int indexLast = ligne.length();							
								int indexFinElement = ligne.lastIndexOf("in");
								
								//creation de la nouvelle ligne a inserer.
								newLine = new StringBuffer();
								newLine.append("Execute query#");	
								
								//recuperation du time.
								String timeStr = ligne.substring(indexFinElement + IN.length() + 1, indexLast);
								double dTime = Double.parseDouble(timeStr.substring(0,timeStr.length()-2));
								newLine.append(dTime).append("#");
								
								//recuperation du nom de l element.
								String element = ligne.substring(indexExecute, indexFinElement-1);
								newLine.append(element);							
								
								fluxEcriture.write(newLine.toString());
								fluxEcriture.write(ligne);
								fluxEcriture.newLine();	
							}
		
						}
						
						//
						//EXECTUE_CSI
						//
						if(ligne.contains(LOG_CSI)){
							String executeCSI = ligne.substring(ligne.indexOf(EXECUTE_CSI), ligne.length());
							fluxEcriture.write(executeCSI);
							fluxEcriture.newLine();	
						}
						
						//on passe à la ligne suivante.
						ligne = fluxLecture.readLine();
						
					}
					else{
						ligne = fluxLecture.readLine();
					}
				}
				
				//
				// Traitement de la MAP et réalisation du fichier de consolidation.
				//
				if (mapTimeParElement!=null && !mapTimeParElement.isEmpty()) {
					generateFileConsolidation(fileConsolidation, fluxEcriture, mapTimeParElement);
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
			
		}
		System.out.println("-- Fin du traitement --");	
	}

	/**
	 * Méthode permettant de générer le fichier de consolidation.
	 * @param fileConsolidation
	 * @param fluxEcriture
	 * @param mapTimeParElement
	 * @return
	 * @throws IOException
	 */
	private static void generateFileConsolidation(
			File fileConsolidation, BufferedWriter fluxEcriture,
			Map<String, List<Double>> mapTimeParElement) throws IOException {
		//réinitialisation du flux d'écriture
		if (fluxEcriture!=null)
			fluxEcriture.close();
		fluxEcriture = new BufferedWriter(new FileWriter(fileConsolidation));
		StringBuffer newLine = null;
		//
		//insertion de la premiere ligne du fichier
		//
		fluxEcriture.write("Element#Nb_Appel#Temps_Cumulé#Temps_Moyen#Min#Max");
		fluxEcriture.newLine();
		//
		//Insertion des lignes de données
		//
		for (String mapKey : mapTimeParElement.keySet()) {
			newLine = new StringBuffer();
			//ajout du nom de l'élément.
			newLine.append(mapKey).append("#");
			List<Double> currentList = mapTimeParElement.get(mapKey);
			//ajout du nombre d'appel à l'élément
			Double nbAppelElement = new Double(currentList.size()); //nombre d'appel à l'élément.
			newLine.append(nbAppelElement).append("#");
			//calcul du temps cumulé, du min et du max.
			Double dCumulTime = new Double(0);
			double min = -1;
			double max = -1;
			for (Iterator<Double> iterator = currentList.iterator(); iterator
					.hasNext();) {
				Double currentTime = (Double) iterator.next();
				dCumulTime += currentTime;
				//mise à jour du min.
				if(currentTime<min || min==-1){
					min = currentTime;
				}
				//mise à jour du max.
				if(currentTime>max || max==-1){
					max = currentTime;
				}
			}
			newLine.append(dCumulTime).append("#");
			//calcul de la moyenne.
			Double avg = dCumulTime/nbAppelElement;
			DecimalFormat df = new DecimalFormat("0.00");
			newLine.append(df.format(avg)).append("#");
			newLine.append(min).append("#");
			newLine.append(max).append("#");
			fluxEcriture.write(newLine.toString());
			fluxEcriture.newLine();
		}
		fluxEcriture.close();

	}

	/**
	 * Méthode permettant d'ajouter dans la map le temps de l'élément.
	 * La map stocke chaque élément et la liste des temps pour chaque élément.
	 * @param mapTimeParElement
	 * @param elementName
	 * @param elementTimeDuration
	 */
	private static void addMap(Map<String, List<Double>> mapTimeParElement,
			String elementName, Double elementTimeDuration) {
		if(mapTimeParElement.containsKey(elementName)){
			//on ajoute la valeur dans la liste de temps correspondant à cet élément.
			mapTimeParElement.get(elementName).add(elementTimeDuration);
		}
		else{
			//ajout dans la map de l'élément et d'une liste de temps associé.
			List<Double> listTime = new ArrayList<Double>();
			listTime.add(elementTimeDuration);
			mapTimeParElement.put(elementName, listTime);
		}
	}
	
}
