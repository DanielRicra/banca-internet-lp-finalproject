
package modelodao;

import Config.Conexion;
import interfaces.CrudCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Cliente;

public class ClienteDAO implements CrudCliente{

    private Connection connection = null;
    private Conexion conexion = new Conexion();
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private Cliente cliente;    
    
    private final String SQL_GET_ALL = "SELECT * FROM clientes"; 
    private final String SQL_GET_BY_ID = "SELECT * FROM clientes WHERE id=?";
    private final String SQL_GET_BY_DNI = "SELECT * FROM clientes WHERE dni=?";
    private final String SQL_INSERT = "INSERT INTO clientes (nombre, direccion, password," +
        "dni, telefono) VALUES (?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE clientes SET nombre=?, direccion=?, password=?," + 
        "dni=?, telefono=? WHERE id=?";
    private final String SQL_DELETE = "DELETE FROM clientes WHERE id=?";
    
    @Override
    public List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement(SQL_GET_ALL);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                cliente = new Cliente();
                
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDni(rs.getString("dni"));
                cliente.setPassword(rs.getString("password"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                
                clientes.add(cliente);
            }
            
            
        } catch (SQLException e) {
            System.out.println("GET ALL: " + e.getStackTrace());
        } finally {
            if (connection != null) {
                conexion.close(ps);
                conexion.close(rs);
                conexion.close(connection);
            }
        }
        
        return clientes;
    }

    @Override
    public Cliente getCliente(int id) {
        cliente = new Cliente();
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement(SQL_GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setPassword(rs.getString("password"));
                cliente.setDni(rs.getString("dni"));
                cliente.setTelefono(rs.getString("telefono"));
            }
            
        } catch (SQLException e) {
            System.out.println("Get Cliente: " + e.getStackTrace());
        } finally {
            if (connection != null) {
                conexion.close(ps);
                conexion.close(rs);
                conexion.close(connection);
            }
        }
        
        return cliente;
    }

    @Override
    public boolean modificar(Cliente cliente) {
        boolean respuesta = false;
        
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDireccion());
            ps.setString(3, cliente.getPassword());
            ps.setString(4, cliente.getDni());
            ps.setString(5, cliente.getTelefono());
            ps.setInt(6, cliente.getId());
            
            respuesta = ps.executeUpdate() == 1;
            
        } catch (SQLException e) {
            System.out.println("Get Cliente: " + e.getStackTrace());
        } finally {
            if (connection != null ) {
                conexion.close(connection);
                conexion.close(ps);
            }
        }
        return respuesta;
    }

    @Override
    public boolean save(Cliente cliente) {
        boolean respuesta = false;
        
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement(SQL_INSERT);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDireccion());
            ps.setString(3, cliente.getPassword());
            ps.setString(4, cliente.getDni());
            ps.setString(5, cliente.getTelefono());
            
            respuesta = ps.executeUpdate() == 1;
            
            
        } catch (SQLException e) {
            System.out.println("Save cleinte: " + e.getStackTrace());
        } finally {
            if(connection != null) {
                conexion.close(connection);
                conexion.close(ps);
            }
        }
        
        return respuesta;
    }

    @Override
    public boolean eliminar(int id) {
        boolean respuesta = false;
        
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            
            respuesta = ps.executeUpdate() == 1;
            
        } catch (SQLException e) {
             System.out.println("Delete cliente: " + e.getStackTrace());
        } finally {
            if (connection != null) {
                conexion.close(connection);
                conexion.close(ps);
            }
        }
        
        return respuesta;
    }

    @Override
    public Cliente getClienteByDNI(String dni) {
         cliente = new Cliente();
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement(SQL_GET_BY_DNI);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setPassword(rs.getString("password"));
                cliente.setDni(rs.getString("dni"));
                cliente.setTelefono(rs.getString("telefono"));
            }
            
        } catch (SQLException e) {
            System.out.println("Get Cliente by DNI: " + e.getStackTrace());
        } finally {
            if (connection != null) {
                conexion.close(ps);
                conexion.close(rs);
                conexion.close(connection);
            }
        }
        
        return cliente;
    }
}
