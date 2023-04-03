package com.example.fujitsudelivery;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.spring.annotations.Recurring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
/*
    * This class is used to schedule the weather data fetching
    * It is used to fetch the weather data every 15 minutes after a full hour
    * It uses the JobRunr library with cron expression
 */
@Service
public class Scheduler {
    private final JobScheduler jobScheduler;
    @Autowired
    private Weather weather;
    @Autowired
    public Scheduler(JobScheduler jobScheduler) {
        this.jobScheduler = jobScheduler;
    }
    //fetches the weather data every 15 minutes after a full hour by callin the getWeatherData method
    @Recurring(cron = "15 * * * *")
    public void getWeather() throws ParserConfigurationException, IOException, SAXException {
        weather.getWeatherData();
    }
}
