package tests;

import entities.ride;
import services.rideService;

public class MainClass {
    public static void main(String[] args) {
        ride r1 = new ride("Mahdi", "Grombalia", "Ghazela", "15:00", 1);
        ride r2 = new ride("Youssef", "Grombalia", "Ghazela", "15:00", 1);

        rideService rs = new rideService();
        //rs.addEntity(r1);
        //rs.deleteEntity(2);
        rs.updateEntity(r2, 1);
        System.out.println(rs.getallData());

    }
}
