package com.example.fujitsudelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collections;
/*
    * This class is used to handle the requests from the client
    * Allows you to set and replace different extra costs for delivery, like weather and windspeed or temperature
    * also allows you to set the base fee for a given city and vehicle type based on the most recent available data
 */
@RestController
public class Controller {
    @Autowired
    private RBFRepository RBFRepository;
    @Autowired
    private PhenomenonRepository phenomenonRepository;
    @Autowired
    private WPEFRepository WPEFRepository;
    @Autowired
    private WSEFRepository WSEFRepository;
    @Autowired
    private ATEFRepository ATEFRepository;
    @Autowired
    private FindFee findFee;


    //add a new RBF to database
    @PostMapping("/addRBF/{city}/{vehicle}/{price}")
    public String addRBF(@PathVariable String city, @PathVariable String vehicle, @PathVariable double price){
        RBF rbf = new RBF();
        rbf.setCity(city);
        rbf.setVehicle(vehicle);
        rbf.setRBF(price);
        RBFRepository.save(rbf);
        return "RBF added";
    }
    //returns the total delivery fee for a given city and vehicle type
    @GetMapping("/getTotal/{city}/{vehicle}")
    public double getTotal(@PathVariable String city, @PathVariable String vehicle) throws ParserConfigurationException, IOException, SAXException {
        double totalFee = findFee.getTotalFee(city, vehicle);
        if (findFee.dontDeliver || totalFee > 50){
            throw new RuntimeException("Usage of selected vehicle type is forbidden");
        }
        return totalFee;
    }
    //add a new ATEF to database
    @PostMapping("/addATEF/{lower}/{upper}/{price}")
    public String addATEF(@PathVariable double lower, @PathVariable double upper, @PathVariable double price){
        ATEF atef = new ATEF();
        atef.setLower(lower);
        atef.setUpper(upper);
        atef.setPrice(price);
        ATEFRepository.save(atef);
        return "ATEF added";
    }
    //delete atef from database
    @PostMapping("/deleteATEF/{id}")
    public String deleteATEF(@PathVariable int id){
        ATEFRepository.deleteById(id);
        return "ATEF deleted";
    }
    //add a new WSEF to database
    @PostMapping("/addWSEF/{lower}/{upper}/{price}")
    public String addWSEF(@PathVariable double lower, @PathVariable double upper, @PathVariable double price){
        WSEF wsef = new WSEF();
        wsef.setLower(lower);
        wsef.setUpper(upper);
        wsef.setPrice(price);
        WSEFRepository.save(wsef);
        return "WSEF added";
    }
    //delete wsef from database
    @PostMapping("/deleteWSEF/{id}")
    public String deleteWSEF(@PathVariable int id){
        WSEFRepository.deleteById(id);
        return "WSEF deleted";
    }
    //add a new WPEF to database
    //unfortunately, this method only lets you add one phenomenon per weather phenomenon fee
    //adding multiple phenomena with same fee still works, but you have to add them one by one
    @PostMapping("/addWPEF/{phenomenon}/{price}")
    public String addWPEF(@PathVariable String phenomenon, @PathVariable double price){
        Phenomenon phenomenon1 = new Phenomenon();
        phenomenon1.setPhenomenon(phenomenon);
        WPEF wpef = new WPEF();
        wpef.setPhenomenon(Collections.singleton(phenomenon1));
        wpef.setPrice(price);
        WPEFRepository.save(wpef);
        phenomenonRepository.save(phenomenon1);
        return "WPEF added";
    }
    //delete wpef from database
    @PostMapping("/deleteWPEF/{id}")
    public String deleteWPEF(@PathVariable int id){
        WPEF deletable = WPEFRepository.findById(id).get();
        WPEFRepository.deleteById(id);
        phenomenonRepository.deleteByWPEF(deletable);
        return "WPEF deleted";
    }
}
