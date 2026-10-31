package org.example.IMPDao;

import org.example.Dao.invitadoDao;
import org.example.Model.invitado;
import org.example.Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class invitadoDaoImpl implements invitadoDao {

    private Connection conexion;

    public invitadoDaoImpl() throws SQLException {
        this.conexion = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public int add(invitado invitado) {

        String sql = "INSERT INTO invitadas " +
                "(documento, nombre, apellidos, telefono, email) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, invitado.getDocumento());
            ps.setString(2, invitado.getNombre());
            ps.setString(3, invitado.getApellido());
            ps.setString(4, invitado.getTelefono());
            ps.setString(5, invitado.getEmail());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean delete(String documento) {

        String sql = "DELETE FROM invitadas WHERE documento = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, documento);

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(invitado invitado) {

        String sql = "UPDATE invitadas SET nombre = ?, apellidos = ?, " +
                "telefono = ?, email = ? WHERE documento = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, invitado.getNombre());
            ps.setString(2, invitado.getApellido());
            ps.setString(3, invitado.getTelefono());
            ps.setString(4, invitado.getEmail());
            ps.setString(5, invitado.getDocumento());

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public invitado getInvitada(String documento) {

        String sql = "SELECT * FROM invitadas WHERE documento = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, documento);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new invitado(
                        rs.getString("documento"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<invitado> getInvitadas() {

        List<invitado> lista = new ArrayList<>();

        String sql = "SELECT * FROM invitadas";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                invitado invitado = new invitado(
                        rs.getString("documento"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );

                lista.add(invitado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
