package dao;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * @author Sérgio Felipe Starke
 */
public class Conexao {

    public static Connection connection() {
        java.sql.Connection conn;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/fixed", "root", ""
            );
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na Conexão...");
            return null;
        }
    }

}
