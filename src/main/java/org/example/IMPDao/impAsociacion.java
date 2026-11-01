package org.example.IMPDao;

import org.example.Dao.asociacionDao;
import org.example.Model.asociacion;
import org.example.Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<asociacion> getAsociaciones() {

        List<asociacion> lista = new ArrayList<>();

        String sql = "SELECT * FROM asociacion";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                asociacion a = new asociacion(
                        rs.getString("nombre"),
                        rs.getString("cif"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getDate("fecha_apertura")
                );

                a.setId_asociacion(rs.getInt("id_asociacion"));

                lista.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Error al recuperar las asociaciones: " + e.getMessage());
        }

        return lista;
    }
    @Override
    public asociacion getAsociacion(int id) {

        String sql = "SELECT * FROM asociacion WHERE id_asociacion = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    asociacion a = new asociacion(
                            rs.getString("nombre"),
                            rs.getString("cif"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("email"),
                            rs.getDate("fecha_apertura")
                    );

                    a.setId_asociacion(rs.getInt("id_asociacion"));

                    return a;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al recuperar la asociación: " + e.getMessage());
        }

        return null;
    }



}
