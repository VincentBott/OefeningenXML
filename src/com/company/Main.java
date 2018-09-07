package com.company;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;




public class Main {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {


        try {


            //    StreamSource xsd = new StreamSource("boeken.xsd");
            //    Schema schema = schemaFactory.newSchema(xsd);
            //    Validator validator = schema.newValidator();
            //    validator.validate(new StreamSource("boeken.xml"));

            SchemaFactory schemaFactory = SchemaFactory.newDefaultInstance();
            Schema schema = schemaFactory.newSchema(new StreamSource("boeken.xsd"));
            SAXParserFactory spf = SAXParserFactory.newDefaultInstance();
            spf.setSchema(schema);
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            saxParser.parse("boeken.xml", new MyContentHandler());

            System.out.println("Validatie is gelukt");

        } catch (SAXException | IOException | ParserConfigurationException ex) {
            System.out.println(ex.getMessage());

        }
    }
}

class MyContentHandler extends DefaultHandler {

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Begin van het document");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Einde van het document");
    }
}
