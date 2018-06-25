package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private final String DB_PATH = "dbhotel";

    /**
     * Retorna uma instância da conexão com o banco de dados
     *
     * @return Connection
     */
    
    public Connection getConexao() {
        Connection connection = null;
        try {
            if (connection == null) {
                String driverName = "com.mysql.jdbc.Driver";
                Class.forName(driverName);
                String url = "jdbc:mysql://localhost:3306/"+DB_PATH;
                connection = DriverManager.getConnection(url, "root", "root123");
                System.out.println("conectado com sucesso");
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Fecha conexão com o banco de dados
     *
     * @param connection
     */
    public void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}