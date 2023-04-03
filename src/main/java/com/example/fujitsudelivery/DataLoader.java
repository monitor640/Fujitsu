package com.example.fujitsudelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

/*
    * This class is used to load the given default values for WPEF into the database
 */

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private WPEFRepository wpefRepository;
    @Autowired
    private PhenomenonRepository phenomenonRepository;
    @Autowired
    private RBFRepository rbfRepository;
    @Autowired
    private ATEFRepository atefRepository;
    @Autowired
    private WSEFRepository wsefRepository;


    private void loadExtraFees(){
        Phenomenon p1 = new Phenomenon();
        p1.setPhenomenon("rain");

        Phenomenon p2 = new Phenomenon();
        p2.setPhenomenon("snow");
        Phenomenon p3 = new Phenomenon();
        p3.setPhenomenon("sleet");

        Phenomenon p4 = new Phenomenon();
        p4.setPhenomenon("hail");
        Phenomenon p5 = new Phenomenon();
        p5.setPhenomenon("thunder");
        Phenomenon p6 = new Phenomenon();
        p6.setPhenomenon("glaze");

        WPEF e1 = new WPEF();
        e1.setPhenomenon(Collections.singleton(p1));
        e1.setPrice(0.5);

        WPEF e2 = new WPEF();
        e2.setPhenomenon(Set.of(p2,p3));
        e2.setPrice(1);

        WPEF e3 = new WPEF();
        e3.setPhenomenon(Set.of(p4,p5,p6));
        e3.setPrice(99);


        wpefRepository.save(e1);
        wpefRepository.save(e2);
        wpefRepository.save(e3);
        phenomenonRepository.save(p1);
        phenomenonRepository.save(p2);
        phenomenonRepository.save(p3);
        phenomenonRepository.save(p4);
        phenomenonRepository.save(p5);
        phenomenonRepository.save(p6);

        ATEF a1 = new ATEF();
        a1.setLower(-10);
        a1.setUpper(0);
        a1.setPrice(0.5);
        ATEF a2 = new ATEF();
        a2.setLower(-999999999);
        a2.setUpper(-10);
        a2.setPrice(1);
        atefRepository.save(a1);
        atefRepository.save(a2);

        WSEF w1 = new WSEF();
        w1.setLower(10);
        w1.setUpper(20);
        w1.setPrice(0.5);
        WSEF w2 = new WSEF();
        w2.setLower(20);
        w2.setUpper(999999999);
        w2.setPrice(999999999);
        wsefRepository.save(w1);
        wsefRepository.save(w2);

        RBF r1 = new RBF();
        r1.setCity("Tallinn");
        r1.setRBF(4);
        r1.setVehicle("Car");
        RBF r2 = new RBF();

        r2.setCity("Tallinn");
        r2.setRBF(3.5);
        r2.setVehicle("Scooter");

        RBF r3 = new RBF();
        r3.setCity("Tallinn");
        r3.setRBF(3);
        r3.setVehicle("Bike");
        //tartu car 3.5
        RBF r4 = new RBF();
        r4.setCity("Tartu");
        r4.setRBF(3.5);
        r4.setVehicle("Car");
        //tartu scooter 3
        RBF r5 = new RBF();
        r5.setCity("Tartu");
        r5.setRBF(3);
        r5.setVehicle("Scooter");
        //tartu bike 2.5
        RBF r6 = new RBF();
        r6.setCity("Tartu");
        r6.setRBF(2.5);
        r6.setVehicle("Bike");
        //Pärnu car 3
        RBF r7 = new RBF();
        r7.setCity("Pärnu");
        r7.setRBF(3);
        r7.setVehicle("Car");
        //Pärnu scooter 2.5
        RBF r8 = new RBF();
        r8.setCity("Pärnu");
        r8.setRBF(2.5);
        r8.setVehicle("Scooter");
        //Pärnu bike 2
        RBF r9 = new RBF();
        r9.setCity("Pärnu");
        r9.setRBF(2);
        r9.setVehicle("Bike");
        rbfRepository.save(r1);
        rbfRepository.save(r2);
        rbfRepository.save(r3);
        rbfRepository.save(r4);
        rbfRepository.save(r5);
        rbfRepository.save(r6);
        rbfRepository.save(r7);
        rbfRepository.save(r8);
        rbfRepository.save(r9);
    }
    @Autowired
    public DataLoader(WPEFRepository wpefRepository, PhenomenonRepository phenomenonRepository) {
        this.wpefRepository = wpefRepository;
        this.phenomenonRepository = phenomenonRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //uncomment this to populate wpef and phenomenon tables with default values
        //loadExtraFees();
    }
}
