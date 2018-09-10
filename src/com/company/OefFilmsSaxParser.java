package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class OefFilmsSaxParser {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

        try {

            SAXParserFactory spf = SAXParserFactory.newDefaultInstance();

            spf.setNamespaceAware(true);

            SAXParser saxParser = spf.newSAXParser();


            List<Film> films = new ArrayList<>();

            saxParser.parse("films.xml", new MyContentHandler(films));

            films.forEach(System.out::println);


        } catch (SAXException | IOException | ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        }

    }
}


class Film {

    private String titel;
    private String jaar;
    private String genre;

    public String getTitel() {
        return titel;
    }

    public String getJaar() {
        return jaar;
    }

    public String getGenre() {
        return genre;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setJaar(String jaar) {
        this.jaar = jaar;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Film{" + "titel='" + this.getTitel() + "', jaar='" + this.getJaar() + "', genre='" + this.getGenre() + "'}";

    }
}

class MyContentHandler extends DefaultHandler {

    private StringBuilder tekstBuilder = new StringBuilder();
    private List<Film> films;
    private Film film;

    public MyContentHandler(List<Film> films) {
        this.films = films;
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

        if (localName.equals("film")) {
            film = new Film();
            for (int i = 0; i < attributes.getLength(); i++) {
                film.setGenre(attributes.getValue(i));

            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        tekstBuilder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (localName.equals("titel"))
            film.setTitel(tekstBuilder.toString().trim());

        else if (localName.equals("jaar"))
            film.setJaar(tekstBuilder.toString().trim());

        else if (localName.equals("genre"))
            film.setGenre(tekstBuilder.toString().trim());

        else if (localName.equals("film")) {
            films.add(film);
        }

        tekstBuilder.setLength(0); // terug leegmaken
    }
}
