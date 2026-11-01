package org.example.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Metadatos {

    private Connection conexion;

    public Metadatos() throws SQLException {
        conexion = DatabaseConnection.getInstance().getConnection();
    }

    public void mostrarMetadatos() {

        try {
            DatabaseMetaData metaData = conexion.getMetaData();

            System.out.println("\n--- METADATOS DE LA BASE DE DATOS ---");
            System.out.println("Base de datos: " +
                    metaData.getDatabaseProductName());
            System.out.println("Versión: " +
                    metaData.getDatabaseProductVersion());
            System.out.println("Driver: " +
                    metaData.getDriverName());
            System.out.println("URL: " +
                    metaData.getURL());

            System.out.println("\n--- TABLAS ---");

            ResultSet tablas = metaData.getTables(
                    conexion.getCatalog(),
                    null,
                    "%",
                    new String[]{"TABLE"}
            );

            while (tablas.next()) {
                System.out.println(
                        "Tabla: " + tablas.getString("TABLE_NAME")
                );
            }

            tablas.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void exportarMetadatos() {

        try (PrintWriter archivo = new PrintWriter(
                new FileWriter("metadatos.txt"))) {

            DatabaseMetaData metaData = conexion.getMetaData();

            archivo.println("--- METADATOS DE LA BASE DE DATOS ---");
            archivo.println("Base de datos: " +
                    metaData.getDatabaseProductName());
            archivo.println("Versión: " +
                    metaData.getDatabaseProductVersion());
            archivo.println("Driver: " +
                    metaData.getDriverName());
            archivo.println("URL: " +
                    metaData.getURL());

            archivo.println("\n--- TABLAS ---");

            ResultSet tablas = metaData.getTables(
                    conexion.getCatalog(),
                    null,
                    "%",
                    new String[]{"TABLE"}
            );

            while (tablas.next()) {
                archivo.println(
                        "Tabla: " + tablas.getString("TABLE_NAME")
                );
            }

            tablas.close();

            System.out.println(
                    "Metadatos exportados a metadatos.txt"
            );

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
