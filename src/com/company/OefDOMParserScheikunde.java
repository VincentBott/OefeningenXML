package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.Scanner;

public class OefDOMParserScheikunde {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();

        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse("elements.xml");

        XPathFactory factory = XPathFactory.newDefaultInstance();

        XPath xpath = factory.newXPath();

        /*
        XPathExpression expression = xpath.compile("/elements/element/symbol/text()");

        NodeList symbolen = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < symbolen.getLength(); i++) {
            System.out.println("Symbool: " + symbolen.item(i).getNodeValue());

        }
        */

        /*
        String elementnaam = (String)xpath.evaluate("elements/element[symbol/text()='Sb']/name/text()",doc, XPathConstants.STRING);
        System.out.printf("Het element met symbool Sb heet %s%n", elementnaam);
        */

        Scanner scanner = new Scanner(System.in);
        System.out.print("Geef een symbool: ");

        String symbool = scanner.nextLine();


        String elementnaam = (String) xpath.evaluate("elements/element[symbol/text()=" + "'" + symbool + "']/name/text()",doc, XPathConstants.STRING);

        if (elementnaam.equals(""))
            System.out.println("Dit element bestaat niet.");
        else
            System.out.printf("Het element met symbool %s heet %s%n", symbool, elementnaam);
    }

}

