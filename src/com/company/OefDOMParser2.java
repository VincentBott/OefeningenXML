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

public class OefDOMParser2 {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        db.setErrorHandler(new MyErrorHandler());
        Document doc = db.parse("boeken2.xml");
        doc.getDocumentElement().normalize();
        XPathFactory xpathFactory= XPathFactory.newDefaultInstance();
        XPath xpath= xpathFactory.newXPath();
        XPathExpression expression = xpath.compile("/boeken/boek/auteur/@geslacht");
        NodeList boeken = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < boeken.getLength(); i++) {
            System.out.printf("%s%n", boeken.item(i).getNodeValue());

        }
    }


    private static class MyErrorHandler implements ErrorHandler {

        @Override
        public void warning(SAXParseException exception) throws SAXException {
            System.out.printf("Waarschuwing: %s%n", exception.getMessage());
        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
            System.out.printf("Error: %s%n", exception.getMessage());
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
            System.out.printf("Fatal error: %s%n", exception.getMessage());
        }
    }
}
