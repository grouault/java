package fr.exagone.xml.sax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

public class ShowFacture {

	public static void main(String[] args) {
		
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser parser = null;
      try {
    	//obtenir une instance d'un analyseur.
	    parser = factory.newSAXParser();
	    final List<String> articles = new ArrayList<String>();  // liste des articles.
        DefaultHandler gestionnaireSAX = new DefaultHandler(){
          boolean bClient = false;
          public void startElement (String uri, String localName,String nom, Attributes attributs) throws SAXException {
    	    // recuperation des infos sur le client.
        	if (("client").equals(nom)) {
    		  System.out.println("client = " + attributs.getValue("nom") + " - " + attributs.getValue("prenom"));	
    		  bClient = true;
    		}
        	// recuperation des articles.
            if ("article".equals(nom)) {
              String description = attributs.getValue("description"); 
              articles.add(description); 	
            }                 
    	  } 	
	      // recuperation de l'adresse.    
	      public void characters(char ch[], int start, int length) throws SAXException {		 
	      	if (bClient) {
	      	  System.out.println("Adresse : " + new String(ch, start, length));       		
	      	  bClient = false;
	      	}
      	 }
        };
        // on parse le document xml.
        parser.parse("datas/facture01.xml", gestionnaireSAX);
        //affichage des articles.
        for (Iterator<String> iterator = articles.iterator(); iterator.hasNext();) {
		  String desc = (String) iterator.next();
		  System.out.println("article = " + desc);	
		}
      } catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  } catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  } catch (IOException e) {
		System.out.println("Impossible de lire le fichier source.");
		e.printStackTrace();
	} 
		
	}
	
}
