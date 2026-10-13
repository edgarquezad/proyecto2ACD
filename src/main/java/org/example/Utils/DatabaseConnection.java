package org.example.Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException {
        try {
            String driver = "org.mariadb.jdbc.Driver";
            Class.forName(driver);

            String url = "jdbc:mariadb://localhost:3306/proyectotm2";
            String user = "root";
            String password = "";

            this.connection = DriverManager.getConnection(url, user, password);

            System.out.println("Conexión realizada correctamente");

        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encuentra el driver de MariaDB", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }
}
