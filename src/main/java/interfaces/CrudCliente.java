
package interfaces;

import java.util.List;
import models.Cliente;

public interface CrudCliente {
    public List<Cliente> getAll();
    public Cliente getCliente(int id);
    public Cliente getClienteByDNI(String dni);
    public boolean modificar(Cliente cliente);
    public boolean save(Cliente cliente);
    public boolean eliminar(int id);
}
