package org.example.IMPDao;
import org.example.Dao.sociosDao;
import org.example.Model.socio;
import org.example.Utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class socioDaoImpl implements sociosDao {

    private Connection conexion;

    public socioDaoImpl() throws SQLException {
        this.conexion = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public int add(socio socia) {
        String sql = "INSERT INTO socios (documento,nombre,apellidos,telefono,edad,email) VALUES(?,?,?,?,?,?)";

        int edad = socia.getEdad();

        if (edad < 18) {
            System.out.println("Se requiere ser mayor de edad");
            return 0;
        }

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, socia.getDocumento());
            ps.setString(2, socia.getNombre());
            ps.setString(3, socia.getApellidos());
            ps.setString(4, socia.getTelefono());
            ps.setInt(5, edad);
            ps.setString(6, socia.getEmail());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean delete(String documento) {
        String sql = "DELETE FROM socios WHERE documento = ?";

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
    public boolean update(socio socia) {
        String sql = "UPDATE socios SET nombre = ?, apellidos = ?, telefono = ?, edad = ?, email = ? WHERE documento = ?";

        if (socia.getEdad() < 18) {
            System.out.println("Se requiere ser mayor de edad");
            return false;
        }

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, socia.getNombre());
            ps.setString(2, socia.getApellidos());
            ps.setString(3, socia.getTelefono());
            ps.setInt(4, socia.getEdad());
            ps.setString(5, socia.getEmail());
            ps.setString(6, socia.getDocumento());

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public socio getSocia(String documento) {
        String sql = "SELECT * FROM socios WHERE documento = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, documento);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                socio socia = new socio();

                socia.setDocumento(rs.getString("documento"));
                socia.setNombre(rs.getString("nombre"));
                socia.setApellidos(rs.getString("apellidos"));
                socia.setTelefono(rs.getString("telefono"));
                socia.setEdad(rs.getInt("edad"));
                socia.setEmail(rs.getString("email"));

                return socia;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<socio> getSocias() {
        String sql = "SELECT * FROM socios";
        List<socio> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                socio socia = new socio();

                socia.setDocumento(rs.getString("documento"));
                socia.setNombre(rs.getString("nombre"));
                socia.setApellidos(rs.getString("apellidos"));
                socia.setTelefono(rs.getString("telefono"));
                socia.setEdad(rs.getInt("edad"));
                socia.setEmail(rs.getString("email"));

                lista.add(socia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}