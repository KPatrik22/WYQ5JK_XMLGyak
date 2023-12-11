package hu.domparse.wyq5jk;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class DOMQueryWYQ5JK {
    public static void main(String[] args) {
        try {
        	// Reading in the file
        	File xmlFile = new File("XMLWYQ5JK.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Queries
            firstQuery(doc);
            secondQuery(doc);
            thirdQuery(doc);
            fourthQuery(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void firstQuery(Document doc)
    {
    	// Querying the name of all drivers
        NodeList soforList = doc.getElementsByTagName("sofor");
        System.out.println("1, Soforok: ");
        for (int i = 0; i < soforList.getLength(); i++) {
            Element sofor = (Element) soforList.item(i);
            String vezeteknev = sofor.getElementsByTagName("vezeteknev").item(0).getTextContent();
            String keresztnev = sofor.getElementsByTagName("keresztnev").item(0).getTextContent();
            System.out.println(vezeteknev + " " + keresztnev);
        }
        System.out.println();
    }
    
    private static void secondQuery(Document doc)
    {
    	// Querying the data of the bus with serial number '789'
        String sorozatszam = "789";
        NodeList buszList = doc.getElementsByTagName("borrower");
        for (int i = 0; i < buszList.getLength(); i++) {
            Element busz = (Element) buszList.item(i);
            if (busz.getAttribute("sorozatszam").equals(sorozatszam)) {
                String csuklos = busz.getElementsByTagName("csuklos").item(0).getTextContent();
                String tipus = busz.getElementsByTagName("tipus").item(0).getTextContent();

                System.out.println("2, Data of the bus with serial number '" + sorozatszam + "':");
                System.out.println("Csuklos: " + csuklos);
                System.out.println("Tipus: " + tipus);
            }
        }
        System.out.println();
    }
    
    private static void thirdQuery(Document doc)
    {
    	// Querying the name of the driver(s) who are at least 52 years old
        NodeList soforList = doc.getElementsByTagName("sofor");
        System.out.println("3, Drivers who are at least 52 years old:");
        for (int i = 0; i < soforList.getLength(); i++) {
            Element sofor = (Element) soforList.item(i);
            int age = Integer.parseInt(sofor.getElementsByTagName("age").item(0).getTextContent());

            if (age >= 52) {
                String vezeteknev = sofor.getElementsByTagName("vezeteknev").item(0).getTextContent();
                String keresztnev = sofor.getElementsByTagName("keresztnev").item(0).getTextContent();
                System.out.println(vezeteknev + " " + keresztnev);
            }
        }
        System.out.println();
    }
    
    private static void fourthQuery(Document doc)
    {
    	// Querying the type of the bus dtiven by Szabó Tamás
        String sofornev = "Szabó Tamás";

        NodeList soforList = doc.getElementsByTagName("sofor");
        for (int i = 0; i < soforList.getLength(); i++) {
            Element sofor = (Element) soforList.item(i);
            String vezeteknev = sofor.getElementsByTagName("vezeteknev").item(0).getTextContent();
            String keresztnev = sofor.getElementsByTagName("keresztnev").item(0).getTextContent();
            String soforteljesnev = vezeteknev + " " + keresztnev;

            if (soforteljesnev.equals(sofornev)) {
                String szemelyi = sofor.getAttribute("szemelyi");

                NodeList vezetList = doc.getElementsByTagName("vezet");
                System.out.println("5, Type of the bus driven by " + sofornev + ": ");
                for (int j = 0; j < vezetList.getLength(); j++) {
                    Element vezet = (Element) vezetList.item(j);
                    if (vezet.getAttribute("szemelyi").equals(szemelyi)) {
                        String sorozatszam = vezet.getAttribute("sorozatszam");

                        NodeList buszList = doc.getElementsByTagName("busz");
                        for (int k = 0; k < buszList.getLength(); k++) {
                            Element book = (Element) buszList.item(k);
                            if (book.getAttribute("sorozatszam").equals(sorozatszam)) {
                                String tipus = book.getElementsByTagName("tipus").item(0).getTextContent();
                                System.out.println(tipus);
                            }
                        }
                    }
                }
                break; 
            }
        }
    }
}
