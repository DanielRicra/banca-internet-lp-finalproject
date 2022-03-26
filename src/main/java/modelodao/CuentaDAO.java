
package modelodao;

import Config.Conexion;
import interfaces.CrudCuenta;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Cuenta;

public class CuentaDAO implements CrudCuenta{

    private Connection connection = null;
    private Conexion conexion = new Conexion();
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private Cuenta cuenta;
    
    private final String SQL_GET_ALL = "SELECT * FROM cuentas";
    private final String SQL_GET_BY_ID = "SELECT * FROM cuentas WHERE numero_cuenta=?";
    private final String SQL_UPDATE_SALDO = "UPDATE cuentas SET saldo=? WHERE numero_cuenta=?";
    private final String SQL_DELETE = "DELETE FROM cuentas WHERE numero_cuenta=?";
    private final String SQL_INSERT = "INSERT INTO cuentas (numero_cuenta, saldo, id_cliente) "+
        " VALUES (?, ?, ?)";
        
    @Override
    public List<Cuenta> getAll() {
        List<Cuenta> cuentas = new ArrayList<>();
        
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement(SQL_GET_ALL);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                cuenta = new Cuenta();                
                cuenta.setNumeroCuenta(rs.getString("numero_cuenta"));
                cuenta.setSaldo(rs.getBigDecimal("saldo"));
                cuenta.setIdCliente(rs.getInt("id_cliente"));
                
                cuentas.add(cuenta);
            }
            
        } catch (SQLException e) {
            System.out.println("All cuentas: " + e.getStackTrace());
        } finally {
            if (connection != null) {
                conexion.close(ps);
                conexion.close(rs);
                conexion.close(connection);
            }
        }
        
        return cuentas;
    }

    @Override
    public Cuenta getCuenta(String numeroCuenta) {
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement(SQL_GET_BY_ID);
            ps.setString(1, numeroCuenta);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                cuenta = new Cuenta();
                cuenta.setNumeroCuenta(rs.getString("numero_cuenta"));
                cuenta.setSaldo(rs.getBigDecimal("saldo"));
                cuenta.setIdCliente(rs.getInt("id_cliente"));
            }
            
        } catch (SQLException e) {
            System.out.println("GET Cuenta: " + e.getStackTrace());
        } finally {
            if (connection != null ) {
                conexion.close(rs);
                conexion.close(ps);
                conexion.close(connection);
            }
        }
        return cuenta;
    }

    @Override
    public boolean modificarSaldo(String numero_cuenta, BigDecimal saldo) {
        boolean respuesta = false;
        
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement(SQL_UPDATE_SALDO);
            ps.setBigDecimal(1, cuenta.getSaldo());
            ps.setString(2, cuenta.getNumeroCuenta());
            
            respuesta = ps.executeUpdate() == 1;
            
        } catch (SQLException e) {            
            System.out.println("Modificar Cuenta: " + e.getStackTrace());
        } finally {
            if (connection != null ) {
                conexion.close(ps);
                conexion.close(connection);
            }
        }
        return respuesta;
    }

    @Override
    public boolean save(Cuenta cuenta) {
        boolean respuesta = false;
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement(SQL_INSERT);
            ps.setString(1, cuenta.getNumeroCuenta());
            ps.setBigDecimal(2, cuenta.getSaldo());
            ps.setInt(3, cuenta.getIdCliente());
            
            respuesta = ps.executeUpdate() == 1;
                        
        } catch (SQLException e) {
             System.out.println("Modificar Cuenta: " + e.getStackTrace());
        } finally {
            if (connection != null ) {
                conexion.close(ps);
                conexion.close(connection);
            }
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(String numeroCuenta) {
        boolean respuesta = false;
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement(SQL_DELETE);
            ps.setString(1, numeroCuenta);
            
            respuesta = ps.executeUpdate() == 1;
            
        } catch (SQLException e) {
             System.out.println("Delete Cuenta: " + e.getStackTrace());
        } finally {
            if (connection != null ) {
                conexion.close(ps);
                conexion.close(connection);
            }
        }
        return respuesta;
    }
    
}
