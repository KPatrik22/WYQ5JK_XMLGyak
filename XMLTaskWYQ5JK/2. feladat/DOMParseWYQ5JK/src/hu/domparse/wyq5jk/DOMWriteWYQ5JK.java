package hu.domparse.wyq5jk;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringJoiner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

public class DOMWriteWYQ5JK {
    public static void main(String[] args) {
        try {
        	 // Creating a new document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Filling up the document with data
            addData(doc);
            
            File outputFile = new File("XMLWYQ5JK1.xml");
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile, true));

            // Printing out the root element and the prolog
            Element rootElement = doc.getDocumentElement();
            String rootName = rootElement.getTagName();
            StringJoiner rootAttributes = new StringJoiner(" ");
            NamedNodeMap rootAttributeMap = rootElement.getAttributes();

            for (int i = 0; i < rootAttributeMap.getLength(); i++) {
                Node attribute = rootAttributeMap.item(i);
                rootAttributes.add(attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
            }

            System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

            System.out.println("<" + rootName + " " + rootAttributes.toString() + ">\n");
            writer.println("<" + rootName + " " + rootAttributes.toString() + ">\n");

            // Processing the elements
            NodeList tulajdonosList = doc.getElementsByTagName("tulajdonos");
            NodeList mvkList = doc.getElementsByTagName("mvk");
            NodeList buszList = doc.getElementsByTagName("busz");
            NodeList soforList = doc.getElementsByTagName("sofor");
            NodeList vezetList = doc.getElementsByTagName("vezet");
            NodeList csaladtagList = doc.getElementsByTagName("csaladtag");
            NodeList rokonList = doc.getElementsByTagName("rokon");

            // Printing out the (formatted) XML to the console and to the file
            printNodeList(tulajdonosList, writer);
            printNodeList(mvkList, writer);
            printNodeList(buszList, writer);
            printNodeList(soforList, writer);
            printNodeList(vezetList, writer);
            printNodeList(csaladtagList, writer);
            printNodeList(rokonList, writer);

            // Closing the root element
            System.out.println("</" + rootName + ">");
            writer.append("</" + rootName + ">");

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void addData(Document doc) {
    	
        Element rootElement = doc.createElement("WYQ5JK_MVK");
        rootElement.setAttribute("xmlns:xs", "http://www.w3.org/2001/XMLSchema-instance");
        rootElement.setAttribute("xs:noNamespaceSchemaLocation", "XMLSchemaWYQ5JK.xsd");
        doc.appendChild(rootElement);

        addTulajdonos(doc, rootElement, "05-10-000406", "Miskolc Holding Önkormányzati Vagyonkezelő Zrt.");
        
        addMvk(doc, rootElement, "05-10-000147", "05-10-000406", "Egyetemváros", "Centrum", "Búza tér");
        
        addBusz(doc, rootElement, "123", "true", "MAN Lion’s City GL A40 CNG");
        addBusz(doc, rootElement, "456", "false", "MAN Lion’s City A21 CNG");
        addBusz(doc, rootElement, "789", "true", "Neoplan N4522 Centroliner");

        addSofor(doc, rootElement, "000001HE", "1965-10-15", "Kovács", "Béla", "58");
        addSofor(doc, rootElement, "000002SA", "1972-12-04", "Szabó", "Tamás", "51");
        addSofor(doc, rootElement, "000003TA", "1971-04-29", "Tóth", "István", "52");

        addVezet(doc, rootElement, "123", "000002SA", "2023-11-13");
        addVezet(doc, rootElement, "456", "000003TA", "2023-11-17");
        addVezet(doc, rootElement, "789", "000001HE", "2023-11-15");

        addCsaladtag(doc, rootElement, "Kovácsné Mária", "55");
        addCsaladtag(doc, rootElement, "Szabó Noémi", "7");
        addCsaladtag(doc, rootElement, "Tóth Géza", "12");

        addRokon(doc, rootElement, "000001HE", "Kovácsné Mária", "házastárs");
        addRokon(doc, rootElement, "000002SA", "Szabó Noémi", "gyermek");
        addRokon(doc, rootElement, "000003TA", "Tóth Géza", "gyermek");

    }
    
    private static void addTulajdonos(Document doc, Element rootElement, String tcegjegyzekszam, String name) {
        Element tulajdonos = doc.createElement("tulajdonos");
        tulajdonos.setAttribute("t_cegjegyzekszam", tcegjegyzekszam);

        Element nameElement = createElement(doc, "name", name);
        tulajdonos.appendChild(nameElement);

        rootElement.appendChild(tulajdonos);
    }

    private static void addMvk(Document doc, Element rootElement, String mvkcegjegyzekszam, String tcegjegyzekszam, String megallo1, String megallo2, String megallo3) {
        Element mvk = doc.createElement("mvk");
        mvk.setAttribute("mvk_cegjegyzekszam", mvkcegjegyzekszam);
        mvk.setAttribute("t_cegjegyzekszam", tcegjegyzekszam);

        Element megallok = doc.createElement("megallok");
        Element megallo1Element = createElement(doc, "megallo", megallo1);
        megallok.appendChild(megallo1Element);
        Element megallo2Element = createElement(doc, "megallo", megallo2);
        megallok.appendChild(megallo2Element);
        Element megallo3Element = createElement(doc, "megallo", megallo3);
        megallok.appendChild(megallo3Element);

        rootElement.appendChild(mvk);
    }
    
    private static void addBusz(Document doc, Element rootElement, String sorozatszam, String csuklos, String tipus) {
        Element busz = doc.createElement("busz");
        busz.setAttribute("sorozatszam", sorozatszam);

        Element csuklosElement = createElement(doc, "csuklos", csuklos);
        busz.appendChild(csuklosElement);

        Element tipusElement = createElement(doc, "tipus", tipus);
        busz.appendChild(tipusElement);

        rootElement.appendChild(busz);
    }

    private static void addSofor(Document doc, Element rootElement, String szemelyi, String szuletesiido, String vezeteknev, String keresztnev, String eletkor) {
        Element sofor = doc.createElement("sofor");
        sofor.setAttribute("szemelyi", szemelyi);

        Element szuletesiidoElement = createElement(doc, "szuletesi_ido", szuletesiido);
        sofor.appendChild(szuletesiidoElement);

        Element name = doc.createElement("teljes_nev");
        Element vezeteknevElement = createElement(doc, "vezeteknev", vezeteknev);
        Element keresztnevElement = createElement(doc, "keresztnev", keresztnev);
        name.appendChild(vezeteknevElement);
        name.appendChild(keresztnevElement);

        Element eletkorElement = createElement(doc, "eletkor", eletkor);
        sofor.appendChild(eletkorElement);

        rootElement.appendChild(sofor);
    }

    private static void addVezet(Document doc, Element rootElement, String buszref, String soforref, String mikor) {
        Element vezet = doc.createElement("vezet");
        vezet.setAttribute("buszREF", buszref);
        vezet.setAttribute("soforREF", soforref);

        Element mikorElement = createElement(doc, "mikor", mikor);
        vezet.appendChild(mikorElement);

        rootElement.appendChild(vezet);
    }

    private static void addCsaladtag(Document doc, Element rootElement, String nev, String kor) {
        Element csaladtag = doc.createElement("csaladtag");
        csaladtag.setAttribute("nev", nev);

        Element korElement = createElement(doc, "kor", kor);
        csaladtag.appendChild(korElement);

        rootElement.appendChild(csaladtag);
    }

    private static void addRokon(Document doc, Element rootElement, String soforref, String csaladtagref, String rokonikapcsolat) {
        Element rokon = doc.createElement("rokon");
        rokon.setAttribute("soforREF", soforref);
        rokon.setAttribute("csaladtagREF", csaladtagref);

        Element rokonikapcsolaElement = createElement(doc, "rokoni_kapcsolat", rokonikapcsolat);
        rokon.appendChild(rokonikapcsolaElement);

        rootElement.appendChild(rokon);
    }


    private static Element createElement(Document doc, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }
    
    // Printing out the content of the NodeList recursively
    private static void printNodeList(NodeList nodeList, PrintWriter writer) {
        for (int i = 0; i < nodeList.getLength(); i++) {
          Node node = nodeList.item(i);
          printNode(node, 1, writer);
          System.out.println("");
          writer.println("");
        }
        System.out.println("");
        writer.println("");
    }

    // Printing out the content of the Node recursively
	private static void printNode(Node node, int indent, PrintWriter writer) {
	    if (node.getNodeType() == Node.ELEMENT_NODE) {
	      Element element = (Element) node;
	      String nodeName = element.getTagName();
	      StringJoiner attributes = new StringJoiner(" ");
	      NamedNodeMap attributeMap = element.getAttributes();
	
	      // Name and attributes
	      for (int i = 0; i < attributeMap.getLength(); i++) {
	        Node attribute = attributeMap.item(i);
	        attributes.add(attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
	      }
	
	      System.out.print(getIndentString(indent));
	      System.out.print("<" + nodeName + " " + attributes.toString()+ ">");
	
	      writer.print(getIndentString(indent));
	      writer.print("<" + nodeName + " " + attributes.toString() + ">");
	
	      // Content
	      NodeList children = element.getChildNodes();
	      if (children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE) {
	        System.out.print(children.item(0).getNodeValue());
	        writer.print(children.item(0).getNodeValue());
	      } else {
	        System.out.println();
	        writer.println();
	        for (int i = 0; i < children.getLength(); i++) {
	          printNode(children.item(i), indent + 1, writer);
	        }
	        System.out.print(getIndentString(indent));
	        writer.print(getIndentString(indent));
	      }
	      
	      // Closing the node
	      System.out.println("</" + nodeName + ">");
	      writer.println("</" + nodeName + ">");
	    }
	}
	
	// Indenting
	private static String getIndentString(int indent) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < indent; i++) {
	      sb.append("  "); // 2 spaces per indent level
	    }
	    return sb.toString();
	}
}
