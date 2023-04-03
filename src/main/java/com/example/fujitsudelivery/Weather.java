package com.example.fujitsudelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

/*
    * This class is used to fetch the weather data from the Estonian weather service
    * It uses the SAX parser to parse the XML data
    * It uses the CityRepository to save the data to the database
 */
@Component
public class Weather{
    @Autowired
    private CityRepository cityRepository;

    public static void main(String[] args) {
    }

    //fetches the weather data from the Estonian weather service and parses it using a custom content handler, then saves the data
    public List<City> getWeatherData() throws ParserConfigurationException, IOException, SAXException {
        final String uri = "https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php";
        XMLReader myReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        WeatherHandler handler = new WeatherHandler();
        myReader.setContentHandler(handler);
        myReader.parse(uri);
        List<City> cities = handler.getCities();
        cityRepository.saveAll(cities);
        return cities;
    }


    public void run(String... args) throws Exception {
        getWeatherData();
    }
}
