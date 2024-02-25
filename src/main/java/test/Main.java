package test;

import Services.CrudUtilisateurs;
import utiles.MyConnections;
import models.Utilisateurs;


public class Main {

    public static void main(String[] args) {
        MyConnections mc= new MyConnections();
        Utilisateurs userToAdd = new Utilisateurs("Youssef", "Harrabi", "Youssefharrabi@gmail.Com", "youssef", "admin");
        Utilisateurs userToAdd2 = new Utilisateurs("Yousssef", "Harrdgabi", "Youssefharrabqsfi@gmail.Com", "youssef", "admin");

        Utilisateurs updatedUser = new Utilisateurs(3, "UpdatedName", "UpdatedLastName", "updated@example.com", "updatedpassword", "user");
        CrudUtilisateurs ps = new CrudUtilisateurs() ;
        //ps.addEntity(userToAdd);
        //ps.addEntity(userToAdd2);
        //ps.updateEntity(updatedUser);
        ps.deleteEntity(updatedUser);






    }
}
