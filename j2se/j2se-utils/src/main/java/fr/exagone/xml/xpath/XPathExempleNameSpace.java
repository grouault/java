package fr.exagone.xml.xpath;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathExempleNameSpace {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		// creation d'un document DOM pour acceder au XML.  
	    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	    domFactory.setNamespaceAware(true); // never forget this!
	    DocumentBuilder builder = domFactory.newDocumentBuilder();
	    Document doc = builder.parse("datas/book-name-space.xml");
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
	    
	    // livre de l'année 1995.
	    expr = xpath.compile("//book[@year='1995']/title/text()");
	    NodeList books95 = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
	    System.out.println("livre de l'année 1995");
	    for (int b=0; b < books95.getLength(); b++) {
	      System.out.println(books95.item(b).getNodeValue());  	
	    }
	    System.out.println("-------------------");
	    
	    //Titre des livres de stephenson.
	    // expr = xpath.compile("//book[author='Neal Stephenson']/title/text()");
	    expr = xpath.compile("//pre:book[pre:author='Neal Stephenson']/pre:title/text()");
	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	        System.out.println(nodes.item(i).getNodeValue()); 
	    }
	    System.out.println("-------------------");
	    
	    // checker les numeros qui ont un ISBN valide.
	    xpath.setXPathFunctionResolver(new ISBNFunctionContext());
	    expr = xpath.compile("//pre:book[not(pre:valid-isbn(isbn))]");
	    NodeList nodesIsbn = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);;
	    for (int i = 0; i < nodesIsbn.getLength(); i++) {
	        System.out.println(nodesIsbn.item(i).getNodeValue()); 
	    }
	    
	}
	
}
