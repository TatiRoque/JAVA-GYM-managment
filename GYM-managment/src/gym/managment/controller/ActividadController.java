package gym.managment.controller;

import gym.managment.dao.ActividadDAO;
import gym.managment.dao.EntrenadorDAO;
import gym.managment.dao.ClienteDAO;
import gym.managment.model.Actividad;
import gym.managment.model.Entrenador;
import gym.managment.model.Cliente;
import java.sql.SQLException;
import java.util.List;

public class ActividadController {
    
    private final ActividadDAO actividadDAO = new ActividadDAO();
    private final EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    // Métodos CRUD básicos siguiendo el patrón de ClienteController
    public void crear(Actividad a) throws SQLException { 
        actividadDAO.insertar(a); 
    }
    
    public List<Actividad> listar() throws SQLException { 
        return actividadDAO.listar(); 
    }
    
    public void actualizar(Actividad a) throws SQLException { 
        actividadDAO.actualizar(a); 
    }
    
    public void eliminar(int id) throws SQLException { 
        actividadDAO.eliminar(id); 
    }
    
    // Métodos específicos para las relaciones y funcionalidades del ActividadFrame
    
    /**
     * Obtiene todos los entrenadores disponibles para el ComboBox
     */
    public List<Entrenador> obtenerEntrenadores() throws SQLException {
        return entrenadorDAO.listar();
    }
    
    /**
     * Obtiene todos los clientes disponibles para inscripciones
     */
    public List<Cliente> obtenerClientes() throws SQLException {
        return clienteDAO.listar();
    }
    
    /**
     * Crea una actividad basándose en los datos del formulario
     * Mapea los campos del ActividadFrame al modelo Actividad
     */
    public Actividad crearActividadDesdeFormulario(String nombreActividad, String horario, 
                                                  Entrenador entrenadorSeleccionado) {
        // Por ahora usamos valores por defecto para descripcion, duracion y cupo
        // Estos podrían venir de campos adicionales en el frame
        Actividad actividad = new Actividad();
        actividad.setNombreActividad(nombreActividad);
        actividad.setDescripcion("Actividad de " + nombreActividad); // Valor por defecto
        actividad.setDuracionMinutos(60); // Valor por defecto - 1 hora
        actividad.setCupoMaximo(20); // Valor por defecto
        actividad.setEntrenador(entrenadorSeleccionado);
        
        return actividad;
    }
    
    /**
     * Convierte una lista de actividades a matriz para JTable
     * Columnas: ID, Nombre, Horario, Entrenador/a
     */
    public Object[][] convertirActividadesParaTabla(List<Actividad> actividades) {
        Object[][] datos = new Object[actividades.size()][4];
        
        for (int i = 0; i < actividades.size(); i++) {
            Actividad a = actividades.get(i);
            datos[i][0] = a.getIdActividad();
            datos[i][1] = a.getNombreActividad();
            datos[i][2] = a.getDuracionMinutos() + " min"; // Formato para mostrar
            datos[i][3] = a.getEntrenador() != null ? 
                         a.getEntrenador().getNombreEntrenador() : "Sin asignar";
        }
        
        return datos;
    }
    
    /**
     * Convierte lista de entrenadores para usar en JComboBox
     */
    public String[] convertirEntrenadoresParaCombo(List<Entrenador> entrenadores) {
        String[] nombres = new String[entrenadores.size()];
        for (int i = 0; i < entrenadores.size(); i++) {
            nombres[i] = entrenadores.get(i).toString(); // Usa el toString() del modelo
        }
        return nombres;
    }
    
    /**
     * Busca un entrenador por nombre desde el ComboBox
     */
    public Entrenador buscarEntrenadorPorNombre(List<Entrenador> entrenadores, String nombreSeleccionado) {
        for (Entrenador e : entrenadores) {
            if (e.toString().equals(nombreSeleccionado)) {
                return e;
            }
        }
        return null;
    }
    
    // Métodos para gestión de inscripciones de clientes
    
    /**
     * Inscribe un cliente a una actividad
     */
    public void inscribirCliente(int idActividad, int idCliente) throws SQLException {
        actividadDAO.inscribirCliente(idActividad, idCliente);
    }
    
    /**
     * Desinscribe un cliente de una actividad
     */
    public void desinscribirCliente(int idActividad, int idCliente) throws SQLException {
        actividadDAO.desinscribirCliente(idActividad, idCliente);
    }
    
    /**
     * Obtiene actividades de un entrenador específico
     */
    public List<Actividad> listarPorEntrenador(int idEntrenador) throws SQLException {
        return actividadDAO.listarPorEntrenador(idEntrenador);
    }
    
    /**
     * Valida los datos del formulario antes de guardar
     */
    public boolean validarDatosActividad(String nombre, String horario, String entrenadorSeleccionado) {
        if (nombre == null || nombre.trim().isEmpty() || nombre.equals("Actividad")) {
            return false;
        }
        
        if (horario == null || horario.trim().isEmpty() || horario.equals("00:00")) {
            return false;
        }
        
        if (entrenadorSeleccionado == null || entrenadorSeleccionado.trim().isEmpty()) {
            return false;
        }
        
        return true;
    }
}