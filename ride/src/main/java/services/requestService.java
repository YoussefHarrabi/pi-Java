package services;
import entities.request;
import entities.ride;
import interfaces.Irequest;
import interfaces.Iride;
import utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class requestService implements Irequest<request> {
    @Override
public void addRequest(request request) {
    String requete = "INSERT INTO request (startLocation, endLocation, departureTime, availableseats) VALUES(?,?,?,?)";
    try {
        PreparedStatement preparedStatement = MyConnection.getInstance().getCon().prepareStatement(requete);
        preparedStatement.setString(1, request.getStartLocation());
        preparedStatement.setString(2, request.getEndLocation());
        preparedStatement.setString(3, request.getDepartureTime());
        preparedStatement.setInt(4, request.getAvailableseats());

        preparedStatement.executeUpdate();
        System.out.println("request added!");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

    public request getrequest(int id) {
        String query = "SELECT * FROM request WHERE id_request = ?";
        request p = new request();

        try {
            PreparedStatement preparedStatement = MyConnection.getInstance().getCon().prepareStatement(query);

            preparedStatement.setInt(1, id);
            Statement srt = MyConnection.getInstance().getCon().createStatement();
            ResultSet rs = srt.executeQuery(query);
            while (rs.next()){
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
    public void updateRequest(request request, int id) {
        String query = "UPDATE request SET startLocation = ?, endLocation = ?, departureTime = ?, availableSeats = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = MyConnection.getInstance().getCon().prepareStatement(query);
            preparedStatement.setString(1, request.getStartLocation());
            preparedStatement.setString(2, request.getEndLocation());
            preparedStatement.setString(3, request.getDepartureTime());
            preparedStatement.setInt(4, request.getAvailableseats());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            System.out.println("Updated!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRequest(int id) {
        String query = "DELETE FROM request WHERE id = ?";
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
    public List<request> getallrequest() {
        List<request> list = new ArrayList<>();
        String query = "SELECT * FROM request";
        try {
            Statement srt = MyConnection.getInstance().getCon().createStatement();
            ResultSet rs = srt.executeQuery(query);
            while(rs.next()){
                request p = new request();
                p.setId_request(rs.getInt("id"));
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


