
package app;

import java.util.List;
import modelodao.ClienteDAO;
import models.Cliente;

public class Main {
    
    public static void main(String[] args) {
        
        ClienteDAO clienteDAO = new ClienteDAO();
        
        System.out.println("Get Cliente por ID: ");
        Cliente cliente5 = clienteDAO.getCliente(5);
        System.out.println(cliente5);
        
        /*
        System.out.println("Modificar un cliente:");
        cliente5.setNombre(cliente5.getNombre() + " Actualizado");
        boolean modificar = clienteDAO.modificar(cliente5);
        System.out.println("Modificado: " + modificar);
        */
        /*
        Cliente nuevoCliente = new Cliente("Nuevo", "Av. Test", "test", "12345670", "123456741");
        System.out.println("Agregar nuevo cliente: ");
        boolean save = clienteDAO.save(nuevoCliente);
        System.out.println("Se guardo ? " + save);
        */
        /*
        boolean eliminar = clienteDAO.eliminar(7);
        System.out.println("Se elimino? " + eliminar);
        */
        System.out.println("Todos los clientes: ");
        List<Cliente> clientes = clienteDAO.getAll();
        clientes.forEach(System.out::println);
    }
}
