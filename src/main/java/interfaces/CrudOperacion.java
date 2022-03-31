
package interfaces;

import java.util.List;
import models.Operacion;

public interface CrudOperacion {
    public List<Operacion> getByNumeroCuenta(String numeroCuenta);
    public Operacion getOperacion(int id);
    public boolean modificar(Operacion operacion);
    public boolean agregar(Operacion operacion);
    public boolean eliminar(int id);
}
