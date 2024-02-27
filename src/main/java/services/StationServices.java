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
        // Logique pour ajouter une entité à la base de données
    }

    public static void addEntity2(Station station) {
        String requete = "INSERT INTO Station (name, address) VALUES (?, ?)";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, station.getName());
            pst.setString(2, station.getAddress());

            pst.executeUpdate();
            System.out.println("Station added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateEntity(Station station) {
        String requete = "UPDATE Station SET name=?, address=? WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            pst.setString(1, station.getName());
            pst.setString(2, station.getAddress());
            pst.setInt(3, station.getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Station Updated");
            } else {
                System.out.println("Station not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteEntity(Station station) {
        String requete = "DELETE FROM Station WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, station.getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Station Deleted");
            } else {
                System.out.println("Station not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Station> getAllData() {
        List<Station> data = new ArrayList<>();
        String requete = "SELECT * FROM Station";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Station s = new Station();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAddress(rs.getString("address"));
                data.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    public static List<Station> getAllData1() {
        List<Station> data = new ArrayList<>();
        String requete = "SELECT * FROM Station";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Station s = new Station();
                s.setId(rs.getInt("id"));
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
