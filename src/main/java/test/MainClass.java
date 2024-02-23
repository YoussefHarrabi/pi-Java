package test;

import Utiles.MyConnection;
import entities.Users;
import services.UserServices;

public class MainClass {

    public static void main(String[] args) {
        MyConnection mc= new MyConnection();
        Users p = new Users("Mohamed","Negzaoui");
        UserServices ps = new UserServices();
        //ps.addEntity2(p);
        System.out.println(ps.getAllData());
    }
}
