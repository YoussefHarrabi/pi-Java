package services;

import entities.Incident;
import entities.Injury;
import interfaces.IServices;
import Utiles.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InjuryServices implements IServices<Injury> {
    @Override
    public void addEntity(Injury injury) {
        String requete1 = "INSERT INTO injury (incidentId,type,Number_pers,severity) VALUES ('"+injury.getIncidentId()+"','"+ injury.getType()+"','"+injury.getNumber_pers()+"','"+ injury.getSeverity()+"')";

        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete1);
            System.out.println("injury added!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateEntity(Injury injury, int idI) {
        String query = "UPDATE injury SET Type=?, Severity=?, Number_pers=? WHERE id="+idI;
        try {
            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
            preparedStatement.setString(1, injury.getType());
            preparedStatement.setInt(3, injury.getNumber_pers());
            preparedStatement.setString(2, injury.getSeverity());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Injury with ID " + idI + " updated successfully");
            } else {
                System.out.println("No Injury found with ID " + idI + " for updating");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void deleteEntity(Injury injury) {
        String requete = "DELETE FROM injury WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,injury.getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Injury Deleted");
            } else {
                System.out.println("Injury not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Injury> getAllData() {
        List<Injury> data = new ArrayList<>();
        String requete = "SELECT * FROM injury";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()){
                Injury i = new Injury();
                i.setId(rs.getInt(1));
                i.setIncidentId(rs.getInt(2));
                i.setType(rs.getString("type"));
                i.setSeverity(rs.getString("severity"));
                i.setNumber_pers(rs.getInt("Number_pers"));

                data.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    public List<Injury> rechercherParMotCle(String keyword) {
        // Récupérer la liste complète des blessures depuis la base de données
        List<Injury> injuryList = getAllData();

        List<Injury> injuriesTrouves = new ArrayList<>();

        // Vous pouvez ajuster la condition de recherche selon vos besoins
        for (Injury injury : injuryList) {
            // Check if the keyword exists in any relevant attribute of the injury
            if (containsKeyword(injury, keyword)) {
                injuriesTrouves.add(injury);
            }
        }

        return injuriesTrouves;
    }

    private boolean containsKeyword(Injury injury, String keyword) {
        // Check if the keyword exists in any attribute of the injury
        // Adjust this method based on the attributes of the Injury class
        return String.valueOf(injury.getIncidentId()).contains(keyword) ||
                injury.getType() != null && injury.getType().toLowerCase().contains(keyword.toLowerCase()) ||
                injury.getSeverity() != null && injury.getSeverity().toLowerCase().contains(keyword.toLowerCase()) ||
                String.valueOf(injury.getNumber_pers()).contains(keyword);
        // Add additional attributes as needed
    }

}
