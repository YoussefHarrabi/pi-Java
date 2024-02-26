package tests;

import entities.Incident;
import entities.Injury;
import services.IncidentServices;
import services.InjuryServices;

import static java.time.LocalTime.now;

public class Main {
    public static void main(String[] args) {
        // MyConnection mc = new MyConnection();
        Injury p = new Injury(27,"human",2,"severe");
        InjuryServices ps = new InjuryServices();
       // ps.updateEntity(p);

        System.out.println(ps.getAllData());
    }
}
