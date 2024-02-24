package services;

import entities.Incident;
import entities.Injury;
import interfaces.IServices;
import utiles.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InjuryServices implements IServices<Injury> {
    @Override
    public void addEntity(Injury injury) {
        String requete1 = "INSERT INTO injury (incidentId,type,severity) VALUES ('"+injury.getIncidentId()+"','"+ injury.getType()+"','"+ injury.getSeverity()+"')";

        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete1);
            System.out.println("injury added!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateEntity(Injury injury) {
        String requete = "UPDATE injury SET type='" + injury.getType() + "', " +
                "severity='" + injury.getSeverity() + "', " +
                "WHERE id=" + injury.getId();
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Incident updated!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteEntity(Injury injury) {
        String requete = "DELETE FROM injury WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,injury.getIncidentId());

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

                data.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
}
