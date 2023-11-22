package xpathWYQ5JK;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class xPathWYQ5JK {

	public static void main(String args[])throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse("studentWYQ5JK.xml");
		
	    	XPathFactory xpathfactory = XPathFactory.newInstance();
	    	XPath xpath = xpathfactory.newXPath();
	    
	    	System.out.println("1. feladat");
	    	XPathExpression expr = xpath.compile("//class/student");
	    	Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    	NodeList nodes = (NodeList) result;
	    	for (int i = 0; i < nodes.getLength(); i++) {
	    		System.out.println(doc.getElementsByTagName("keresztnev").item(0).getTextContent());
	    		System.out.println(doc.getElementsByTagName("vezeteknev").item(0).getTextContent());
	    		System.out.println(doc.getElementsByTagName("becenev").item(0).getTextContent());
	    		System.out.println(doc.getElementsByTagName("kor").item(0).getTextContent());
	    	}
	    
	    	System.out.println("2. feladat");
	    	expr = xpath.compile("//student[@id='02']");
	    	result = expr.evaluate(doc, XPathConstants.NODESET);
	    	nodes = (NodeList) result;
	    	for (int i = 0; i < nodes.getLength(); i++) {
	    	NodeList children = nodes.item(i).getChildNodes();
	    	for(int j = 0; j < children.getLength(); j++){
	    		System.out.print(children.item(j).getTextContent());
	    	}

		System.out.println("3. feladat");
		expr = xpath.compile("//student");
		result = expr.evaluate(doc, XPathConstants.NODESET);
	    	nodes = (NodeList) result;

		for (int i = 0; i < nodes.getLength(); i++) {
	    		System.out.println(doc.getElementsByTagName("keresztnev").item(0).getTextContent());
	    		System.out.println(doc.getElementsByTagName("vezeteknev").item(0).getTextContent());
	    		System.out.println(doc.getElementsByTagName("becenev").item(0).getTextContent());
	    		System.out.println(doc.getElementsByTagName("kor").item(0).getTextContent());
	    	}
	}
}