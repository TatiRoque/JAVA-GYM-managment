package gym.managment.dao;

import gym.managment.model.Cliente;
import gym.managment.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void insertar(Cliente c) throws SQLException {
        String sql = "INSERT INTO clientes (nombreCliente, telefono, dni) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombreCliente());
            ps.setString(2, c.getTelefono());
            ps.setInt(3, c.getDni());
            ps.executeUpdate();
        }
    }

    public List<Cliente> listar() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT idCliente, nombreCliente, telefono, dni FROM clientes";
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getInt("idCliente"),
                    rs.getString("nombreCliente"),
                    rs.getString("telefono"),
                    rs.getInt("dni")
                );
                lista.add(c);
            }
        }
        return lista;
    }

    public void actualizar(Cliente c) throws SQLException {
        String sql = "UPDATE clientes SET nombreCliente=?, telefono=?, dni=? WHERE idCliente=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombreCliente());
            ps.setString(2, c.getTelefono());
            ps.setInt(3, c.getDni());
            ps.setInt(4, c.getIdCliente());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idCliente) throws SQLException {
        String sql = "DELETE FROM clientes WHERE idCliente=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.executeUpdate();
        }
    }
}
