package com.example.fujitsudelivery;

import jakarta.persistence.*;
/*
    * Class to represent the cities table in the database
    * Each database row represents a weather data recording for a station at a given time
    * Each city has a station name, wmo station code, air temperature, wind speed, weather phenomenon and observation time
    * Purely data storage and getters/setters
    *
 */

@Entity
@Table(name = "cities")
public class City {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "station")
    String station;
    @Column(name = "wmo_station_code")
    String wmo_station_code;
    @Column(name = "air_temp")
    double air_temp;
    @Column(name = "wind_speed")
    double wind_speed;
    @Column(name = "weather_phenomenon")
    String weather_phenomenon;
    @Column(name = "observation_time")
    String observationTime;

    public City() {
    }
    public String getStation_name() {
        return station;
    }

    public void setStation_name(String station_name) {
        this.station = station_name;
    }

    public String getWmo_station_code() {
        return wmo_station_code;
    }

    public void setWmo_station_code(String wmo_station_code) {
        this.wmo_station_code = wmo_station_code;
    }

    public double getAir_temp() {
        return air_temp;
    }

    public void setAir_temp(double air_temp) {
        this.air_temp = air_temp;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getWeather_phenomenon() {
        return weather_phenomenon;
    }

    public void setWeather_phenomenon(String weather_phenomenon) {
        this.weather_phenomenon = weather_phenomenon;
    }

    public String getObservationTime() {
        return observationTime;
    }

    public void setObservationTime(String observation_time) {
        this.observationTime = observation_time;
    }

    @Override
    public String toString() {
        return "City{" +
                "station_name='" + station + '\'' +
                ", wmo_station_code='" + wmo_station_code + '\'' +
                ", air_temp=" + air_temp +
                ", wind_speed=" + wind_speed +
                ", weather_phenomenon='" + weather_phenomenon + '\'' +
                ", observation_time='" + observationTime + '\'' +
                '}';
    }
}
