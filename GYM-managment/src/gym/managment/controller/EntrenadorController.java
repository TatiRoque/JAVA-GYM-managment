package gym.managment.controller;

import gym.managment.dao.EntrenadorDAO;
import gym.managment.model.Entrenador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class EntrenadorController {

    private EntrenadorDAO entrenadorDAO;
    private static final Logger LOGGER = Logger.getLogger(EntrenadorController.class.getName());

    public EntrenadorController() {
        this.entrenadorDAO = new EntrenadorDAO();
    }

    // Insertar
    public void agregarEntrenador(String nombre, String especialidad) {
        if (nombre.isEmpty() || especialidad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre y especialidad no pueden estar vacíos");
            return;
        }
        try {
            Entrenador e = new Entrenador(0, nombre, especialidad); // id auto-generado por BD
            entrenadorDAO.insertar(e);
            JOptionPane.showMessageDialog(null, "Entrenador agregado correctamente.");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al agregar entrenador", ex);
            JOptionPane.showMessageDialog(null, "Error al agregar entrenador:\n" + ex.getMessage());
        }
    }

    // Listar
    public List<Entrenador> obtenerEntrenadores() {
        try {
            return entrenadorDAO.listar();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al listar entrenadores", ex);
            JOptionPane.showMessageDialog(null, "Error al obtener entrenadores:\n" + ex.getMessage());
            return new ArrayList<>();
        }
    }

    // Actualizar
    public void actualizarEntrenador(int id, String nombre, String especialidad) {
        if (nombre.isEmpty() || especialidad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre y especialidad no pueden estar vacíos");
            return;
        }
        try {
            Entrenador e = new Entrenador(id, nombre, especialidad);
            entrenadorDAO.actualizar(e);
            JOptionPane.showMessageDialog(null, "Entrenador actualizado correctamente.");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al actualizar entrenador", ex);
            JOptionPane.showMessageDialog(null, "Error al actualizar entrenador:\n" + ex.getMessage());
        }
    }

    // Eliminar
    public void eliminarEntrenador(int id) {
        try {
            entrenadorDAO.eliminar(id);
            JOptionPane.showMessageDialog(null, "Entrenador eliminado correctamente.");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error al eliminar entrenador", ex);
            JOptionPane.showMessageDialog(null, "Error al eliminar entrenador:\n" + ex.getMessage());
        }
    }
}
