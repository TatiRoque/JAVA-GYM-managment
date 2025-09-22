package gym.managment.model;

import java.util.ArrayList;
import java.util.List;

public class Actividad {
    private int idActividad;
    private String nombreActividad;
    private String descripcion;
    private int duracionMinutos;
    private int cupoMaximo;
    private Entrenador entrenador; // Relación 1:1
    private List<Cliente> clientes; // Relación 1:N
    
    // Constructor vacío
    public Actividad() {
        this.clientes = new ArrayList<>();
    }
    
    // Constructor con parámetros básicos (sin entrenador ni clientes)
    public Actividad(int idActividad, String nombreActividad, String descripcion, 
                    int duracionMinutos, int cupoMaximo) {
        this.idActividad = idActividad;
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
        this.duracionMinutos = duracionMinutos;
        this.cupoMaximo = cupoMaximo;
        this.clientes = new ArrayList<>();
    }
    
    // Constructor completo
    public Actividad(int idActividad, String nombreActividad, String descripcion, 
                    int duracionMinutos, int cupoMaximo, Entrenador entrenador) {
        this(idActividad, nombreActividad, descripcion, duracionMinutos, cupoMaximo);
        this.entrenador = entrenador;
    }
    
    // Getters y Setters
    public int getIdActividad() { return idActividad; }
    public void setIdActividad(int idActividad) { this.idActividad = idActividad; }
    
    public String getNombreActividad() { return nombreActividad; }
    public void setNombreActividad(String nombreActividad) { this.nombreActividad = nombreActividad; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public int getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }
    
    public int getCupoMaximo() { return cupoMaximo; }
    public void setCupoMaximo(int cupoMaximo) { this.cupoMaximo = cupoMaximo; }
    
    public Entrenador getEntrenador() { return entrenador; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }
    
    public List<Cliente> getClientes() { return clientes; }
    public void setClientes(List<Cliente> clientes) { this.clientes = clientes; }
    
    // Métodos de utilidad para manejar clientes
    public boolean agregarCliente(Cliente cliente) {
        if (clientes.size() < cupoMaximo && !clientes.contains(cliente)) {
            return clientes.add(cliente);
        }
        return false; // Cupo lleno o cliente ya inscrito
    }
    
    public boolean eliminarCliente(Cliente cliente) {
        return clientes.remove(cliente);
    }
    
    public int getCantidadClientesInscritos() {
        return clientes.size();
    }
    
    public boolean tieneCupoDisponible() {
        return clientes.size() < cupoMaximo;
    }
    
    public boolean estaClienteInscrito(Cliente cliente) {
        return clientes.contains(cliente);
    }
    
    @Override
    public String toString() {
        String entrenadorNombre = (entrenador != null) ? entrenador.getNombreEntrenador() : "Sin asignar";
        return nombreActividad + " - " + entrenadorNombre + " (" + clientes.size() + "/" + cupoMaximo + " inscriptos)";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Actividad actividad = (Actividad) obj;
        return idActividad == actividad.idActividad;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(idActividad);
    }
}