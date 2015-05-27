package fr.exagone.xml.xpath;

import java.io.IOException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

public class XPathExample {

	  /**
	   * Exemple avec un fichier xml sans NameSpace.
	   *  ==> Le fichier Xml contient un NameSpace.
	   * @param args
	   * @throws ParserConfigurationException
	   * @throws SAXException
	   * @throws IOException
	   * @throws XPathExpressionException
	   */
	  public static void main(String[] args) 
			   throws ParserConfigurationException, SAXException, 
			          IOException, XPathExpressionException {
		// creation d'un document DOM pour acceder au XML.  
	    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	    domFactory.setNamespaceAware(true); // never forget this!
	    DocumentBuilder builder = domFactory.newDocumentBuilder();
	    Document doc = builder.parse("datas/books.xml");
        // obtention d'un objet XPath.
	    XPathFactory factory = XPathFactory.newInstance();
	    XPath xpath = factory.newXPath();
	    xpath.setNamespaceContext(new PersonalNamespaceContext());
	    
	    /** REQUETES */
	    XPathExpression expr = null;
	    // nombre de livres.
	    expr = xpath.compile("count(//book)");
	    Double nbBooks = (Double) expr.evaluate(doc, XPathConstants.NUMBER);
	    System.out.println("Nombre de livres = " + nbBooks.intValue());
	    System.out.println("-------------------");
	    
	    // nombre de livres de stephenson.
	    expr = xpath.compile("count(//book[author='Neal Stephenson'])");
	    Double nbLivreStephenson = (Double) expr.evaluate(doc, XPathConstants.NUMBER);
	    System.out.println("Nombre de livres de stephenson = " + nbLivreStephenson.intValue());
	    System.out.println("-------------------");
	    
	    // annee des books.
	    expr = xpath.compile("//book/@year");
	    NodeList years = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
	    for (int y=0; y<years.getLength(); y++) {
	      System.out.println("year of books = " + years.item(y).getNodeValue());	
	    }
	    System.out.println("-------------------");
	    
	    // selection à partir d'un attribut.
	    // livre de l'année 1995.
	    expr = xpath.compile("//book[@year='1995']/title/text()");
	    NodeList books95 = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
	    System.out.println("livre de l'année 1995");
	    for (int b=0; b < books95.getLength(); b++) {
	      System.out.println(books95.item(b).getNodeValue());  	
	    }
	    System.out.println("-------------------");
	    
	    //Titre des livres de stephenson.
	    expr = xpath.compile("//book[author='Neal Stephenson']/title/text()");
	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	        System.out.println(nodes.item(i).getNodeValue()); 
	    }
	    System.out.println("-------------------");
	    

	}
	
}
