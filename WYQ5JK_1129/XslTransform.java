package xsltWYQ5JK;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XslTransform {


	    public static void main(String[] args) {
	        try {
	        	//1. feladat
	            String xmlInput = "hallgatoWYQ5JK.xml";
	            String xsltInputHTML = "hallgatoWYQ5JK.xsl";
	            String xsltInputXML = "hallgatoWYQ5JKxml.xsl";
	            String outputResult = "hallgatoWYQ5JK.html";
	            String outputResultXML = "hallgatoWYQ5JK.out.xml";

	            TransformerFactory transformerFactory = TransformerFactory.newInstance();

	            Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsltInputHTML));
	            transformer.transform(new StreamSource(xmlInput), new StreamResult(outputResult));
	            
	            transformer = transformerFactory.newTransformer(new StreamSource(xsltInputXML));
	            transformer.transform(new StreamSource(xmlInput), new StreamResult(outputResultXML));
	            
	            //2. feladat
	            xmlInput = "orarendWYQ5JK.xml";
	            xsltInputHTML = "orarendWYQ5JK.xsl";
	            xsltInputXML = "orarendWYQ5JKxml.xsl";
	            outputResult = "orarendWYQ5JK.html";
	            outputResultXML = "orarendWYQ5JK.out.xml";

	            transformer = transformerFactory.newTransformer(new StreamSource(xsltInputHTML));
	            transformer.transform(new StreamSource(xmlInput), new StreamResult(outputResult));
	            
	            transformer = transformerFactory.newTransformer(new StreamSource(xsltInputXML));
	            transformer.transform(new StreamSource(xmlInput), new StreamResult(outputResultXML));

	            System.out.println("Sikeres XSLT transzformáció, eredmény mentve.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}