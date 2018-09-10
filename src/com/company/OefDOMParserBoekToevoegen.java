package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.Scanner;

public class OefDOMParserBoekToevoegen {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse("boeken2.xml");

        Element boekElement = doc.createElement("boek");


        Element titelElement = doc.createElement("titel");
        Text tekstTitel= doc.createTextNode("Harry Potter and the Philosophers Stone");

        Element beoordelingElement = doc.createElement("beoordeling");
        Text tekstBeoordeling= doc.createTextNode("Heb hem niet gelezen.");

        Element auteurElement = doc.createElement("auteur");
        Text tekstAuteur = doc.createTextNode("J. K. Rowling");


        titelElement.appendChild(tekstTitel);
        beoordelingElement.appendChild(tekstBeoordeling);
        auteurElement.appendChild(tekstAuteur);
        boekElement.appendChild(titelElement);


        doc.getDocumentElement().appendChild(titelElement);
        doc.getDocumentElement().appendChild(auteurElement);
        doc.getDocumentElement().appendChild(boekElement);

        TransformerFactory tff = TransformerFactory.newDefaultInstance();
        Transformer transformer = tff.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult("boeken3.xml");
        transformer.transform(source, result);
        result = new StreamResult(System.out);
        transformer.transform(source, result);

    }
}

/*
Titel: Harry Potter and the Philosophers Stone

Beoordeling: (vul zelf maar in)

Auteur: J. K. Rowling
 */
