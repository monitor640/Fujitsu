package com.example.fujitsudelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * This class is used to calculate the total fee for the delivery
 */
@Service
public class FindFee {
    private double totalFee = 0;
    private double RBF = 0;
    private double ATEF = 0;
    private double WSEF = 0;
    private double WPEF = 0;
    String city;
    String vehicle;
    boolean dontDeliver = false;

    @Autowired
    private RBFRepository RBFRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private WPEFRepository wpefRepository;
    @Autowired
    private PhenomenonRepository phenomenonRepository;
    @Autowired
    private ATEFRepository atefRepository;
    @Autowired
    private WSEFRepository wsefRepository;
    //Map of Estonian cities and their weather stations
    Map<String, String> cityToStation = Stream.of(
            new Object[][]{
                    {"Tallinn", "Tallinn-Harku"},
                    {"Tartu", "Tartu-Tõravere"},
                    {"Pärnu", "Pärnu"},
            }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));

    public FindFee() {
    }

    //finds the base fee for the delivery based on the city and vehicle type
    public void setRBF() {
        RBF = RBFRepository.findByCityAndVehicle(city, vehicle).getRBF();
    }

    //finds the additional cost due to weather phenomenon
    public void setWPEF(City c) {

        String phenomenonName = c.getWeather_phenomenon();
        if (phenomenonName == null) {
            return;
        }
        //process the name of the weather phenomenon from the API to match the database
        //database has more general names for the weather phenomena
        phenomenonName = processPhenomenon(phenomenonName);
        //tries to find the weather phenomenon in the database
        //if the phenomenon is not found, the delivery has no extra cost
        try {
            Phenomenon phenomenon = phenomenonRepository.findByPhenomenon(phenomenonName);
            com.example.fujitsudelivery.WPEF WPEF = phenomenon.getExtraFees();
            this.WPEF = WPEF.getPrice();
            if (this.WPEF == 99.0) {
                dontDeliver = true;
            }
        } catch (Exception e) {
            System.out.println("Phenomenon not found");
        }

    }
    //set the additional cost due to air temperature
    //finds the range the current air temperature is in and sets the additional cost accordingly
    //if not in any range, the delivery has no extra cost
    public void setATEF(City c) {
        try {
            double temp = c.getAir_temp();
            List<ATEF> atefList = atefRepository.findAll();
            for (ATEF atef : atefList) {
                if (temp >= atef.getLower() && temp <= atef.getUpper()) {
                    ATEF = atef.getPrice();

                }
            }

        } catch (Exception e) {
            System.out.println("No air temperature data");

        }


    }
    //set the additional cost due to wind speed
    //finds the range the current wind speed is in and sets the additional cost accordingly
    //if not in any range, the delivery has no extra cost
    public void setWSEF(City c) {
        try {
            double windSpeed = c.getWind_speed();
            List<WSEF> wsefList = wsefRepository.findAll();
            for (WSEF wsef : wsefList) {
                if (windSpeed >= wsef.getLower() && windSpeed <= wsef.getUpper()) {
                    WSEF = wsef.getPrice();

                }
            }
        } catch (Exception e) {
            System.out.println("No wind speed data");
        }

    }
    //returns the total fee for the delivery
    //if the delivery is by car, the only cost is the base fee
    //if the delivery is by scooter or bike, the total fee is the sum of the base fee and the additional costs
    public double getTotalFee(String city, String vehicle) {
        this.city = city;
        this.vehicle = vehicle;
        setRBF();
        if (vehicle.equals("Scooter") || vehicle.equals("Bike")) {
            List<City> c = cityRepository.findBystation(cityToStation.get(city));
            City newestData = c.get(c.size() - 1);
            System.out.println(newestData.toString());
            setWPEF(newestData);
            setATEF(newestData);
            setWSEF(newestData);

        }
        System.out.println("RBF: " + RBF + " ATEF: " + ATEF + " WSEF: " + WSEF + " WPEF: " + WPEF);
        totalFee = RBF + ATEF + WSEF + WPEF;
        return totalFee;
    }


    public String processPhenomenon(String phenomenonName) {
        if (phenomenonName.contains("rain")) {
            return "rain";
        } else if (phenomenonName.contains("snow")) {
            return "snow";
        } else if (phenomenonName.contains("sleet")) {
            return "sleet";
        } else if (phenomenonName.contains("glaze")) {
            return "glaze";
        } else if (phenomenonName.contains("hail")) {
            return "hail";
        } else if (phenomenonName.contains("thunder")) {
            return "thunder";
        } else {
            return phenomenonName;
        }
    }

}
