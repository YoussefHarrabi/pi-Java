package Services;

import Interfaces.CrudServices;
import models.Utilisateurs;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utiles.MyConnections;
import java.sql.ResultSet;
import java.util.ArrayList;
public class CrudUtilisateurs implements CrudServices<Utilisateurs> {

    @Override
    public void addEntity(Utilisateurs utilisateurs) {
        // Check if the email already exists in the database
        String email = utilisateurs.getEmail();
        String checkQuery = "SELECT COUNT(*) FROM Utilisateurs WHERE email='" + email + "'";
        try {
            Statement checkStatement = MyConnections.getInstance().getCnx().createStatement();
            ResultSet resultSet = checkStatement.executeQuery(checkQuery);
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0) {
                System.out.println("User with this email already exists in the database.");
                return; // Exit the method without adding the user
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while checking for existing email: " + e.getMessage());
            return; // Exit the method if an error occurs
        }

        // If the email doesn't exist, proceed with adding the user
        String requete = "INSERT INTO Utilisateurs (nom, prenom, email, `Mot de passe`, Rôle) VALUES ('" +
                utilisateurs.getNom() + "','" +
                utilisateurs.getPrenom() + "','" +
                utilisateurs.getEmail() + "','" +
                utilisateurs.getMotDePasse() + "','" +
                utilisateurs.getRole() + "')";
        try {
            Statement st = MyConnections.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("User added!!");
        } catch (SQLException e) {
            System.out.println("Error occurred while adding user: " + e.getMessage());
        }
    }






    @Override
    public void updateEntity(Utilisateurs utilisateurs) {
        // Check if the email already exists in the database, excluding the current user
        String email = utilisateurs.getEmail();
        int userId = utilisateurs.getId();
        String checkQuery = "SELECT COUNT(*) FROM Utilisateurs WHERE email='" + email + "' AND id<>" + userId;
        try {
            Statement checkStatement = MyConnections.getInstance().getCnx().createStatement();
            ResultSet resultSet = checkStatement.executeQuery(checkQuery);
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0) {
                System.out.println("Another user with this email already exists in the database.");
                return; // Exit the method without updating the user
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while checking for existing email: " + e.getMessage());
            return; // Exit the method if an error occurs
        }

        // If the email doesn't exist, proceed with updating the user
        String requete = "UPDATE Utilisateurs SET nom='" + utilisateurs.getNom() + "', " +
                "prenom='" + utilisateurs.getPrenom() + "', " +
                "email='" + utilisateurs.getEmail() + "', " +
                "`Mot de passe`='" + utilisateurs.getMotDePasse() + "', " +
                "Rôle='" + utilisateurs.getRole() + "' " +
                "WHERE id=" + utilisateurs.getId();
        try {
            Statement st = MyConnections.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("User updated!!");
        } catch (SQLException e) {
            System.out.println("Error occurred while updating user: " + e.getMessage());
        }
    }


    @Override
    public void deleteEntity(Utilisateurs utilisateurs) {
        String requete = "DELETE FROM Utilisateurs WHERE id=" + utilisateurs.getId();
        try {
            Statement st = MyConnections.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("User deleted!!");
        } catch (SQLException e) {
            System.out.println("Error occurred while deleting user: " + e.getMessage());
        }
    }

    // Method to fetch a user by email from the database
    public Utilisateurs getUserByEmail(String email) {
        String query = "SELECT * FROM Utilisateurs WHERE email=?";
        Utilisateurs user = null;

        try {
            PreparedStatement statement = MyConnections.getInstance().getCnx().prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String userEmail = resultSet.getString("email");
                String password = resultSet.getString("Mot de passe");
                String role = resultSet.getString("Rôle");

                user = new Utilisateurs(id, nom, prenom, userEmail, password, role);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user by email: " + e.getMessage());
        }

        return user;
    }

    @Override
    public List<Utilisateurs> getAllData() {
        List<Utilisateurs> userList = new ArrayList<>();

        String query = "SELECT * FROM Utilisateurs";
        try {
            Statement statement = MyConnections.getInstance().getCnx().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String motDePasse = resultSet.getString("Mot de passe");
                String role = resultSet.getString("Rôle");

                Utilisateurs user = new Utilisateurs(id, nom, prenom, email, motDePasse, role);
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching user data: " + e.getMessage());
        }

        return userList;
    }
}
