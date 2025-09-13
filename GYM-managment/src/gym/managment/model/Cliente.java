package gym.managment.model;

public class Cliente {
    private int idCliente;
    private String nombreCliente;
    private String telefono;
    private int dni;

    public Cliente() { }

    public Cliente(int idCliente, String nombreCliente, String telefono, int dni) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        this.dni = dni;
    }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public int getDni() { return dni; }
    public void setDni(int dni) { this.dni = dni; }

    @Override
    public String toString() {
        return nombreCliente + " (DNI " + dni + ")";
    }
}
