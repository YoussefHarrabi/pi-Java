package services;

import entities.ride;
import interfaces.Iride;
import utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class rideService implements Iride<ride> {
    @Override
    public void addEntity(ride ride) {
        String requete = "INSERT INTO ride (driver, startLocation, endLocation, departureTime, availableSeats) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = MyConnection.getInstance().getCon().prepareStatement(requete);
            preparedStatement.setString(1, ride.getDriver());
            preparedStatement.setString(2, ride.getStartLocation());
            preparedStatement.setString(3, ride.getEndLocation());
            preparedStatement.setString(4, ride.getDepartureTime());
            preparedStatement.setInt(5, ride.getAvailableseats());

            preparedStatement.executeUpdate();
            System.out.println("Ride added!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ride getride(int id) {
        String query = "SELECT * FROM ride WHERE ride_id = ?";
        ride p = new ride();

        try {
            PreparedStatement preparedStatement = MyConnection.getInstance().getCon().prepareStatement(query);

            preparedStatement.setInt(1, id);
            Statement srt = MyConnection.getInstance().getCon().createStatement();
            ResultSet rs = srt.executeQuery(query);
            while (rs.next()){

            p.setId_driver(id);
            p.setDriver(rs.getString("driver"));
            p.setStartLocation(rs.getString("startLocation"));
            p.setEndLocation(rs.getString("endLocation"));
            p.setDepartureTime(rs.getString("departureTime"));
            p.setAvailableseats(rs.getInt("availableSeats"));
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return p;
    }


    @Override
    public void updateEntity(ride ride, int id) {
        String query = "UPDATE ride SET driver = ?, startLocation = ?, endLocation = ?, departureTime = ?, availableSeats = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = MyConnection.getInstance().getCon().prepareStatement(query);
            preparedStatement.setString(1, ride.getDriver());
            preparedStatement.setString(2, ride.getStartLocation());
            preparedStatement.setString(3, ride.getEndLocation());
            preparedStatement.setString(4, ride.getDepartureTime());
            preparedStatement.setInt(5, ride.getAvailableseats());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
            System.out.println("Updated!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEntity(int id) {
        String query = "DELETE FROM ride WHERE id = ?";
        try {
            PreparedStatement preparedStatement = MyConnection.getInstance().getCon().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Deleted!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ride> getallData() {
        List<ride> list = new ArrayList<>();
        String query = "SELECT * FROM ride";
        try {
            Statement srt = MyConnection.getInstance().getCon().createStatement();
            ResultSet rs = srt.executeQuery(query);
            while(rs.next()){
                ride p = new ride();
                p.setId_driver(rs.getInt("id"));
                p.setDriver(rs.getString("driver"));
                p.setStartLocation(rs.getString("startLocation"));
                p.setEndLocation(rs.getString("endLocation"));
                p.setDepartureTime(rs.getString("departureTime"));
                p.setAvailableseats(rs.getInt("availableSeats"));


                list.add(p);
            }
            System.out.println("Done!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}

