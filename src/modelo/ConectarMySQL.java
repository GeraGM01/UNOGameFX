package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectarMySQL {

    public static final String URL = "jdbc:mysql://localhost:3306/unobd";
    public static final String USER = "root";
    public static final String PSWD = "gerazm";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(URL, USER, PSWD);
        } catch (Exception e) {
            System.out.println("Error ! " + e.getMessage());
        }
        return connection;
    }
}
