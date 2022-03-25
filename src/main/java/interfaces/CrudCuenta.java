
package interfaces;

import java.util.List;
import models.Cuenta;

public interface CrudCuenta {
    public List<Cuenta> getAll();
    public Cuenta getCuenta(String numeroCuenta);
    public boolean modificar(Cuenta cuenta);
    public boolean save(Cuenta cuenta);
    public boolean delete(String numeroCuenta);
}
