package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private final String url="jdbc:mysql://localhost:3306/mahdi";
    private final String login="root";
    private final String password="";
    public static MyConnection instance;
    Connection con;
    private MyConnection(){
        try {
            con = DriverManager.getConnection(url, login, password);
            System.out.println("Connection established!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getCon() {
        return con;
    }

    public static MyConnection getInstance() {
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }
}
