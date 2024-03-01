package Services;
import Interfaces.CrudServices;
import models.Utilisateurs;

import java.sql.*;
import java.util.List;
import utiles.MyConnections;

import java.util.ArrayList;

public class StationServices {

    public List<String> getAllStationNames() {
        List<String> stationNames = new ArrayList<>();

        try {
            Connection connection = MyConnections.getInstance().getCnx();
            String query = "SELECT station_name FROM station"; // Assuming "Station" is the table name
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String stationName = resultSet.getString("station_name");
                stationNames.add(stationName);
            }

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }

        return stationNames;
    }

    public int calculateDistance(String fromStation, String toStation) {
        // Get the order of the fromStation and toStation
        int fromOrder = getStationOrder(fromStation);
        int toOrder = getStationOrder(toStation);

        // If either of the stations is null or not found, return a default distance
        if (fromOrder == -1 || toOrder == -1) {
            return -1; // Default distance or invalid input
        }

        // Calculate distance based on the order of stations
        return Math.abs(toOrder - fromOrder); // Assuming stations are ordered consecutively
    }

    private int getStationOrder(String stationName) {
        try {
            Connection connection = MyConnections.getInstance().getCnx();
            String query = "SELECT station_order FROM station WHERE station_name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, stationName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("station_order");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }
        return -1; // Station order not found
    }
}

