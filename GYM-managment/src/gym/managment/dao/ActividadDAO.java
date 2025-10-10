package gym.managment.dao;

import gym.managment.model.Actividad;
import gym.managment.model.Cliente;
import gym.managment.model.Entrenador;
import gym.managment.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActividadDAO {

    public void insertar(Actividad a) throws SQLException {
        String sql = "INSERT INTO actividades (nombreActividad, descripcion, duracionMinutos, cupoMaximo, idEntrenador) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getNombreActividad());
            ps.setString(2, a.getDescripcion());
            ps.setInt(3, a.getDuracionMinutos());
            ps.setInt(4, a.getCupoMaximo());
            ps.setInt(5, a.getEntrenador() != null ? a.getEntrenador().getIdEntrenador() : 0);
            ps.executeUpdate();
        }
    }

    public List<Actividad> listar() throws SQLException {
        List<Actividad> lista = new ArrayList<>();
        String sql = """
            SELECT a.idActividad, a.nombreActividad, a.descripcion, a.duracionMinutos, a.cupoMaximo,
                   e.idEntrenador, e.nombreEntrenador, e.especialidad
            FROM actividades a
            LEFT JOIN entrenadores e ON a.idEntrenador = e.idEntrenador
        """;
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                // Crear entrenador si existe
                Entrenador entrenador = null;
                if (rs.getInt("idEntrenador") > 0) {
                    entrenador = new Entrenador(
                        rs.getInt("idEntrenador"),
                        rs.getString("nombreEntrenador"),
                        rs.getString("especialidad")
                    );
                }
                
                // Crear actividad
                Actividad a = new Actividad(
                    rs.getInt("idActividad"),
                    rs.getString("nombreActividad"),
                    rs.getString("descripcion"),
                    rs.getInt("duracionMinutos"),
                    rs.getInt("cupoMaximo"),
                    entrenador
                );
                
                // Cargar clientes de la actividad
                a.setClientes(obtenerClientesPorActividad(conn, a.getIdActividad()));
                
                lista.add(a);
            }
        }
        return lista;
    }

    public void actualizar(Actividad a) throws SQLException {
        String sql = "UPDATE actividades SET nombreActividad=?, descripcion=?, duracionMinutos=?, cupoMaximo=?, idEntrenador=? WHERE idActividad=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getNombreActividad());
            ps.setString(2, a.getDescripcion());
            ps.setInt(3, a.getDuracionMinutos());
            ps.setInt(4, a.getCupoMaximo());
            ps.setInt(5, a.getEntrenador() != null ? a.getEntrenador().getIdEntrenador() : 0);
            ps.setInt(6, a.getIdActividad());
            ps.executeUpdate();
        }
    }

    public void eliminar(int idActividad) throws SQLException {
        try (Connection conn = ConexionBD.getConnection()) {
            // Primero eliminar las inscripciones
            String sqlInscripciones = "DELETE FROM inscripciones WHERE idActividad=?";
            try (PreparedStatement psInscripciones = conn.prepareStatement(sqlInscripciones)) {
                psInscripciones.setInt(1, idActividad);
                psInscripciones.executeUpdate();
            }
            
            // Luego eliminar la actividad
            String sqlActividad = "DELETE FROM actividades WHERE idActividad=?";
            try (PreparedStatement psActividad = conn.prepareStatement(sqlActividad)) {
                psActividad.setInt(1, idActividad);
                psActividad.executeUpdate();
            }
        }
    }

    // Método auxiliar para cargar clientes de una actividad
    private List<Cliente> obtenerClientesPorActividad(Connection conn, int idActividad) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = """
            SELECT c.idCliente, c.nombreCliente, c.telefono, c.dni
            FROM clientes c
            INNER JOIN inscripciones i ON c.idCliente = i.idCliente
            WHERE i.idActividad = ?
        """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idActividad);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nombreCliente"),
                        rs.getString("telefono"),
                        rs.getInt("dni")
                    );
                    clientes.add(cliente);
                }
            }
        }
        return clientes;
    }

    // Métodos para manejar inscripciones de clientes
    public void inscribirCliente(int idActividad, int idCliente) throws SQLException {
        String sql = "INSERT INTO inscripciones (idActividad, idCliente) VALUES (?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idActividad);
            ps.setInt(2, idCliente);
            ps.executeUpdate();
        }
    }

    public void desinscribirCliente(int idActividad, int idCliente) throws SQLException {
        String sql = "DELETE FROM inscripciones WHERE idActividad=? AND idCliente=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idActividad);
            ps.setInt(2, idCliente);
            ps.executeUpdate();
        }
    }

    // Método para obtener actividades por entrenador
    public List<Actividad> listarPorEntrenador(int idEntrenador) throws SQLException {
        List<Actividad> lista = new ArrayList<>();
        String sql = """
            SELECT a.idActividad, a.nombreActividad, a.descripcion, a.duracionMinutos, a.cupoMaximo,
                   e.idEntrenador, e.nombreEntrenador, e.especialidad
            FROM actividades a
            INNER JOIN entrenadores e ON a.idEntrenador = e.idEntrenador
            WHERE e.idEntrenador = ?
        """;
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEntrenador);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Entrenador entrenador = new Entrenador(
                        rs.getInt("idEntrenador"),
                        rs.getString("nombreEntrenador"),
                        rs.getString("especialidad")
                    );
                    
                    Actividad a = new Actividad(
                        rs.getInt("idActividad"),
                        rs.getString("nombreActividad"),
                        rs.getString("descripcion"),
                        rs.getInt("duracionMinutos"),
                        rs.getInt("cupoMaximo"),
                        entrenador
                    );
                    
                    a.setClientes(obtenerClientesPorActividad(conn, a.getIdActividad()));
                    lista.add(a);
                }
            }
        }
        return lista;
    }
}