
package modelodao;

import Config.Conexion;
import interfaces.CrudOperacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Operacion;

public class OperacionDAO implements CrudOperacion{

    private Connection connection = null;
    private Conexion conexion = new Conexion();
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Operacion operacion;
    
    private final String SQL_GET_BY_NCUENTA = "SELECT * FROM operaciones WHERE numero_cuenta=?" + 
        " ORDER BY fecha DESC LIMIT 10";
    private final String SQL_INSERT = "INSERT INTO operaciones (tipo, monto, " + 
        "numero_cuenta) VALUES (?, ?, ?)";
    private final String SQL_DELETE = "DELETE FROM operaciones WHERE id=?";
    private final String SQL_GET_BY_ID = "SELECT * FROM operaciones WHERE id=?";
    
        
    @Override
    public List<Operacion> getByNumeroCuenta(String numeroCuenta) {
        List<Operacion> operaciones = new ArrayList<>();
        
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement(SQL_GET_BY_NCUENTA);
            ps.setString(1, numeroCuenta);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                operacion = new Operacion();
                operacion.setId(rs.getInt("id"));
                operacion.setTipo(rs.getString("tipo").charAt(0));
                operacion.setMonto(rs.getBigDecimal("monto"));
                operacion.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                operacion.setNumeroCuenta(rs.getString("numero_cuenta"));
                
                operaciones.add(operacion);
            }
            
        } catch (SQLException e) {
            System.out.println("GetByNumeroCuenta: " + e.getStackTrace());
        } finally {
            if (connection != null) {
                conexion.close(ps);
                conexion.close(rs);
                conexion.close(connection);
            }
        }
        
        return operaciones;
    }

    @Override
    public Operacion getOperacion(int id) {
        operacion = new Operacion();
        
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement(SQL_GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                operacion.setId(rs.getInt("id"));
                operacion.setTipo(rs.getString("tipo").charAt(0));
                operacion.setMonto(rs.getBigDecimal("monto"));
                operacion.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                operacion.setNumeroCuenta(rs.getString("numero_cuenta"));
                
            }
            
        } catch (SQLException e) {
            System.out.println("GetOperacion by ID: " + e.getStackTrace());
        } finally {
            if (connection != null) {
                conexion.close(ps);
                conexion.close(rs);
                conexion.close(connection);
            }
        }
        
        return operacion;
    }

    @Override
    public boolean modificar(Operacion operacion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean save(Operacion operacion) {
        boolean respuesta = false;
        
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement(SQL_INSERT);
            ps.setString(1, String.valueOf(operacion.getTipo()));
            ps.setBigDecimal(2, operacion.getMonto());
            ps.setString(3, operacion.getNumeroCuenta());
            
            respuesta = ps.executeUpdate() == 1;
            
        } catch (SQLException e) {
            System.out.println("GetOperacion by ID: " + e.getStackTrace());
        } finally {
            if (connection != null) {
                conexion.close(ps);
                conexion.close(connection);
            }
        }
        
        return respuesta;
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
