package services;

import entities.Station;
import interfaces.Iservices;
import utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class StationServices implements Iservices<Station> {

    public void addEntity(Station station) {


    }

    public void addEntity2(Station station) {


        String requete = " INSERT INTO Station (name,address) Values (?,?)";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            //  pst.setInt(1,Commun_means_of_transport.getId());
            pst.setString(1,Station.getName());
            pst.setString(2,Station.getAddress());




            pst.executeUpdate();
            System.out.println(" added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }



    public void updateEntity(Station station) {


        String requete = "UPDATE Station SET name=?, address=? WHERE name=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            pst.setString(1, Station.getName());
            pst.setString(2, Station.getAddress());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Commun_means_of_transport Updated");
            } else {
                System.out.println("Commun_means_of_transport mch mawjoud");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteEntity(Station station) {

        String requete = "DELETE FROM Station WHERE name=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1,  Station.getName());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("station Deleted");
            } else {
                System.out.println("station not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public List<Station> getAllData() {

       List<Station> data = new ArrayList<>();
        String requete= "SELECT * FROM Station";
        try {
            Statement st = MyConnection.getInstance ().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Station s = new Station();
                s.setName(rs.getString("name"));
                s.setAddress(rs.getString("address"));


                data.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;

    }





    }











