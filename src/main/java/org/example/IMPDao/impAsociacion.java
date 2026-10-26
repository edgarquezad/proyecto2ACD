package org.example.IMPDao;

import org.example.Dao.asociacionDao;
import org.example.Model.asociacion;
import org.example.Utils.DatabaseConnection;

import java.sql.*;

public class impAsociacion implements asociacionDao {
    private Connection conexion;

    public impAsociacion() throws SQLException {
        this.conexion = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public int add(asociacion asociacion) {
        String sql = "INSERT INTO asociacion " +
                "(nombre, cif, direccion, telefono, email, fecha_apertura) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, asociacion.getNombre());
            ps.setString(2, asociacion.getCif());
            ps.setString(3, asociacion.getDireccion());
            ps.setString(4, asociacion.getTelefono());
            ps.setString(5, asociacion.getEmail());
            ps.setDate(6, asociacion.getFecha_apertura());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al crear la asociación: "
                    + e.getMessage()

            );
            return -1;
        }

        return -1;
    }

}
