package utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnections {

    private final String url="jdbc:mysql://localhost:3306/pi java";
    private final String login="root";
    private final String mdp="";
    public  static MyConnections instance;

    Connection cnx;

    public MyConnections(){
        try {
            cnx = DriverManager.getConnection(url,login,mdp);
            System.out.println("Connexion établie!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }
    public Connection getCnx(){
        return cnx;

    }

    public static MyConnections getInstance() {
        if (instance == null) {
            instance = new MyConnections();
        }
        return instance;
    }

}
