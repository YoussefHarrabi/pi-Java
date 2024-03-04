package services;

import entities.Commun_means_of_transport;
import interfaces.Iservices;
import javafx.collections.ObservableList;
import utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Commun_means_of_transportServices implements Iservices<Commun_means_of_transport> {
    public void addEntity(Commun_means_of_transport Commun_means_of_transport) {
    }

    public static void addEntity2(Commun_means_of_transport Commun_means_of_transport) {
        String requete = " INSERT INTO Commun_means_of_transport (registration_number,type) Values (?,?)";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            //  pst.setInt(1,Commun_means_of_transport.getId());
            pst.setString(1, Commun_means_of_transport.getRegistration_number());
            pst.setString(2, Commun_means_of_transport.getType());


            pst.executeUpdate();
            System.out.println("Commun_means_of_transport added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateEntity(Commun_means_of_transport Commun_means_of_transport) {
        String requete = "UPDATE Commun_means_of_transport SET registration_number=?, type=? WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            pst.setString(1, Commun_means_of_transport.getRegistration_number());
            pst.setString(2, Commun_means_of_transport.getType());
            pst.setInt(3, Commun_means_of_transport.getId());

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

    @Override
    public void deleteEntity(Commun_means_of_transport Commun_means_of_transport) {


        String requete = "DELETE FROM Commun_means_of_transport WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, Commun_means_of_transport.getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Commun_means_of_transport Deleted");
            } else {
                System.out.println("Commun_means_of_transport not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public List<Commun_means_of_transport> getAllData() {
        List<Commun_means_of_transport> data = new ArrayList<>();
        String requete = "SELECT * FROM commun_means_of_transport";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Commun_means_of_transport p = new Commun_means_of_transport();
                p.setId(rs.getInt("id"));
                p.setRegistration_number(rs.getString("registration_number"));
                p.setType(rs.getString("type"));


                data.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    /*public boolean isRegistrationNumberExists(int registrationNumber) {
        String query = "SELECT * FROM Commun_means_of_transport WHERE registration_number = ?";
        try (PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query)) {
            preparedStatement.setInt(1, registrationNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Si un enregistrement est trouvé, cela signifie que le numéro existe déjà.
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    } */




    public boolean isRegistrationNumberExists(String registrationNumber) {
        String query = "SELECT * FROM Commun_means_of_transport WHERE registration_number = ?";
        try (PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query)) {
            preparedStatement.setString(1, registrationNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Si un enregistrement est trouvé, cela signifie que le numéro existe déjà.
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public long countBuses() {
        Commun_means_of_transportServices moyenServices = new Commun_means_of_transportServices();
        List<Commun_means_of_transport> transports = moyenServices.getAllData();

        // Use Java Streams to count the number of buses
        return transports.stream()
                .filter(transport -> "bus".equals(transport.getType()))
                .count();
    }/*


    public int getCountOfPaniersWithTotalPriceGreaterThan100() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM panier p JOIN product pr ON p.id_produit = pr.id WHERE p.quantite * pr.price > 100";

        try (PreparedStatement pst = new MyConnection().getCnx().prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }


    public int getCountOfPaniersWithTotalPriceLessThanEqual100() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM panier p JOIN product pr ON p.id_produit = pr.id WHERE p.quantite * pr.price <= 100";

        try (PreparedStatement pst = new MyConnection().getCnx().prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }


    */

    public long countTrains() {
        Commun_means_of_transportServices moyenServices = new Commun_means_of_transportServices();
        List<Commun_means_of_transport> transports = moyenServices.getAllData();

        // Use Java Streams to count the number of trains
        return transports.stream()
                .filter(transport -> "train".equals(transport.getType()))
                .count();
    }



}

