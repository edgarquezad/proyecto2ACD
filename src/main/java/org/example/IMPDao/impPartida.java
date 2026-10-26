package org.example.IMPDao;
import org.example.Dao.partidaDao;
import org.example.Model.partida;
import org.example.Utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class impPartida implements partidaDao {

    private Connection conexion;
    public impPartida() throws SQLException {
        conexion = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public int add(partida partida) {
        if (!datosValidos(partida)) {
            return 0;
        }

        String sql = "INSERT INTO partidas " +
                "(id_juego, fecha, hora, plazas_libres, jugadores_deseados) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, partida.getIdJuego());
            ps.setDate(2, partida.getFecha());
            ps.setTime(3, partida.getHora());
            ps.setInt(4, partida.getPlazasLibres());
            ps.setInt(5, partida.getJugadoresDeseados());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al añadir la partida: " + e.getMessage());
        }

        return 0;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM partidas WHERE id_partida = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar la partida: " + e.getMessage());
            return false;
        }
    }

    @Override
    public partida getPartida(int id) {
        String sql = "SELECT * FROM partidas WHERE id_partida = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    partida partidaEncontrada = new partida(
                            rs.getInt("id_juego"),
                            rs.getDate("fecha"),
                            rs.getTime("hora"),
                            rs.getInt("plazas_libres"),
                            rs.getInt("jugadores_deseados")
                    );
                    partidaEncontrada.setId_partida(rs.getInt("id_partida"));
                    return partidaEncontrada;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener la partida: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<partida> getPartidas() {
        List<partida> partidas = new ArrayList<>();

        String sql = "SELECT * FROM partidas ORDER BY fecha, hora";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                partidas.add(new partida(

                        rs.getInt("id_juego"),
                        rs.getDate("fecha"),
                        rs.getTime("hora"),
                        rs.getInt("plazas_libres"),
                        rs.getInt("jugadores_deseados")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener las partidas: " + e.getMessage());
        }

        return partidas;
    }

    @Override
    public boolean update(partida partida) {
        if (!datosValidos(partida)) {
            return false;
        }

        String sql = "UPDATE partidas SET " +
                "id_juego = ?, fecha = ?, hora = ?, " +
                "plazas_libres = ?, jugadores_deseados = ? " +
                "WHERE id_partida = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, partida.getIdJuego());
            ps.setDate(2, partida.getFecha());
            ps.setTime(3, partida.getHora());
            ps.setInt(4, partida.getPlazasLibres());
            ps.setInt(5, partida.getJugadoresDeseados());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar la partida: " + e.getMessage());
            return false;
        }
    }

    private boolean datosValidos(partida partida) {
        if (partida == null
                || partida.getFecha() == null
                || partida.getHora() == null
                || partida.getIdJuego() <= 0
                || partida.getPlazasLibres() < 0
                || partida.getJugadoresDeseados() <= 0) {

            System.out.println("Los datos de la partida no son válidos.");
            return false;
        }

        String sql = "SELECT min_jugadores, max_jugadores " +
                "FROM juegos WHERE id_juego = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, partida.getIdJuego());

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("El juego indicado no existe.");
                    return false;
                }

                int minimo = rs.getInt("min_jugadores");
                int maximo = rs.getInt("max_jugadores");

                if (partida.getJugadoresDeseados() < minimo
                        || partida.getJugadoresDeseados() > maximo) {
                    System.out.println(
                            "El número de jugadores debe estar entre "
                                    + minimo + " y " + maximo + "."
                    );

                    return false;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al validar la partida: " + e.getMessage());
            return false;
        }

        return true;
    }
}
