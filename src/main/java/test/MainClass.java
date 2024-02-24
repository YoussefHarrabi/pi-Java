package test;

import entities.Commun_means_of_transport;
import entities.Station;
import services.Commun_means_of_transportServices;
import services.StationServices;
import utils.MyConnection;

public class MainClass {
    public static void main(String[] args)
    { MyConnection mc =  MyConnection.getInstance();

        Commun_means_of_transport p = new Commun_means_of_transport ( 235,"kia");
        //Commun_means_of_transportServices ps = new Commun_means_of_transportServices();
        //ps.addEntity2(p);
        //System.out.println(ps.getAllData());
       // p.setId(2);
        //ps.updateEntity(p);
        //ps.deleteEntity(p);
       // System.out.println(ps.getAllData());

        Station s=new Station("lll","LaSeine");
        StationServices pst = new StationServices();

        s.setName("birHkaiem");
        //pst.deleteEntity(s);
       // pst.updateEntity(s);



        // StationServices pst = new StationServices();
        pst.addEntity2(s);
        //System.out.println(ps.getAllData());



    }
}
