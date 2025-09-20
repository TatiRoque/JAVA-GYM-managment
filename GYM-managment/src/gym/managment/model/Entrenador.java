
package gym.managment.model;
public class Entrenador {
    private int idEntrenador;
    private String nombreEntrenador;
    private String especialidad;

    public Entrenador() { }

    public Entrenador(int idEntrenador, String nombreEntrenador, String especialidad) {
        this.idEntrenador = idEntrenador;
        this.nombreEntrenador = nombreEntrenador;
        this.especialidad = especialidad;

    }

    public int getIdEntrenador() { return idEntrenador; }
    public void setIdEntrenador(int idEntrenador) { this.idEntrenador = idEntrenador; }

    public String getNombreEntrenador() { return nombreEntrenador; }
    public void setNombreEntrenador(String nombreEntrenador) { this.nombreEntrenador = nombreEntrenador; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }



    @Override
    public String toString() {
        return nombreEntrenador + " (Especialidad: " + especialidad + ")";
    }
}
