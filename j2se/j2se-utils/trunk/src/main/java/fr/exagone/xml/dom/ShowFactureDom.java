package fr.exagone.xml.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class ShowFactureDom {
	
	enum Elements {article, client};
	
	public static void main(String[] args) {
		
	  try {
		// obtain a parser.	 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
	    // obtain de DOM document instance.
		Document document = builder.parse("datas/facture01.xml");
		Element nodeFacture = document.getDocumentElement(); // recuperation de l'élément racine : facture.
		// infos de la facture.
		String dateFacture = nodeFacture.getAttribute("date");
		String montantFacture = nodeFacture.getAttribute("montant");
		System.out.println("TRAITEMENT FACTURE : montant = " + montantFacture + " / date = " + dateFacture);
		// recuperation et traitement des noeuds.
		NodeList elements = nodeFacture.getChildNodes(); 
		Node currentNode = null;
		for (int i=0; i<elements.getLength(); i++) {
	      currentNode = elements.item(i);
	      if (Node.ELEMENT_NODE == currentNode.getNodeType()) {;  
	        Element currentElement = (Element)currentNode;
	        switch (Elements.valueOf(currentElement.getNodeName())) {
	        case client:
	          traiterInfosClient(currentElement);
	          break;
	        case article:
			  traiterInfosArticle(currentElement);	
			  break;
			default:
			  break;
			}
	      }
		}
	  } catch (ParserConfigurationException e) {
		String pb =  "problement de configuration de parser";
		System.out.println(pb);
		e.printStackTrace();
	  } catch (SAXException e) {
		String pb = "Document incorrect";
		System.out.println(pb);
		e.printStackTrace();
	  } catch (IOException e) {
		String pb = "Problème de lecture du fichier.";
		System.out.println(pb);
		e.printStackTrace();
	  }	
		
	}

	/**
	 * traiter les infos d'un article.
	 * @param element
	 */
	private static void traiterInfosArticle (Element element){
      String description = element.getAttribute("description");
      System.out.println("Traitement article : " + description);
	}
	
	/**
	 * traiter les infos du client
	 * @param element
	 */
	private static void traiterInfosClient(Element element) {
      StringBuffer buffer = new StringBuffer();
	  String nom = element.getAttribute("nom");
	  String prenom = element.getAttribute("prenom");
	  String adresse = ((Text)element.getFirstChild()).getData();
	  buffer.append("Traitement Client : \n").append("nom :").append(nom).append("\n");
	  buffer.append("prenom : ").append(prenom).append("\n");
	  buffer.append("adresse : ").append(adresse);
	  System.out.println(buffer.toString());
	}
	
}


