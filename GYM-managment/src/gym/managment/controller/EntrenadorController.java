
package gym.managment.controller;
import gym.managment.dao.EntrenadorDAO;
import gym.managment.model.Entrenador;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class EntrenadorController {

    private EntrenadorDAO entrenadorDAO;

    public EntrenadorController() {
        this.entrenadorDAO = new EntrenadorDAO();
    }

    // Insertar
    public void agregarEntrenador(String nombre, String especialidad) {
        try {
            Entrenador e = new Entrenador(0, nombre, especialidad); // id=0, lo genera la BD
            entrenadorDAO.insertar(e);
            System.out.println("Entrenador agregado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al acceder a la base de datos: " + ex.getMessage());
        }
    }

    // Listar
    public List<Entrenador> obtenerEntrenadores() {
        try {
            return entrenadorDAO.listar();
        } catch (SQLException ex) {
            System.out.println("Error al acceder a la base de datos: " + ex.getMessage());            
            return new ArrayList<>();
        }
    }

    // Actualizar
    public void actualizarEntrenador(int id, String nombre, String especialidad) {
        try {
            Entrenador e = new Entrenador(id, nombre, especialidad);
            entrenadorDAO.actualizar(e);
            System.out.println("Entrenador actualizado correctamente.");
        } catch (SQLException ex) {
           System.out.println("Error al acceder a la base de datos: " + ex.getMessage());
        }
    }

    // Eliminar
    public void eliminarEntrenador(int id) {
        try {
            entrenadorDAO.eliminar(id);
            System.out.println("Entrenador eliminado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al acceder a la base de datos: " + ex.getMessage());        }
    }
}

