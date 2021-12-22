package dao;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * @author Sérgio Felipe Starke
 */ 
public class Conexao {

    public static Connection connection() {
        java.sql.Connection connect;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/fixed", "root", ""
            );
            return connect;
        } catch (ClassNotFoundException | SQLException e) {
 JOptionPane.showMessageDialog(null,"Erro na conexão do Banco de Dados: \n"+ e);
            return null;
        }
    }

}
