package utils;
import java.sql.*;
public class MyConnection {
    private final String url= "jdbc:mysql://localhost:3306/pi java";
    private final String login= "root";
    private final String mdp= "";
   private Connection cnx;
   static MyConnection instance;



    private MyConnection(){
        try {
            cnx = DriverManager.getConnection(url, login, mdp);
            System.out.println("connexion établie !") ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public Connection getCnx()
    {
        return cnx;
    }
    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }
}
