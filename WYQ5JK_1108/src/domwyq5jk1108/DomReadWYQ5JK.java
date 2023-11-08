package domwyq5jk1108;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DOMRead {
	public static void main(String[] args) {

	      try {
	         File inputFile = new File("orarendWYQ5JK.xml");
	         
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         
	         doc.getDocumentElement().normalize();
	         System.out.println("Gyokerelem :" + doc.getDocumentElement().getNodeName());
	         NodeList nList = doc.getElementsByTagName("ora");
	         
	         for (int i = 0; i < nList.getLength(); i++) {
	            Node nNode = nList.item(i);
	            System.out.println("\nJelenlegi elem:" + nNode.getNodeName());
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               System.out.println("Targy: " + eElement.getAttribute("targy"));
	               //System.out.println("Idopont: " + eElement.getElementsByTagName("idopont").item(0)..getTextContent() + " "+ eElement.getElementsByTagName("tol").item(0).getTextContent() + " - " + eElement.getElementsByTagName("ig").item(0).getTextContent());
	               System.out.println("Helyszin: " + eElement.getElementsByTagName("helyszín").item(0).getTextContent());
	               System.out.println("Oktato: " + eElement.getElementsByTagName("oktato").item(0).getTextContent());
	               System.out.println("Szak: " + eElement.getElementsByTagName("szak").item(0).getTextContent());
	            }
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
}