package hu.domparse.wyq5jk;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

public class DOMModifyWYQ5JK {
    public static void main(String[] args) {
        try {
            // Reading in the file
        	File xmlFile = new File("XMLWYQ5JK.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            
            // Implementation of changes
           
            //Changing the name of the owner
            NodeList tulajdonosList = doc.getElementsByTagName("tulajdonos");
            Element tulajdonos = (Element) tulajdonosList.item(0);
            tulajdonos.getElementsByTagName("name").item(0).setTextContent("Kolozsvári Patrik");
            
            //Changing the type of the bus
            NodeList buszList = doc.getElementsByTagName("busz");
            Element busz = (Element) buszList.item(1);
            busz.getElementsByTagName("tipus").item(0).setTextContent("nem rossz busz");
            
            //Changing the age of a driver
            NodeList soforList = doc.getElementsByTagName("sofor");
            Element sofor = (Element) soforList.item(1);
            sofor.getElementsByTagName("eletkor").item(0).setTextContent("30");
            
            //Changing the date of an element in Vezet
            NodeList vezetList = doc.getElementsByTagName("vezet");
            Element vezet = (Element) vezetList.item(1);
            vezet.getElementsByTagName("mikor").item(0).setTextContent("2023-11-14");
            
            
            // Transforming the modified document to a string
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Creating a StringWriter to write the XML string
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));

            // Getting the modified XML as a string
            String output = writer.toString();
            // Printing out the modified XML to the console
            System.out.println(output);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
