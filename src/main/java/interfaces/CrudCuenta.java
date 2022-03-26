
package interfaces;

import java.math.BigDecimal;
import java.util.List;
import models.Cuenta;

public interface CrudCuenta {
    public List<Cuenta> getAll();
    public Cuenta getCuenta(String numeroCuenta);
    public boolean modificarSaldo(String numero_cuenta, BigDecimal saldo);
    public boolean save(Cuenta cuenta);
    public boolean eliminar(String numeroCuenta);
}
