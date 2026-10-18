package org.example;

import org.example.Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {

        try {
            DatabaseConnection database = DatabaseConnection.getInstance();
            Connection connection = database.getConnection();


            Scanner entrada = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n===== ASOCIACIÓN DE JUEGOS DE MESA =====");
                System.out.println(
                        "1. Gestionar juegos de mesa \n" +
                                "2. Gestionar socias \n" +
                                "3. Gestionar partidas \n" +
                                "4. Gestionar invitadas \n" +
                                "5. Gestionar datos de la asociación \n" +
                                "6. Consultar metadatos de la base de datos\n" +
                                "7. Exportar datos a XML\n" +
                                "8. Exportar datos a JSON \n" +
                                "0. Salir \n");
                System.out.print("Elige una opción: ");

                while (!entrada.hasNextInt()) {
                    System.out.println("Introduce un número válido.");
                    entrada.next();
                    System.out.print("Elige una opción: ");
                }
                opcion = entrada.nextInt();


                switch (opcion) {
                    case 1:
                        int opcionJuegos;
                        System.out.println("Gestión de juegos de mesa");
                        System.out.println(
                                "1. Añadir juego \n" +
                                        "2. Mostrar juegos \n" +
                                        "3. Modificar juego \n" +
                                        "4. Eliminar juego \n" +
                                        "0. Volver al menú principal \n");


                        opcionJuegos = entrada.nextInt();

                        switch (opcionJuegos) {
                            case 1:
                                System.out.println("add Games / añadir juego");
                                break;
                            case 2:
                                System.out.println("view Games / mostrar juego");
                                break;
                            case 3:
                                System.out.println("edit Games / editar juego");
                                break;
                            case 4:
                                System.out.println("delete Games / eliminar juego");
                                break;
                            case 0:
                                System.out.println("return home / regresar al menu ");
                                break;
                        }


                        break;
                    case 2:
                        System.out.println("Gestión de socias");
                        break;
                    case 3:
                        System.out.println("Gestión de partidas");
                        break;
                    case 4:
                        System.out.println("Gestión de invitadas");
                        break;
                    case 5:
                        System.out.println("Datos de la asociación");
                        break;
                    case 6:
                        System.out.println("Metadatos de la base de datos");
                        break;
                    case 7:
                        System.out.println("Exportación a XML");
                        break;
                    case 8:
                        System.out.println("Exportación a JSON");
                        break;
                    case 0:
                        System.out.println("Saliendo de la aplicación...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }

            } while (opcion != 0);
            entrada.close();

        } catch (SQLException e) {

            System.out.println("error de conexion con la base de datos / data base error conection ");
        }
    }
}




