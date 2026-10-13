package org.example;

import org.example.Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;


public class App {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection database = DatabaseConnection.getInstance();
        Connection connection = database.getConnection();
    }
}
