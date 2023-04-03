package com.example.fujitsudelivery;

import org.xml.sax.helpers.DefaultHandler;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/*
    * This is where the magic happens
    * This class is used to parse the XML data from the weather service
 */
public class WeatherHandler extends DefaultHandler {
    List<String> wantedCities = Stream.of("Tallinn-Harku","Pärnu","Tartu-Tõravere").collect(Collectors.toList());
    List<City> cities;
    private String timestamp;
    private Stack elements = new Stack();
    private Stack objects = new Stack();

    //get the 3 cities that are needed and set the observation time
    public List<City> getCities() {

        for (City city : cities) {
            city.setObservationTime(timestamp);
        }
        //filter out unwanted cities
        return cities.stream().filter(city -> wantedCities.contains(city.getStation_name())).collect(Collectors.toList());

    }
    //initialize the list of cities when the document starts
    @Override
    public void startDocument() throws org.xml.sax.SAXException {
        cities = new ArrayList<>();
    }
    //push the elements to the stack and create a new city object when the station element is found
    //set the timestamp when the first element is read
    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws org.xml.sax.SAXException {
        elements.push(qName);
        if (qName.equals("observations")) {
            timestamp = attributes.getValue("timestamp");
        }
        if (qName.equals("station")){
            City currentCity = new City();
            objects.push(currentCity);
        }
    }
    //when the station element is closed, add the city to the list of cities
    //pop the elements from the stack as its values are initialized in the city object
    @Override
    public void endElement(String uri, String localName, String qName) throws org.xml.sax.SAXException {
        elements.pop();
        if (qName.equals("station")) {
            City object = (City) objects.pop();
            cities.add(object);

        }
    }
    //sets the behaviour for the element contents (values)
    //set the values for the city object based on the current element
    @Override
    public void characters(char[] ch, int start, int length) throws org.xml.sax.SAXException {
        String value = new String(ch, start, length).trim();
        if (value.length() == 0) {
            return;
        }


        if (currentElement().equals("name")) {
            City currentCity = (City) objects.peek();
            currentCity.setStation_name(value);
        }else if (currentElement().equals("airtemperature")) {
            try {
                City currentCity = (City) objects.peek();
                currentCity.setAir_temp(Double.parseDouble(value));
            }
            catch (Exception e){
                System.out.println("Error parsing airtemperature");
            }
        }
        else if (currentElement().equals("windspeed")) {
            try {
                City currentCity = (City) objects.peek();
                currentCity.setWind_speed(Double.parseDouble(value));
            }
            catch (Exception e){
                System.out.println("Error parsing windspeed");
            }
        }
        else if (currentElement().equals("phenomenon")){
            try {
                City currentCity = (City) objects.peek();
                currentCity.setWeather_phenomenon(value);
            }
            catch (Exception e){
                System.out.println("Error parsing phenomenon");
            }
        }else if (currentElement().equals("wmocode")){
            try {
                City currentCity = (City) objects.peek();
                currentCity.setWmo_station_code(value);
            }
            catch (Exception e){
                System.out.println("Error parsing wmocode");
            }
        }




    }
    //helper method to get the current element
    private String currentElement() {
        return (String) elements.peek();
    }

    @Override
    public void endDocument() throws org.xml.sax.SAXException {

    }



}

