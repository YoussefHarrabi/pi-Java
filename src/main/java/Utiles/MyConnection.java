package Utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public MyConnection() {

    }

    // Méthode pour obtenir une connexion à la base de données
    public static Connection getConnection() {
        Connection connection = null; // Ajout de la déclaration de la variable
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
            System.out.println("connection etablie!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Gérer l'exception (journalisation, message utilisateur, etc.)
        }
        return connection;
    }
}
