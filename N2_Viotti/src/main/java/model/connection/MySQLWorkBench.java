package model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLWorkBench {

    private static Connection conn;

    // Método para obter a conexão
    public static Connection getConnection() {
        if (conn == null) {
            try {
               
                String url = "jdbc:mysql://localhost:3306/n2vioti2";
                String user = "root";
                String password = "1234";
                
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Conexão estabelecida com sucesso!");

            } catch (SQLException e) {
                System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão fechada com sucesso.");
                conn = null;  // Definir a conexão como null após fechar
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        } else {
            System.err.println("Nenhuma conexão aberta para fechar.");
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt) {
       
    }
}