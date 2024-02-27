package services;

import entities.Incident;
import interfaces.IServices;
import Utiles.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IncidentServices implements IServices<Incident> {

    @Override
    public void addEntity(Incident incident) {
        String requete = "INSERT INTO incident (Type,Place,Hour,Description) VALUES ('"+ incident.getType()+"','"+ incident.getPlace()+"','"+incident.getHour()+"','"+incident.getDescription()+"')";

        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("incident added!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateEntity(Incident incident,int id) {
        String query = "UPDATE incident SET Type=?, Place=?, Hour=?, Description=? WHERE IncidentId="+id;
        try {
            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
            preparedStatement.setString(1, incident.getType());
            preparedStatement.setString(2, incident.getPlace());
            preparedStatement.setString(3, incident.getHour());
            preparedStatement.setString(4, incident.getDescription());


            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Incident with ID " + id + " updated successfully");
            } else {
                System.out.println("No Incident found with ID " + id + " for updating");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }





    @Override
    public void deleteEntity(Incident incident) {
        String requete = "DELETE FROM incident WHERE IncidentId=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,incident.getIncidentId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Incident Deleted");
            } else {
                System.out.println("Incident not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Incident> getAllData() {
        List<Incident> data = new ArrayList<>();
        String requete = "SELECT * FROM incident";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()){
                Incident i = new Incident();
                i.setIncidentId(rs.getInt(1));
                i.setType(rs.getString("Type"));
                i.setPlace(rs.getString("Place"));
                i.setHour(rs.getString("Hour"));
                i.setDescription(rs.getString("Description"));
                i.setDate(rs.getDate("Date"));
                data.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    public List<Incident> rechercherParMotCle(String keyword) {
        // Récupérer la liste complète des incidents depuis la base de données
        List<Incident> incidentList = getAllData();

        List<Incident> incidentsTrouves = new ArrayList<>();

        // Vous pouvez ajuster la condition de recherche selon vos besoins
        for (Incident inc : incidentList) {
            // Check if the keyword exists in any relevant attribute of the incident
            if (containsKeyword(inc, keyword)) {
                incidentsTrouves.add(inc);
            }
        }

        return incidentsTrouves;
    }

    private boolean containsKeyword(Incident incident, String keyword) {
        // Check if the keyword exists in any attribute of the incident
        // Adjust this method based on the attributes of the Incident class
        return incident.getType() != null && incident.getType().toLowerCase().contains(keyword.toLowerCase()) ||
                incident.getDescription() != null && incident.getDescription().toLowerCase().contains(keyword.toLowerCase()) ||
                incident.getHour() != null && incident.getHour().toLowerCase().contains(keyword.toLowerCase()) ||
                incident.getPlace() != null && incident.getPlace().toLowerCase().contains(keyword.toLowerCase()) ||
                incident.getDate() != null && (String.valueOf(incident.getDate())).toLowerCase().contains(keyword.toLowerCase());

        // Add additional attributes as needed
    }


}
