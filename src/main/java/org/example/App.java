package org.example;

import org.example.Dao.invitadoDao;
import org.example.IMPDao.*;
import org.example.Model.*;
import org.example.Utils.DatabaseConnection;
import org.example.Utils.ExportarJSON;
import org.example.Utils.ExportarXML;
import org.example.Utils.Metadatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
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
                        """
                                1. Gestionar juegos de mesa\s
                                2. Gestionar socias\s
                                3. Gestionar partidas\s
                                4. Gestionar invitadas\s
                                5. Gestionar datos de la asociación\s
                                6. Consultar metadatos de la base de datos\s
                                7. Exportar datos a XML\s
                                8. Exportar datos a JSON\s
                                0. Salir\s
                                """);
                System.out.print("Elige una opción: ");
                opcion = leerOpcion(entrada);

                switch (opcion) {
                    case 1:
                        gestionarJuegos(entrada);
                        break;
                    case 2:
                        gestionarSocios(entrada);
                        break;

                    case 3:
                        gestionarPartidad(entrada);
                        break;
                    case 4:
                        gestionarInvitados(entrada);
                        break;
                    case 5:
                        gestionarAsociacion(entrada);
                        break;

                    case 6:
                        Metadatos metadatos = new Metadatos();

                        metadatos.mostrarMetadatos();
                        metadatos.exportarMetadatos();

                        break;
                    case 7:
                        Exportar(entrada);
                        break;
                    case 8:
                        exportarJSON(entrada);
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


    /// //////////////////////////////////////////////////////////////////////////////777

    public static void Exportar(Scanner entrada) throws SQLException {
        impJuego juego = new impJuego();
        socioDaoImpl socio = new socioDaoImpl();
        impPartida partida = new impPartida();
        int seleccion;
        System.out.println("""
                Exportar XML   \s
                1-- Juegos
                2-- Socios
                3-- Partidas
                0-- Volver"""
        );

        seleccion = leerOpcion(entrada);

        switch (seleccion) {

            case 1:

                ExportarXML.exportarJuegos(
                        juego.getjuegos()
                );
                break;

            case 2:
                ExportarXML.exportarSocios(
                        socio.getSocias()
                );
                break;

            case 3:
                ExportarXML.exportarPartidas(
                        partida.getPartidas()
                );
                break;

            case 0:
                break;

            default:
                System.out.println("Opción no válida.");
        }


    }

    public static void gestionarSocios(Scanner entrada) throws SQLException {
        System.out.println("Gestión de socios");
        int opcionGestion;
        do {
            System.out.println("""
                    1 - Añadir socios \s
                    2 - Eliminar socios\s
                    3 - Editar Socios\s
                    4 - Listar Socios\s
                    5 - Buscar Socios\s
                    0. - Volver"""
            );
            System.out.print("Elige una opción: ");
            opcionGestion = leerOpcion(entrada);

            socioDaoImpl socioDao = new socioDaoImpl();


            switch (opcionGestion) {
                case 1:
                    System.out.println("----AÑADIR SOCIOS---\n ");
                    System.out.println("Ingrese el documento del socio: ");
                    String documento = entrada.nextLine();

                    System.out.println("Ingresa el nombre del socio: ");
                    String nombre = entrada.nextLine();

                    System.out.println("Ingresa los apellidos del socio: ");
                    String apellidos = entrada.nextLine();

                    System.out.println("Ingrese el telefono del socio: ");
                    String telefono = entrada.nextLine();


                    int edad = 0;
                    boolean edadValido = false;
                    while (!edadValido) {
                        try {
                            System.out.println("Ingresa el edad del socio: ");
                            edad = Integer.parseInt(entrada.nextLine());
                            edadValido = true;
                        } catch (Exception e) {
                            System.out.println("no es un dato valido indroduce un entero ");
                        }
                    }


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

                        edad = 0;
                        edadValido = false;
                        while (!edadValido) {
                            try {
                                System.out.println("Ingresa la nueva edad : ");
                                edad = Integer.parseInt(entrada.nextLine());
                                edadValido = true;
                            } catch (Exception e) {
                                System.out.println("no es un dato valido indroduce un entero ");
                            }
                        }
                        socioEditar.setEdad(edad);

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
                            System.out.println("Documento indentidad : " + s.getDocumento() + "\n" +
                                    "Nombre: " + s.getNombre() + "\n" +
                                    " Apellido: " + s.getApellidos() + "\n" +
                                    " Telefono: " + s.getTelefono() + "\n"
                            );
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
        } while (opcionGestion != 0);
    }


    public static void gestionarJuegos(Scanner entrada) throws SQLException {
        int opcionJuegos;
        impJuego impJuego = new impJuego();
        impAsociacion daoAsociacion = new impAsociacion();

        System.out.println("Gestión de juegos de mesa");

        do {
            System.out.println(
                    """
                            1. Añadir juego\s
                            2. Mostrar juegos\s
                            3. Modificar juego\s
                            4. Eliminar juego\s
                            0. Volver al menú principal\s
                            """);
            opcionJuegos = leerOpcion(entrada);

            switch (opcionJuegos) {
                case 1:

                    System.out.println("add Games / añadir juego");

                    System.out.println("nombre del juego /name game :");
                    String nombre = entrada.nextLine();

                    System.out.println("autor/s :");
                    String autores = entrada.nextLine();

                    System.out.println("descripcion del juego /game description :");
                    String descripcion = entrada.nextLine();

                    int minimoJugadores = 0;
                    boolean minimoJugadoresValido = false;
                    while (!minimoJugadoresValido) {
                        try {
                            System.out.println("numero minimo de jugadores: ");
                            minimoJugadores = Integer.parseInt(entrada.nextLine());
                            minimoJugadoresValido = true;
                        } catch (Exception e) {
                            System.out.println("no es un numero minimo de jugadores");
                        }
                    }


                    int maxJugadores = 0;
                    boolean maxJugadoresValido = false;
                    while (!maxJugadoresValido) {
                        try {
                            System.out.println("numero maximo de jugadores: ");
                            maxJugadores = Integer.parseInt(entrada.nextLine());
                            maxJugadoresValido = true;
                        } catch (Exception e) {
                            System.out.println("no es un numero maximo de jugadores");
                        }
                    }

                    int duracion = 0;
                    boolean duracionValido = false;
                    while (!duracionValido) {
                        try {
                            System.out.println("duracion en minutos : ");
                            duracion = Integer.parseInt(entrada.nextLine());
                            duracionValido = true;
                        } catch (Exception e) {
                            System.out.println("se requiere un entero ");
                        }
                    }

                    System.out.println("tematica: ");
                    String tematica = entrada.nextLine();

                    tipo tipojuego = null;
                    boolean tipovalido = false;
                    while (!tipovalido) {
                        try {
                            System.out.println("tipo cooperativo / competitivo :");
                            String texto = entrada.nextLine();
                            tipojuego = tipo.valueOf(texto.toLowerCase());
                            tipovalido = true;
                        } catch (Exception e) {
                            System.out.println("no es un tipo cooperativo / competitivo");
                        }
                    }


                    System.out.println("ubicacion: ");
                    String ubicacion = entrada.nextLine();

                    estado Estado = null;
                    boolean estadoValido = false;
                    while (!estadoValido) {
                        try {
                            System.out.println("Estado : Prestado / Disponible / En Reparacion:");
                            String texto = entrada.nextLine();
                            Estado = estado.valueOf(texto.toLowerCase());
                            estadoValido = true;

                        } catch (Exception e) {
                            System.out.println("no es un Estado valido");
                        }
                    }
                    //fecha
                    Date fecha_alta = null;
                    boolean fecha_altaValido = false;
                    while (!fecha_altaValido) {
                        try {
                            System.out.println("fecha de alta formato 0000-00-00 : ");
                            String texto = entrada.nextLine();
                            fecha_alta = Date.valueOf(texto);
                            fecha_altaValido = true;
                        } catch (Exception e) {
                            System.out.println("no es un fecha de alta formato 0000-00-00");
                        }
                    }


                    int id_asociacion = 0;
                    boolean id_asociacionValido = false;

                    while (!id_asociacionValido) {

                        try {
                            System.out.println("ID de la asociación (0 si pertenece a un socio):");

                            id_asociacion = Integer.parseInt(entrada.nextLine());

                            if (id_asociacion == 0) {
                                id_asociacionValido = true;

                            } else if (daoAsociacion.getAsociacion(id_asociacion) != null) {
                                id_asociacionValido = true;

                            } else {
                                System.out.println("No existe ninguna asociación con ese ID.");
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Debes introducir un número válido.");
                        }
                    }

                    System.out.println("documento Propietaria: ");
                    String documento_propietaria = entrada.nextLine();


                    juego juego = new juego(nombre, autores, descripcion, minimoJugadores, maxJugadores, duracion, tematica,
                            tipojuego, ubicacion, Estado, fecha_alta, id_asociacion, documento_propietaria);


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
                                    "Id: " + juego1.getIdJuego() + "\n"
                                            + "Nombre : " + juego1.getNombre() + "\n"
                                            + "Autores: " + juego1.getAutores() + "\n"
                                            + "Descripción: " + juego1.getDescripcion() + "\n"
                                            + "Tipo: " + juego1.getTipo() + "\n"
                                            + "Estado: " + juego1.getEstado() + "\n"
                            );

                        }
                    }
                    break;

                case 3:


                    int id = 0;
                    boolean idValido = false;
                    while (!idValido) {
                        try {
                            System.out.println("id que se quiere editar: ");
                            id = Integer.parseInt(entrada.nextLine());
                            idValido = true;

                        } catch (Exception e) {
                            System.out.println("no es un id valido ");
                        }
                    }
                    juego editarJuego = impJuego.getjuego(id);

                    if (editarJuego == null) {
                        System.out.println("no existe juego con ese ID");
                    } else {
                        System.out.println("Juego encontrado -Nombre: " + editarJuego.getNombre());

                        System.out.println("nuevo nombre: ");
                        editarJuego.setNombre(entrada.nextLine());

                        System.out.println("nuevo autores: ");
                        editarJuego.setAutores(entrada.nextLine());

                        System.out.println("nuevo descripcion: ");
                        editarJuego.setDescripcion(entrada.nextLine());

                        minimoJugadores = 0;
                        minimoJugadoresValido = false;

                        while (!minimoJugadoresValido) {
                            try {
                                System.out.println("nuevo minimo de jugadores: ");
                                minimoJugadores = Integer.parseInt(entrada.nextLine());
                                minimoJugadoresValido = true;
                            } catch (Exception e) {
                                System.out.println("numero no valido escriba un entero");
                            }
                        }

                        editarJuego.setMinimoJugadores(minimoJugadores);

                        maxJugadores = 0;
                        maxJugadoresValido = false;
                        while (!maxJugadoresValido) {
                            try {
                                System.out.println("nuevo maximo de jugadores: ");
                                maxJugadores = Integer.parseInt(entrada.nextLine());
                                maxJugadoresValido = true;

                            } catch (Exception e) {
                                System.out.println("numero no valido escriba un entero");
                            }
                        }

                        editarJuego.setMaximoJuegadores(maxJugadores);

                        //duracion
                        duracion = 0;
                        boolean duracionValida = false;
                        while (!duracionValida) {
                            try {
                                System.out.println("nuevo duracion: ");
                                duracion = Integer.parseInt(entrada.nextLine());
                                duracionValida = true;
                            } catch (Exception e) {
                                System.out.println("numero no valido escriba un entero");
                            }
                        }

                        editarJuego.setDuracion(duracion);

                        System.out.println("nuevo tematica: ");
                        editarJuego.setTematica(entrada.nextLine());
                        //Tipo
                        tipo tipo1 = null;
                        boolean tipoValido = false;
                        while (!tipoValido) {
                            try {
                                System.out.println("nuevo tipo: (cooperativo / competitivo) ");
                                String texto = entrada.nextLine();
                                tipo1 = tipo.valueOf(texto.toLowerCase());
                                tipoValido = true;
                            } catch (Exception e) {
                                System.out.println("no es un tipo valido");
                            }
                        }

                        editarJuego.setTipo(tipo1);
                        //estado
                        estado estado1 = null;
                        boolean valido = false;
                        while (!valido) {
                            try {
                                System.out.println("Nuevo estado (disponible/prestado/en_reparacion)");
                                String texto = entrada.nextLine();
                                estado1 = estado.valueOf(texto.toLowerCase());
                                valido = true;
                            } catch (Exception e) {
                                System.out.println("no es un estado valido");
                            }
                        }

                        editarJuego.setEstado(estado1);


                        int idAsociacion = 0;
                        boolean idAsociacionValido = false;


                        while (!idAsociacionValido) {
                            try {
                                System.out.println("Nueva asociación ID: ");
                                idAsociacion = Integer.parseInt(entrada.nextLine());

                                if (idAsociacion == 0) {
                                    idAsociacionValido = true;

                                } else if (daoAsociacion.getAsociacion(idAsociacion) != null) {
                                    idAsociacionValido = true;

                                } else {
                                    System.out.println("No existe ninguna asociación con ese ID.");
                                }

                            } catch (NumberFormatException e) {
                                System.out.println("Número no válido. Escriba un entero.");
                            }
                        }
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
                    System.out.println(" ----------ELIMINAR JUEGOS------------------");

                    System.out.println("id del juego que se quiere eliminar :");
                    int idEliminar = leerOpcion(entrada);
                    boolean eliminado = impJuego.delete(idEliminar);
                    if (eliminado) {
                        System.out.println("Juego eliminado correctamente");
                    } else
                        System.out.println("error al eliminar el juego");
                    break;

                case 0:
                    System.out.println("return home / regresar al menu ");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcionJuegos != 0);
    }


    public static void gestionarAsociacion(Scanner entrada) throws SQLException {
        int opcionAsociacion;

        System.out.println("\n--- GESTIÓN DE ASOCIACIONES ---");

        do {
            System.out.println("1. Añadir asociación \n");
            System.out.println("2. Listar Asociaciones \n");
            System.out.println("0. Volver al menú principal \n");
            System.out.println("Elige una opción: \n");

            opcionAsociacion = leerOpcion(entrada);

            switch (opcionAsociacion) {
                case 1:
                    System.out.println("Nombre de la asociación:");
                    String nombre = entrada.nextLine().toUpperCase();

                    System.out.println("CIF: ejemplo A012345678 ");
                    String cif = entrada.nextLine();

                    System.out.println("Dirección:");
                    String direccion = entrada.nextLine();

                    System.out.println("Teléfono:");
                    String telefono = entrada.nextLine();

                    System.out.println("Email:");
                    String email = entrada.nextLine();


                    Date fechaApertura = null;
                    boolean fechaAperturaValido = false;
                    while (!fechaAperturaValido) {
                        try {
                            System.out.println("Fecha de apertura (AAAA-MM-DD):");
                            String texto = entrada.nextLine();
                            fechaApertura = Date.valueOf(texto);
                            fechaAperturaValido = true;

                        } catch (Exception e) {
                            System.out.println("Fecha de apertura no valido");
                        }
                    }

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

                case 2:
                    System.out.println("----LISTADO ASOCIACIONES----");
                    impAsociacion daoAsociacion2 = new impAsociacion();
                    List<asociacion> asociaciones = daoAsociacion2.getAsociaciones();
                    if (asociaciones.isEmpty()) {
                        System.out.println("No hay asociaciones registradas.");
                    } else {
                        for (asociacion a : asociaciones) {
                            System.out.println("------------------------------");
                            System.out.println("ID: " + a.getId_asociacion());
                            System.out.println("Nombre: " + a.getNombre());
                            System.out.println("CIF: " + a.getCif());
                            System.out.println("Dirección: " + a.getDireccion());
                            System.out.println("Teléfono: " + a.getTelefono());
                            System.out.println("Email: " + a.getEmail());
                            System.out.println("Fecha de apertura: " + a.getFecha_apertura());
                        }
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcionAsociacion != 0);

    }


    public static void gestionarInvitados(Scanner opcion) throws SQLException {
        invitadoDaoImpl invitadoDao = new invitadoDaoImpl();
        int seleccion;

        System.out.println("--- Gestionar Partidad ---");
        do {
            System.out.println(
                    """
                            1-- Añadir Invitado\s
                            2-- Eliminar Invitado
                            3-- Editar Invitado
                            4-- Listar Invitados
                            5-- Buscar Invitado
                            0-- Salir"""
            );

            seleccion = leerOpcion(opcion);
            switch (seleccion) {
                case 1:
                    System.out.println("--- AÑADIR INVITADO ---");

                    System.out.println("Documento:");
                    String documento = opcion.nextLine();

                    System.out.println("Nombre:");
                    String nombre = opcion.nextLine();

                    System.out.println("Apellidos:");
                    String apellidos = opcion.nextLine();

                    System.out.println("Teléfono:");
                    String telefono = opcion.nextLine();

                    System.out.println("Email:");
                    String email = opcion.nextLine();

                    invitado nuevoInvitado = new invitado(
                            documento, nombre, apellidos, telefono, email
                    );

                    int resultado = invitadoDao.add(nuevoInvitado);

                    if (resultado > 0) {
                        System.out.println("Invitado añadido correctamente.");
                    } else {
                        System.out.println("Error al añadir el invitado.");
                    }
                    break;

                case 2:
                    System.out.println("--- ELIMINAR INVITADO ---");
                    System.out.println("Documento del invitado:");
                    documento = opcion.nextLine();

                    boolean eliminado = invitadoDao.delete(documento);

                    if (eliminado) {
                        System.out.println("Invitado eliminado correctamente.");
                    } else {
                        System.out.println("No se ha podido eliminar el invitado.");
                    }
                    break;

                case 3:
                    System.out.println("--- EDITAR INVITADO ---");
                    System.out.println("Documento del invitado:");
                    documento = opcion.nextLine();

                    invitado editarInvitado = invitadoDao.getInvitada(documento);

                    if (editarInvitado != null) {

                        System.out.println("Nuevo nombre:");
                        editarInvitado.setNombre(opcion.nextLine());

                        System.out.println("Nuevos apellidos:");
                        editarInvitado.setApellido(opcion.nextLine());

                        System.out.println("Nuevo teléfono:");
                        editarInvitado.setTelefono(opcion.nextLine());

                        System.out.println("Nuevo email:");
                        editarInvitado.setEmail(opcion.nextLine());

                        boolean actualizado = invitadoDao.update(editarInvitado);

                        if (actualizado) {
                            System.out.println("Invitado actualizado correctamente.");
                        } else {
                            System.out.println("Error al actualizar el invitado.");
                        }

                    } else {
                        System.out.println("No existe ningún invitado con ese documento.");
                    }

                    break;
                case 4:
                    System.out.println("--- LISTA DE INVITADOS ---");

                    List<invitado> invitados = invitadoDao.getInvitadas();

                    if (invitados.isEmpty()) {
                        System.out.println("No hay invitados registrados.");
                    } else {
                        for (invitado i : invitados) {
                            System.out.println(
                                    "Documento: " + i.getDocumento()
                                            + " | Nombre: " + i.getNombre()
                                            + " | Apellidos: " + i.getApellido()
                                            + " | Teléfono: " + i.getTelefono()
                                            + " | Email: " + i.getEmail()
                            );
                        }
                    }

                    break;
                case 5:
                    System.out.println("--- BUSCAR INVITADO ---");
                    System.out.println("Documento del invitado:");
                    documento = opcion.nextLine();
                    invitado encontrado = invitadoDao.getInvitada(documento);
                    if (encontrado != null) {
                        System.out.println("Documento: " + encontrado.getDocumento());
                        System.out.println("Nombre: " + encontrado.getNombre());
                        System.out.println("Apellidos: " + encontrado.getApellido());
                        System.out.println("Teléfono: " + encontrado.getTelefono());
                        System.out.println("Email: " + encontrado.getEmail());
                    } else {
                        System.out.println("No existe ningún invitado con ese documento.");
                    }

                    break;
            }

        } while (seleccion != 0);


    }

    public static void gestionarPartidad(Scanner opcion) throws SQLException {

        impPartida impPartida = new impPartida();
        impJuego daoJuegos = new impJuego();
        socioDaoImpl socioDao = new socioDaoImpl();
        invitadoDao invitadoDao = new invitadoDaoImpl();
        int seleccion;

        do {
            System.out.println("""
                     --- GESTIÓN DE PARTIDAS ---
                    1-- Añadir Partida
                    2-- Eliminar Partida
                    3-- Buscar Partida
                    4-- Listar Partidas
                    5-- Actualizar Partida
                    6-- Añadir participante
                    7-- Mostrar participantes
                    8-- Eliminar participante
                    9-- Añadir ganador
                    10--Mostrar ganadores
                    0-- Salir
                    """);
            seleccion = leerOpcion(opcion);

            switch (seleccion) {

                case 1:
                    System.out.println("--Añadir Partida--");

                    System.out.println("ID del juego:");

                    int id_juego = 0;
                    boolean juegoValido = false;

                    while (!juegoValido) {
                        try {
                            id_juego = Integer.parseInt(opcion.nextLine());

                            if (daoJuegos.getjuego(id_juego) != null) {
                                juegoValido = true;
                            } else {
                                System.out.println("No existe ningún juego con ese ID. Inténtalo de nuevo.");
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Debes introducir un número entero.");
                        }
                    }


                    Date fecha = null;
                    boolean fechaValido = false;
                    while (!fechaValido) {
                        try {
                            System.out.println("introduce una fecha formato 0000-00-00: ");
                            fecha = Date.valueOf(opcion.nextLine());
                            fechaValido = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Formato invalido.");
                        }
                    }
                    //hora
                    Time hora = null;
                    boolean horaValido = false;
                    while (!horaValido) {
                        try {
                            System.out.println("introduce una hora formato hh:mm:ss: ");
                            hora = Time.valueOf(opcion.nextLine());
                            horaValido = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Formato invalido.");
                        }
                    }
                    //plazas libres
                    int plazasLibres = 0;
                    boolean plazasLibresValido = false;
                    while (!plazasLibresValido) {
                        try {
                            System.out.println("palzasLibres: ");
                            plazasLibres = Integer.parseInt(opcion.nextLine());
                            plazasLibresValido = true;

                        } catch (NumberFormatException e) {
                            System.out.println("se requiere un numero entero ");
                        }
                    }

                    System.out.println("jugadores Deseados: ");
                    int jugadoresDeseados = leerOpcion(opcion);

                    partida partida = new partida(id_juego, fecha, hora, plazasLibres, jugadoresDeseados);
                    int resultado = impPartida.add(partida);

                    if (resultado > 0) {
                        System.out.println("Partida creada correctamente. ID: " + resultado);
                    } else {
                        System.out.println("No se ha podido crear la partida");
                    }
                    break;

                case 2:
                    System.out.println("------ ELIMINAR PARTIDA ------");
                    System.out.println("Id de partida ");
                    int id_partida = 0;
                    boolean idPatidaValido = false;
                    while (!idPatidaValido) {
                        try {
                            System.out.println("Id del partida");
                            id_partida = Integer.parseInt(opcion.nextLine());
                            idPatidaValido = true;
                        } catch (Exception e) {
                            System.out.println("No se ha podido crear la partida");
                        }
                    }

                    boolean elimnado = impPartida.delete(id_partida);
                    if (elimnado) {
                        System.out.println("Partida Eliminada correctamente.");
                    } else {
                        System.out.println("No se ha podido elimnar la partida ");
                    }
                    break;

                case 3:
                    System.out.println("Buscar Partida");


                    int _partida = 0;
                    boolean idpatidaValido = false;
                    while (!idpatidaValido) {
                        try {
                            System.out.println("Id de la partida a buscar : ");
                            _partida = Integer.parseInt(opcion.nextLine());
                            idpatidaValido = true;

                        } catch (Exception e) {
                            System.out.println("id de la partida no valido se necesita un entero");
                        }
                    }

                    partida partidaEncontrada = impPartida.getPartida(_partida);
                    if (partidaEncontrada != null) {

                        System.out.println("\n------ DATOS DE LA PARTIDA ------");
                        System.out.println("ID de la partida: " + partidaEncontrada.getId_partida());
                        System.out.println("ID del juego: " + partidaEncontrada.getIdJuego());
                        System.out.println("Fecha: " + partidaEncontrada.getFecha());
                        System.out.println("Hora: " + partidaEncontrada.getHora());
                        System.out.println("Plazas libres: " + partidaEncontrada.getPlazasLibres());
                        System.out.println("Jugadores deseados: " + partidaEncontrada.getJugadoresDeseados());
                        System.out.println("---------------------------------");

                    } else {
                        System.out.println("No se ha encontrado ninguna partida con ese id ");
                    }
                    break;

                case 4:

                    System.out.println("\n------ LISTA DE PARTIDAS ------");

                    List<partida> partidas = impPartida.getPartidas();

                    if (!partidas.isEmpty()) {

                        for (partida p : partidas) {

                            System.out.println("------------------------------");
                            System.out.println("ID de la partida: " + p.getId_partida());
                            System.out.println("ID del juego: " + p.getIdJuego());
                            System.out.println("Fecha: " + p.getFecha());
                            System.out.println("Hora: " + p.getHora());
                            System.out.println("Plazas libres: " + p.getPlazasLibres());
                            System.out.println("Jugadores deseados: " + p.getJugadoresDeseados());
                        }

                        System.out.println("------------------------------");
                        System.out.println("Total de partidas: " + partidas.size());

                    } else {

                        System.out.println("No se encontraron partidas. La lista está vacía.");
                    }

                    break;

                case 5:
                    System.out.println("------Actualizar Partida------");
                    System.out.println("Id de la partida : ");
                    int partida_id = leerOpcion(opcion);
                    partida actualizar = impPartida.getPartida(partida_id);
                    if (actualizar != null) {
                        fecha = null;
                        boolean fechaValida = false;
                        while (!fechaValida) {
                            try {
                                System.out.println("Actualizar fecha formato 0000-00-00 : ");
                                fecha = Date.valueOf(opcion.next());
                                fechaValida = true;
                            } catch (Exception e) {
                                System.out.println("no es un fromato de fecha valido");
                            }

                        }

                        actualizar.setFecha(fecha);

                        System.out.println("hora : ");
                        actualizar.setHora(Time.valueOf(opcion.next()));

                        System.out.println("plazasLibres: ");
                        actualizar.setPlazasLibres(Integer.parseInt(opcion.next()));

                        boolean actualizado = impPartida.update(actualizar);
                        if (actualizado) {
                            System.out.println("Partida actualizada correctamente");
                        } else {
                            System.out.println("No se ha podido actualizar la partida");
                        }
                    } else {
                        System.out.println("No se encontro partida con ese id  ");
                    }
                    break;

                case 6:

                    System.out.println("--- AÑADIR PARTICIPANTE A LA PARTIDA ---");

                    int idPartida = 0;
                    boolean partidaValida = false;

                    while (!partidaValida) {
                        try {
                            System.out.println("Introduce el ID de la partida:");
                            idPartida = Integer.parseInt(opcion.next());

                            if (idPartida <= 0) {
                                System.out.println("El ID debe ser mayor que cero.");

                            } else if (impPartida.getPartida(idPartida) == null) {
                                System.out.println("No existe ninguna partida con ese ID.");

                            } else {
                                partidaValida = true;
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Introduce un número entero válido.");
                        }
                    }

                    System.out.println("\nTipo de participante:");
                    System.out.println("1 - Socio");
                    System.out.println("2 - Invitada");

                    int tipo = leerOpcion(opcion);

                    String tipoParticipante;
                    boolean participanteExiste = false;
                    String documento;

                    if (tipo == 1) {

                        tipoParticipante = "socia";
                        List<socio> socios = socioDao.getSocias();
                        if (socios.isEmpty()) {
                            System.out.println("No hay socios registrados.");
                            break;
                        }

                        System.out.println("\n--- LISTA DE SOCIOS ---");

                        for (socio s : socios) {
                            System.out.println("--------------------------");
                            System.out.println("Documento: " + s.getDocumento());
                            System.out.println("Nombre: " + s.getNombre());
                            System.out.println("Apellidos: " + s.getApellidos());
                        }


                        System.out.println("\nIntroduce el documento del socio:");
                        documento = opcion.next();


                        socio socioEncontrado = socioDao.getSocia(documento);

                        if (socioEncontrado != null) {
                            participanteExiste = true;
                        } else {
                            System.out.println("No existe ningún socio con ese documento.");
                        }

                    } else if (tipo == 2) {

                        tipoParticipante = "invitada";

                        List<invitado> invitados = invitadoDao.getInvitadas();

                        if (invitados.isEmpty()) {
                            System.out.println("No hay invitadas registradas.");
                            break;
                        }

                        System.out.println("\n--- LISTA DE INVITADAS ---");

                        for (invitado i : invitados) {
                            System.out.println("--------------------------");
                            System.out.println("Documento: " + i.getDocumento());
                            System.out.println("Nombre: " + i.getNombre());
                            System.out.println("Apellidos: " + i.getApellido());
                        }


                        System.out.println("\nIntroduce el documento de la invitada:");
                        documento = opcion.next();


                        invitado invitadoEncontrado = invitadoDao.getInvitada(documento);

                        if (invitadoEncontrado != null) {
                            participanteExiste = true;
                        } else {
                            System.out.println("No existe ninguna invitada con ese documento.");
                        }

                    } else {
                        System.out.println("Tipo no válido.");
                        break;
                    }


                    if (participanteExiste) {

                        boolean addParticipante = impPartida.addParticipante(
                                idPartida,
                                documento,
                                tipoParticipante
                        );

                        if (addParticipante) {
                            System.out.println("Participante añadido correctamente.");
                        } else {
                            System.out.println("No se ha podido añadir el participante.");
                        }
                    }

                    break;

                case 7:
                    System.out.println("--- PARTICIPANTES DE UNA PARTIDA ---");

                    idPartida = 0;
                    partidaValida = false;

                    while (!partidaValida) {
                        try {
                            System.out.println("Introduce el ID de la partida: ");
                            idPartida = Integer.parseInt(opcion.next());

                            if (idPartida <= 0) {
                                System.out.println("El ID debe ser un número mayor que cero.");

                            } else if (impPartida.getPartida(idPartida) == null) {
                                System.out.println("No existe ninguna partida con ese ID.");

                            } else {
                                partidaValida = true;
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Introduce un número entero válido.");
                        }
                    }

                    List<String> participantes = impPartida.getParticipantes(idPartida);

                    if (participantes.isEmpty()) {
                        System.out.println("No hay participantes en esta partida.");
                    } else {
                        System.out.println("\nParticipantes de la partida " + idPartida + ":");

                        for (int i = 0; i < participantes.size(); i++) {
                            System.out.println((i + 1) + ". " + participantes.get(i));
                        }

                        System.out.println("Total de participantes: " + participantes.size());
                    }


                    break;
                case 8:
                    System.out.println("--- ELIMINAR PARTICIPANTE DE UNA PARTIDA ---");

                    int idPartidaEliminar = 0;
                    boolean idPartidaEliminarValido = false;
                    while (!idPartidaEliminarValido) {
                        try {
                            System.out.println("Introduce el ID de la partida:");
                            idPartidaEliminar = Integer.parseInt(opcion.next());
                            idPartidaEliminarValido = true;
                        } catch (Exception e) {
                            System.out.println("error se necesita un numero enteor para continuar ");
                        }
                    }


                    System.out.println("Introduce el documento del participante:");
                    String documentoEliminar = opcion.next();

                    boolean eliminado = impPartida.eliminarParticipante(
                            idPartidaEliminar, documentoEliminar);

                    if (eliminado) {
                        System.out.println("Participante eliminado correctamente.");
                    } else {
                        System.out.println("No se ha encontrado ese participante en la partida.");
                    }

                    break;
                case 9:

                    System.out.println("--- AÑADIR GANADOR DE UNA PARTIDA ---");

                    int idPartidaGanador = 0;
                    boolean partidaGanadorValida = false;

                    while (!partidaGanadorValida) {
                        try {
                            System.out.println("Introduce el ID de la partida:");
                            idPartidaGanador = Integer.parseInt(opcion.next());

                            if (idPartidaGanador <= 0) {
                                System.out.println("El ID debe ser mayor que cero.");

                            } else if (impPartida.getPartida(idPartidaGanador) == null) {
                                System.out.println("No existe ninguna partida con ese ID.");

                            } else {
                                partidaGanadorValida = true;
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Introduce un número entero válido.");
                        }
                    }


                    List<String> participantesGanador =
                            impPartida.getParticipantes(idPartidaGanador);

                    if (participantesGanador.isEmpty()) {
                        System.out.println("No hay participantes en esta partida.");
                        break;
                    }

                    System.out.println("\n--- PARTICIPANTES DE LA PARTIDA ---");

                    for (int i = 0; i < participantesGanador.size(); i++) {
                        System.out.println((i + 1) + ". " + participantesGanador.get(i));
                    }

                    System.out.println("\nIntroduce el documento del ganador:");
                    String documentoGanador = opcion.next();

                    System.out.println("Tipo del ganador:");
                    System.out.println("1 - Socio");
                    System.out.println("2 - Invitada");

                    int tipoGanadorOpcion = leerOpcion(opcion);

                    String tipoGanador;

                    if (tipoGanadorOpcion == 1) {
                        tipoGanador = "socia";

                    } else if (tipoGanadorOpcion == 2) {
                        tipoGanador = "invitada";

                    } else {
                        System.out.println("Tipo no válido.");
                        break;
                    }

                    boolean addGanador = impPartida.addGanador(
                            idPartidaGanador,
                            documentoGanador,
                            tipoGanador
                    );

                    if (addGanador) {
                        System.out.println("Ganador añadido correctamente.");
                    } else {
                        System.out.println("No se ha podido añadir el ganador.");
                    }

                    break;
                case 10:
                    System.out.println("--- GANADORES DE UNA PARTIDA ---");

                    System.out.println("Introduce el ID de la partida:");
                    int idPartidaConsulta = opcion.nextInt();

                    List<String> ganadores = impPartida.getGanadores(idPartidaConsulta);

                    if (ganadores.isEmpty()) {
                        System.out.println("No hay ganadores registrados para esta partida.");
                    } else {
                        for (String ganador : ganadores) {
                            System.out.println(ganador);
                        }
                    }

                    break;


            }

        } while (seleccion != 0);
    }

    /// ///////////////////////////////////////////77

    public static int leerOpcion(Scanner entrada) {
        while (!entrada.hasNextInt()) {
            System.out.println("Introduce un número válido.");
            entrada.next();
            System.out.print("Elige una opción: ");
        }
        int opcion = entrada.nextInt();
        entrada.nextLine();
        return opcion;
    }

    public static void exportarJSON(Scanner entrada) throws SQLException {

        impJuego juego = new impJuego();
        socioDaoImpl socio = new socioDaoImpl();
        impPartida partida = new impPartida();

        System.out.println("""
                ----- EXPORTAR JSON -----
                
                1 -- Juegos
                2 -- Socios
                3 -- Partidas
                0 -- Volver
                """);

        int seleccion = leerOpcion(entrada);

        switch (seleccion) {

            case 1:
                ExportarJSON.exportarJuegos(
                        juego.getjuegos()
                );
                break;

            case 2:
                ExportarJSON.exportarSocios(
                        socio.getSocias()
                );
                break;

            case 3:
                ExportarJSON.exportarPartidas(
                        partida.getPartidas()
                );
                break;

            case 0:
                System.out.println("Volviendo al menú principal...");
                break;

            default:
                System.out.println("Opción no válida.");
        }
    }
}




