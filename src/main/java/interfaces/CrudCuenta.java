
package interfaces;

import java.math.BigDecimal;
import java.util.List;
import models.Cuenta;

public interface CrudCuenta {
    public List<Cuenta> getCuentas();
    public Cuenta getCuenta(String numeroCuenta);
    public Cuenta getCuentaByIdCliente(int idCliente);
    public boolean modificarSaldo(String numero_cuenta, BigDecimal saldo);
    public boolean agregar(Cuenta cuenta);
    public boolean eliminar(String numeroCuenta);
}
