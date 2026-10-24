package org.example;

import org.example.IMPDao.socioDaoImpl;
import org.example.IMPDao.impAsociacion;
import org.example.IMPDao.impJuego;
import org.example.Model.*;
import org.example.Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
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
                entrada.nextLine();


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
                        entrada.nextLine();

                        impJuego impJuego = new impJuego();
                        switch (opcionJuegos) {

                            case 1:

                                System.out.println("add Games / añadir juego");

                                System.out.println("nombre del juego /name game :");
                                String nombre = entrada.nextLine();

                                System.out.println("autor/s :");
                                String autores = entrada.nextLine();

                                System.out.println("descripcion del juego /game description :");
                                String descripcion = entrada.nextLine();

                                System.out.println("numero minimo de jugadores: ");
                                int min_players = Integer.parseInt(entrada.nextLine());

                                System.out.println("maximo de jugadores: :");
                                int max_players = Integer.parseInt(entrada.nextLine());

                                System.out.println("duracion en minutos: ");
                                int duracion = Integer.parseInt(entrada.nextLine());

                                System.out.println("tematica: ");
                                String tematica = entrada.nextLine();

                                System.out.println("tipo :");
                                String tipojuego = entrada.nextLine();
                                tipo tipoEnum = tipo.valueOf(tipojuego.toLowerCase());

                                System.out.println("ubicacion: ");
                                String ubicacion = entrada.nextLine();

                                System.out.println("estado : 'Prestado','Disponible','En_Reparacion' ");
                                String estadoP = entrada.nextLine();
                                estado Estado = estado.valueOf(estadoP.toLowerCase());

                                System.out.println("fecha de alta: ");
                                Date fecha_alta = Date.valueOf(entrada.nextLine());

                                System.out.println("id asociacion: ");
                                int id_asociacion = Integer.parseInt(entrada.nextLine());

                                System.out.println("documento Propietaria: ");
                                String documento_propietaria = entrada.nextLine();


                                juego juego = new juego(nombre, autores, descripcion, min_players, max_players, duracion, tematica,
                                        tipoEnum, ubicacion, Estado, fecha_alta, id_asociacion, documento_propietaria);


                                impJuego.add(juego);

                                break;

                            case 2:
                                System.out.println("view Games / mostrar juego");
                                impJuego mostrarJuegos = new impJuego();
                                List<juego> juegos = mostrarJuegos.getjuegos();
                                if (juegos.isEmpty()) {
                                    System.out.println("No hay juegos disponibles.");
                                } else {
                                    for (juego juego1 : juegos) {
                                        System.out.println(
                                                "id: " + juego1.getIdJuego()
                                                        + "nombre : " + juego1.getNombre() + "\n"
                                                        + "Autores: " + juego1.getAutores() + "\n"
                                                        + "Descripción: " + juego1.getDescripcion() + "\n"
                                                        + "Tipo: " + juego1.getTipo() + "\n"
                                                        + "Estado: " + juego1.getEstado()
                                        );

                                    }
                                }


                                break;
                            case 3:
                                System.out.println("edit Games / editar juego");

                                System.out.println("id del juego que se quiere editar :");
                                int id = Integer.parseInt(entrada.nextLine());
                                juego editarJuego = impJuego.getjuego(id);

                                if (editarJuego == null) {
                                    System.out.println("no existe juego con ese ID");
                                } else {
                                    System.out.println("Juego encontrado: /n" +
                                            "Nombre: " + editarJuego.getNombre());

                                    System.out.println("nuevo nombre: ");
                                    editarJuego.setNombre(entrada.nextLine());

                                    System.out.println("nuevo autores: ");
                                    editarJuego.setAutores(entrada.nextLine());

                                    System.out.println("nuevo descripcion: ");
                                    editarJuego.setDescripcion(entrada.nextLine());

                                    System.out.println("nuevo numero minimo: ");
                                    editarJuego.setMinimoJugadores(Integer.parseInt(entrada.nextLine()));

                                    System.out.println("nuevo maximo de jugadores: ");
                                    editarJuego.setMaximoJuegadores(Integer.parseInt(entrada.nextLine()));

                                    System.out.println("nuevo duracion: ");
                                    editarJuego.setDuracion(Integer.parseInt(entrada.nextLine()));

                                    System.out.println("nuevo tematica: ");
                                    editarJuego.setTematica(entrada.nextLine());

                                    System.out.println("nuevo tipo:(cooperativo/competitivo)");
                                    String tipoNuevo = entrada.nextLine().toLowerCase();
                                    editarJuego.setTipo(tipo.valueOf(tipoNuevo));


                                    System.out.println("Nuevo estado (disponible/prestado/en_reparacion):");
                                    String estadoNuevo = entrada.nextLine().toLowerCase();
                                    editarJuego.setEstado(estado.valueOf(estadoNuevo));

                                    System.out.println("ID de la asociación (0 si pertenece a una socia):");
                                    int idAsociacion = Integer.parseInt(entrada.nextLine());

                                    editarJuego.setId_asociacion(idAsociacion);
                                    if (idAsociacion == 0) {
                                        System.out.println("Documento de la socia propietaria:");
                                        editarJuego.setDocumentoPropietaria(entrada.nextLine());
                                    } else {
                                        editarJuego.setDocumentoPropietaria(null);
                                    }
                                    boolean actualizado = impJuego.update(editarJuego);
                                    if (actualizado) {
                                        System.out.println("Juego actualizado correctamente");
                                    } else
                                        System.out.println("error al modificar el juego");

                                }

                                break;

                            case 4:
                                System.out.println("delete Games / eliminar juego");
                                System.out.println("id del juego que se quiere eliminar :");

                                boolean eliminado = impJuego.delete(Integer.parseInt(entrada.nextLine()));
                                if (eliminado) {
                                    System.out.println("Juego eliminado correctamente");
                                } else
                                    System.out.println("error al eliminar el juego");
                                break;

                            case 0:
                                System.out.println("return home / regresar al menu ");
                                break;
                        }


                        break;
                    case 2:
                        System.out.println("Gestión de socios");

                        System.out.println("1 - Añadir socios   " +
                                "2 - Eliminar socios" +
                                "3- Editar Socios" +
                                "4 - Listar Socios" +
                                "5 - Buscar Socios" +
                                "0. - Volver"
                        );
                        System.out.print("Elige una opción: ");
                        int opcionGestion = entrada.nextInt();

                        socioDaoImpl socioDao = new socioDaoImpl();
                        entrada.nextLine();


                        switch (opcionGestion) {
                            case 1:
                                System.out.println("----AÑADIR SOCIOS---\n ");
                                System.out.println("Ingrese el documento del socio:");
                                String documento = entrada.nextLine();

                                System.out.println("Ingresa el nombre del socio: ");
                                String nombre = entrada.nextLine();

                                System.out.println("Ingresa los apellidos del socio: ");
                                String apellidos = entrada.nextLine();

                                System.out.println("Ingrese el telefono del socio: ");
                                String telefono = entrada.nextLine();


                                System.out.println("Ingrese la edad del socio: ");
                                int edad = Integer.parseInt(entrada.nextLine());

                                System.out.println("Ingresa el email del socio: ");
                                String email = entrada.nextLine();

                                socio socio = new socio(documento, nombre, apellidos, telefono, edad, email);

                                int resultado = socioDao.add(socio);

                                if (resultado == 0) {
                                    System.out.println("no se pudo agregar el socio");
                                } else {
                                    System.out.println("el socio agregado correctamente");
                                }
                                break;

                            case 2:
                                System.out.println("----ELIMINAR SOCIOS---\n ");

                                System.out.println("Ingrese el documento del socio: ");
                                documento = entrada.nextLine();


                                boolean eliminado = socioDao.delete(documento);

                                if (eliminado) {
                                    System.out.println("el socio eliminado correctamente");
                                } else
                                    System.out.println("error al eliminar el socio");
                                break;

                            case 3:
                                System.out.println("--- EDITAR SOCIO ---");

                                System.out.println("Ingrese el documento del socio:");
                                String documentoEditar = entrada.nextLine();

                                socio socioEditar = socioDao.getSocia(documentoEditar);

                                if (socioEditar != null) {
                                    System.out.println("Ingrese el nuevo nombre:");
                                    socioEditar.setNombre(entrada.nextLine());

                                    System.out.println("Ingrese los nuevos apellidos:");
                                    socioEditar.setApellidos(entrada.nextLine());

                                    System.out.println("Ingrese el nuevo teléfono:");
                                    socioEditar.setTelefono(entrada.nextLine());

                                    System.out.println("Ingrese la nueva edad:");
                                    socioEditar.setEdad(Integer.parseInt(entrada.nextLine()));

                                    System.out.println("Ingrese el nuevo email:");
                                    socioEditar.setEmail(entrada.nextLine());

                                    boolean actualizado = socioDao.update(socioEditar);

                                    if (actualizado) {
                                        System.out.println("Socio modificado correctamente");
                                    } else {
                                        System.out.println("Error al modificar el socio");
                                    }

                                } else {
                                    System.out.println("No se ha encontrado el socio");
                                }
                                break;

                            case 4:
                                System.out.println("--- LISTAR SOCIOS ---");

                                List<socio> listaSocios = socioDao.getSocias();

                                if (listaSocios.isEmpty()) {
                                    System.out.println("No hay socios registrados");
                                } else {
                                    for (socio s : listaSocios) {
                                        System.out.println(s);
                                    }
                                }
                                break;

                            case 5:
                                System.out.println("--- BUSCAR SOCIO ---");

                                System.out.println("Ingrese el documento del socio:");
                                String documentoBuscar = entrada.nextLine();

                                socio socioEncontrado = socioDao.getSocia(documentoBuscar);

                                if (socioEncontrado != null) {
                                    System.out.println(socioEncontrado);
                                } else {
                                    System.out.println("No se ha encontrado el socio");
                                }
                                break;
                            case 0:
                                System.out.println("return home / regresar al menu ");
                                break;

                        }


                    case 3:
                        System.out.println("Gestión de partidas");
                        break;
                    case 4:
                        System.out.println("Gestión de invitadas");
                        break;
                    case 5:
                        int opcionAsociacion;
                        do {
                            System.out.println("\n--- GESTIÓN DE ASOCIACIONES ---");
                            System.out.println("1. Añadir asociación");
                            System.out.println("0. Volver al menú principal");
                            System.out.println("Elige una opción:");

                            opcionAsociacion = entrada.nextInt();
                            entrada.nextLine();

                            switch (opcionAsociacion) {
                                case 1:
                                    System.out.println("Nombre de la asociación:");
                                    String nombre = entrada.nextLine().toUpperCase();

                                    System.out.println("CIF:");
                                    String cif = entrada.nextLine();

                                    System.out.println("Dirección:");
                                    String direccion = entrada.nextLine();

                                    System.out.println("Teléfono:");
                                    String telefono = entrada.nextLine();

                                    System.out.println("Email:");
                                    String email = entrada.nextLine();

                                    System.out.println("Fecha de apertura (AAAA-MM-DD):");
                                    java.sql.Date fechaApertura =
                                            java.sql.Date.valueOf(entrada.nextLine());

                                    asociacion asociacion = new asociacion(
                                            nombre, cif, direccion, telefono, email, fechaApertura
                                    );

                                    impAsociacion daoAsociacion = new impAsociacion();
                                    int idGenerado = daoAsociacion.add(asociacion);

                                    if (idGenerado > 0) {
                                        System.out.println(
                                                "Asociación creada correctamente. ID: " + idGenerado
                                        );
                                    } else {
                                        System.out.println("No se ha podido crear la asociación.");
                                    }
                                    break;

                                case 0:
                                    System.out.println("Volviendo al menú principal...");
                                    break;

                                default:
                                    System.out.println("Opción no válida.");
                            }

                        } while (opcionAsociacion != 0);

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




