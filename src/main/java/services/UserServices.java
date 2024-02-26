package services;


import Utiles.MyConnection;
import entities.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServices implements interfaces.IServices<Users> {
    @Override
    public void addEntity(Users users) {
        String requete= "INSERT INTO Users (nom,prenom) VALUES ('"+users.getNom()+"','"+users.getPrenom()+"')";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("User added!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addEntity2(Users users) {
        String requete= "INSERT INTO Users (nom,prenom) VALUES (?,?)";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1,users.getNom());
            pst.setString(2,users.getPrenom());
            pst.executeUpdate();
            System.out.println("User added 2 !!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void updateEntity(Users users,int id) {

    }

    @Override
    public void deleteEntity(Users users) {

    }

    @Override
    public List<Users> getAllData() {
        List<Users> data = new ArrayList<>();
        String requete = "SELECT * FROM Users";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs =st.executeQuery(requete);
            while(rs.next()){
                Users p = new Users();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                data.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }
}
