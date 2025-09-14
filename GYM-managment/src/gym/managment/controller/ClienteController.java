package gym.managment.controller;

import gym.managment.dao.ClienteDAO;
import gym.managment.model.Cliente;
import java.sql.SQLException;
import java.util.List;

public class ClienteController {
    private final ClienteDAO dao = new ClienteDAO();

    public void crear(Cliente c) throws SQLException { dao.insertar(c); }
    public List<Cliente> listar() throws SQLException { return dao.listar(); }
    public void actualizar(Cliente c) throws SQLException { dao.actualizar(c); }
    public void eliminar(int id) throws SQLException { dao.eliminar(id); }
}
