package fr.exagone.xml.xpath;

import javax.xml.xpath.*;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class ShowFactureXpath {

	public static void main(String[] args) {
		
	XPath xpath = XPathFactory.newInstance().newXPath();
	String expression = "//client/text()";
	InputSource inputSource = new InputSource("datas/facture01.xml");
	try {
		Node node = (Node) xpath.evaluate(expression, inputSource, XPathConstants.NODE);
		System.out.println("node.name = " + node.getNodeName());
		System.out.println("node.value = " + node.getNodeValue());
	} catch (XPathExpressionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
	}
	
}
