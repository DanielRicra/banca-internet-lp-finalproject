
package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Operacion {
    
    private int id;
    private char tipo;
    private BigDecimal monto;
    private LocalDateTime fecha;
    private String numeroCuenta;

    public Operacion() {
    }

    public Operacion(char tipo, BigDecimal monto, LocalDateTime fecha, String numeroCuenta) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
        this.numeroCuenta = numeroCuenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    @Override
    public String toString() {
        return "Operacion{" + "id=" + id + 
            ", tipo=" + tipo + ", monto=" + monto + 
            ", fecha=" + fecha + 
            ", numeroCuenta=" + numeroCuenta + 
            '}';
    }
    
    
}
