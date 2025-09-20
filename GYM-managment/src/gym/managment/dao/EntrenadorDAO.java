/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gym.managment.dao;
import gym.managment.model.Entrenador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntrenadorDAO {

    public void insertar(Entrenador e) throws SQLException {
        String sql = "INSERT INTO entrenadores (nombreEntrenador, especialidad) VALUES (?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getNombreEntrenador());
            ps.setString(2, e.getEspecialidad());
            ps.executeUpdate();
        }
    }

    public List<Entrenador> listar() throws SQLException {
        List<Entrenador> lista = new ArrayList<>();
        String sql = "SELECT idEntrenador, nombreEntrenador, especialidad FROM entrenadores";
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Entrenador e = new Entrenador(
                    rs.getInt("idEntrenador"),
                    rs.getString("nombreEntrenador"),
                    rs.getString("especialidad")
                );
                lista.add(e);
            }
        }
        return lista;
    }

    public void actualizar(Entrenador e) throws SQLException {
        String sql = "UPDATE entrenadores SET nombreEntrenador=?, especialidad=? WHERE idEntrenador=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getNombreEntrenador());
            ps.setString(2, e.getEspecialidad());
            ps.setInt(3, e.getIdEntrenador());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idEntrenador) throws SQLException {
        String sql = "DELETE FROM entrenadores WHERE idEntrenador=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEntrenador);
            ps.executeUpdate();
        }
    }
}

