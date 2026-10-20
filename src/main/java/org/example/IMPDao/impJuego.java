package org.example.IMPDao;

import org.example.Dao.juego.juegoDao;
import org.example.Model.estado;
import org.example.Model.juego.juego;
import org.example.Model.tipo;
import org.example.Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class impJuego implements juegoDao {

    private Connection conexion;

    public impJuego() throws SQLException {
        this.conexion = DatabaseConnection.getInstance().getConnection();

    }

    @Override
    public int add(juego juego) {
        String sql = "INSERT INTO juegos (nombre,autores,descripcion," +
                "min_jugadores,max_jugadores,duracion,tematica,tipo,ubicacion,estado" +
                ",fecha_alta,id_asociacion,documento_propietaria ) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            java.sql.PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            {
                ps.setString(1, juego.getNombre());
                ps.setString(2, juego.getAutores());
                ps.setString(3, juego.getDescripcion());
                ps.setInt(4, juego.getMinimoJugadores());
                ps.setInt(5, juego.getMaximoJuegadores());
                ps.setInt(6, juego.getDuracion());
                ps.setString(7, juego.getTematica());
                String tipo = juego.getTipo().name();
                ps.setString(8, tipo);
                ps.setString(9, juego.getUbicacion());
                String estado = juego.getEstado().name();

                if (estado.equals("en_reparacion")) {
                    estado = "En reparación";
                }

                ps.setString(10, estado);
                ps.setDate(11, new java.sql.Date(juego.getFechaAlta().getTime()));

                if (juego.getId_asociacion() > 0) {

                    ps.setInt(12, juego.getId_asociacion());
                    ps.setNull(13, Types.VARCHAR);

                } else {
                    ps.setNull(12, Types.INTEGER);
                    String documento = juego.getDocumentoPropietaria();
                    if (documento == null || documento.trim().isEmpty()) {
                        throw new IllegalArgumentException(
                                "Debes indicar el documento de una socia."
                        );
                    }

                    ps.setString(13, documento.trim());
                }


                int filas = ps.executeUpdate();
                if (filas > 0) {
                    try (java.sql.ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            return rs.getInt(1);
                        } else
                            return 0;
                    }
                }

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;

    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM juegos WHERE id_juego = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas == 0) {
                return false;
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public juego getjuego(int id) {
        String sql = "SELECT * FROM juegos WHERE id_juego = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    juego juego = new juego();

                    juego.setIdJuego(rs.getInt("id_juego"));
                    juego.setNombre(rs.getString("nombre"));
                    juego.setAutores(rs.getString("autores"));
                    juego.setDescripcion(rs.getString("descripcion"));
                    juego.setMinimoJugadores(rs.getInt("min_jugadores"));
                    juego.setMaximoJuegadores(rs.getInt("max_jugadores"));
                    juego.setDuracion(rs.getInt("duracion"));
                    juego.setTematica(rs.getString("tematica"));

                    juego.setTipo(tipo.valueOf(
                            rs.getString("tipo").toLowerCase()));

                    juego.setUbicacion(rs.getString("ubicacion"));

                    String estadoBD = rs.getString("estado");

                    if (estadoBD.equalsIgnoreCase("En reparación")
                            || estadoBD.equalsIgnoreCase("En reparacion")) {
                        estadoBD = "en_reparacion";
                    }

                    juego.setEstado(estado.valueOf(estadoBD.toLowerCase()));
                    juego.setFechaAlta(rs.getDate("fecha_alta"));

                    int idAsociacion = rs.getInt("id_asociacion");

                    if (rs.wasNull()) {
                        juego.setId_asociacion(0);
                    } else {
                        juego.setId_asociacion(idAsociacion);
                    }

                    juego.setDocumentoPropietaria(
                            rs.getString("documento_propietaria"));

                    return juego;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<juego> getjuegos() {
        List<juego> juegos = new ArrayList<>();
        String sql = "SELECT * FROM juegos";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                juego juego = new juego();
                juego.setIdJuego(rs.getInt("id_juego"));
                juego.setNombre(rs.getString("nombre"));
                juego.setAutores(rs.getString("autores"));
                juego.setDescripcion(rs.getString("descripcion"));
                juego.setMinimoJugadores(rs.getInt("min_jugadores"));
                juego.setMaximoJuegadores(rs.getInt("max_jugadores"));
                juego.setDuracion(rs.getInt("duracion"));
                juego.setTematica(rs.getString("tematica"));
                juego.setTipo(tipo.valueOf(rs.getString("tipo").toLowerCase()));
                juego.setUbicacion(rs.getString("ubicacion"));
                String estadoBD = rs.getString("estado");

                if (estadoBD.equalsIgnoreCase("En reparación")
                        || estadoBD.equalsIgnoreCase("En reparacion")) {
                    estadoBD = "en_reparacion";
                }
                juego.setEstado(estado.valueOf(estadoBD.toLowerCase()));
                juego.setFechaAlta(rs.getDate("fecha_alta"));
                int idAsociacion = rs.getInt("id_asociacion");

                if (rs.wasNull()) {
                    juego.setId_asociacion(0);
                } else {
                    juego.setId_asociacion(idAsociacion);
                }
                juego.setDocumentoPropietaria(rs.getString("documento_propietaria"));
                juegos.add(juego);
            }

            return juegos;
        } catch (SQLException e) {
            throw new RuntimeException("fallo en realizar la consulta del juego\n " + e.getMessage());

        }

    }


    @Override
    public boolean update(juego juego) {

        // Comprobar que el juego existe
        if (juego == null || juego.getIdJuego() <= 0) {
            throw new IllegalArgumentException(
                    "El juego debe tener un ID válido.");
        }

        // Comprobar la fecha
        if (juego.getFechaAlta() == null) {
            throw new IllegalArgumentException(
                    "Debes indicar la fecha de alta del juego.");
        }

        boolean tieneAsociacion = juego.getId_asociacion() > 0;
        String documento = juego.getDocumentoPropietaria();

        if (!tieneAsociacion &&
                (documento == null || documento.trim().isEmpty())) {
            throw new IllegalArgumentException(
                    "Debes indicar una asociación o el documento de una socia.");
        }

        String sql = "UPDATE juegos SET nombre = ?, autores = ?, descripcion = ?, " +
                "min_jugadores = ?, max_jugadores = ?, duracion = ?, tematica = ?, " +
                "tipo = ?, ubicacion = ?, estado = ?, fecha_alta = ?, " +
                "id_asociacion = ?, documento_propietaria = ? " +
                "WHERE id_juego = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, juego.getNombre());
            ps.setString(2, juego.getAutores());
            ps.setString(3, juego.getDescripcion());
            ps.setInt(4, juego.getMinimoJugadores());
            ps.setInt(5, juego.getMaximoJuegadores());
            ps.setInt(6, juego.getDuracion());
            ps.setString(7, juego.getTematica());
            ps.setString(8, juego.getTipo().name());
            ps.setString(9, juego.getUbicacion());

            String estadoBD = juego.getEstado().name();

            if (estadoBD.equals("en_reparacion")) {
                estadoBD = "En reparación";
            } else {
                estadoBD = estadoBD.substring(0, 1).toUpperCase()
                        + estadoBD.substring(1);
            }

            ps.setString(10, estadoBD);
            ps.setDate(11, new java.sql.Date(
                    juego.getFechaAlta().getTime()));

            if (tieneAsociacion) {
                ps.setInt(12, juego.getId_asociacion());
                ps.setNull(13, Types.VARCHAR);
            } else {
                ps.setNull(12, Types.INTEGER);
                ps.setString(13, documento.trim());
            }

            ps.setInt(14, juego.getIdJuego());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
