package com.company;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;


public class OefDOMParser {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        SchemaFactory schemaFactory = SchemaFactory.newDefaultInstance();
        Schema schema = schemaFactory.newSchema(new StreamSource("boeken.xsd"));
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
        dbf.setNamespaceAware(true);
        dbf.setSchema(schema);
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        db.setErrorHandler(new MyErrorHandler());
        Document doc = db.parse("boeken.xml");
        Element root = doc.getDocumentElement();

        doc.getDocumentElement().normalize();
        System.out.printf("Root node: %s%n", doc.getDocumentElement().getTagName());

        NodeList children =  doc.getDocumentElement().getChildNodes();
        toonChildren(children);
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

    private static void toonChildren(NodeList items){        //   Recursief programmeren ! De functie vanuit de functie zelf oproepen.
        for (int i = 0; i < items.getLength(); i++) {
            switch(items.item(i).getNodeType()){
                case Node.ELEMENT_NODE:
                    System.out.printf("Node: %s%n", items.item(i).getNodeName());
                    if (items.item(i).hasChildNodes()){
                        toonChildren(items.item(i).getChildNodes());
                    }
                    /*
                case Node.TEXT_NODE:
                    System.out.println(items.item(i).getNodeValue());
                    break;
                    */
            }
        }
    }

}


