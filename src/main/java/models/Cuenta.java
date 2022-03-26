
package models;

import java.math.BigDecimal;

public class Cuenta {
    
    private String numeroCuenta;
    private BigDecimal saldo;
    private int idCliente;

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "numeroCuenta=" + numeroCuenta + 
            ", saldo=" + saldo + 
            ", idCliente=" + idCliente + 
            '}';
    }
  
}
