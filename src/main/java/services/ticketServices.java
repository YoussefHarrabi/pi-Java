package services;

import utils.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class ticketServices {

    // Supposons que vous avez une connexion à la base de données ici
    public MyConnection cnx;



    public Time getDepartureTime(int ticketId) {
        // À remplacer par votre logique pour obtenir le temps de départ depuis la base de données
        Time temps_depart = null;

        try {
            String query = "SELECT temps_depart FROM tickets WHERE ticket_id = ?";
         //   PreparedStatement preparedStatement = cnx.getCnx().prepareStatement(query);
            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
            preparedStatement.setInt(1, ticketId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                temps_depart = resultSet.getTime("temps_depart");
            }

            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon votre logique
        }

        return temps_depart;
    }

    // Méthode pour obtenir la durée du trajet
    public Time getTrajetDuration(int ticketId) {
        // À remplacer par votre logique pour obtenir la durée du trajet depuis la base de données
        Time duree_trajet = null;

        try {
            String query = "SELECT duree_trajet FROM tickets WHERE ticket_id = ?";
            //PreparedStatement preparedStatement = cnx.getCnx().prepareStatement(query);
            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
            preparedStatement.setInt(1, ticketId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                duree_trajet = resultSet.getTime("duree_trajet");
            }

            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon votre logique
        }

        return duree_trajet;
    }
}
