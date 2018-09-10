/*
package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


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

            //    saxParser.parse("boeken.xml", new MyContentHandler());

            //    System.out.println("Validatie is gelukt");

        //    List<String> titels = new ArrayList<>();
        //    saxParser.parse("boeken.xml", new MyContentHandler(titels));
        //    System.out.println("\nDe titels:\n");

        //    titels.forEach(System.out::println);

            List<Boek> boeken = new ArrayList<>();
            saxParser.parse("boeken.xml", new MyContentHandler(boeken));
            System.out.println("De boeken:");
            boeken.forEach(System.out::println);
        /*
            for (int i = 0; i < titels.size(); i++) {
                System.out.printf("\"%s\"\n", titels.get(i).toString());
            }
        */
/*
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            System.out.println(ex.getMessage());

        }
    }
}


/*
class Boek{
    private String titel;
    private String auteur;
    private String beoordeling;

    public String getTitel() {
        return titel;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getBeoordeling() {
        return beoordeling;
    }
    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setBeoordeling(String beoordeling) {
        this.beoordeling = beoordeling;
    }

    @Override
    public String toString() {
        return "Boek{" +
                "titel='" + titel + "', auteur='" + auteur + "', beoordeling='" +  beoordeling + "'}";

    }
}
*/
/*
class MyContentHandler extends DefaultHandler {
    private StringBuilder tekstBuilder = new StringBuilder();
    private List<Boek> boeken;
    private Boek boek;

    public MyContentHandler(List<Boek> boeken) {
        this.boeken = boeken;
    }

    @Override
    public void startDocument() throws SAXException {
        //System.out.println("Begin van het document");
    }

    @Override
    public void endDocument() throws SAXException {
        //System.out.println("Einde van het document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("boek")){
            boek=new Boek();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        tekstBuilder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("titel"))
            boek.setTitel(tekstBuilder.toString());

        else if (localName.equals("auteur"))
            boek.setAuteur(tekstBuilder.toString());

        else if (localName.equals("beoordeling"))
            boek.setBeoordeling(tekstBuilder.toString());

        else if (localName.equals("boek")){
            boeken.add(boek);
        }
        tekstBuilder.setLength(0); // terug leegmaken
    }
}

/*
class MyContentHandler extends DefaultHandler {

    private StringBuilder tekstBuilder = new StringBuilder();


    @Override
    public void startDocument() throws SAXException {
        System.out.println("Begin van het document");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Einde van het document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.printf("Begin element <%s>\n", localName);
    }

    @Override
    public void error(SAXParseException e) throws SAXParseException {
        throw e;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        tekstBuilder.append(ch, start, length);
    }
/*
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.printf("De tekst van %s is \"%s\"%n\n", localName, tekstBuilder.toString());
        tekstBuilder.setLength(0); // terug leegmaken
    }
*/

/*
    private List<String> titels;

    public MyContentHandler(List<String> titels) {
        this.titels = titels;
    }
*/
/*
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("titel")) {
            titels.add(tekstBuilder.toString());
        }
        tekstBuilder.setLength(0); // terug leegmaken
    }
    */

