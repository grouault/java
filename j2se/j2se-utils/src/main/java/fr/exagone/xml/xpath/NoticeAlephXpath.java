package fr.exagone.xml.xpath;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class NoticeAlephXpath {


	public static void main(String[] args) {
		
	XPath xpath = XPathFactory.newInstance().newXPath();
	String expression = "//datafield700/text()";
	InputSource inputSource = new InputSource("datas/notice-aleph.xml");
	try {
		   NodeList nodes = (NodeList) xpath.evaluate(expression, inputSource, XPathConstants.NODESET);
		   StringBuffer buffer = new StringBuffer(); 
		   for (int i = 0, n = nodes.getLength(); i < n; i++) {
		      Node node = nodes.item(i);
		      if (node.getNodeValue() != null && node.getNodeValue().length() > 0  ) {
		    	// System.out.println(" Type = " + node.getNodeType());
		    	// System.out.println(" Node = " + Node.TEXT_NODE);
		    	buffer.append(node.getNodeValue()); 
		      }
		    }
		   System.out.println(buffer.toString().trim());
	} catch (XPathExpressionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
	}
	
}
