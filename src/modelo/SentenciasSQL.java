package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SentenciasSQL {

    private final ConectarMySQL conecta;

    public SentenciasSQL() {
        conecta = new ConectarMySQL();
    }

    public String consultaDB(String correo, String contrasena) {
        String ban = "incorrecto";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet rs = null;

        try {
            connection = conecta.getConnection();
            selectStmt = connection.prepareStatement("SELECT Correo FROM Credenciales WHERE Correo = ? AND Clave = ?");
            selectStmt.setString(1, correo);
            selectStmt.setString(2, contrasena);
            rs = selectStmt.executeQuery();
            if (rs.next()) {
                ban = "correcto";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (selectStmt != null) selectStmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ban;
    }
}

