
package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import modelodao.ClienteDAO;
import modelodao.CuentaDAO;
import modelodao.OperacionDAO;
import models.Cliente;
import models.Cuenta;
import models.Operacion;

public class Test {
    
    public static void main(String[] args) {
        // === Test Cliente ===
        ClienteDAO clienteDAO = new ClienteDAO();
        
        System.out.println("Get Cliente por ID ");
        Cliente cliente5 = clienteDAO.getCliente(5);
        System.out.println(cliente5);
        
        /*
        System.out.println("Modificar un cliente");
        cliente5.setNombre(cliente5.getNombre() + " Actualizado");
        boolean modificar = clienteDAO.modificar(cliente5);
        System.out.println("Modificado: " + modificar);
        */
        /*
        Cliente nuevoCliente = new Cliente("Nuevo", "Av. Test", "test", "12345670", "123456741");
        System.out.println("Agregar nuevo cliente ");
        boolean save = clienteDAO.agregar(nuevoCliente);
        System.out.println("Se guardo ? " + save);
        */
        /*
        boolean eliminar = clienteDAO.eliminar(7);
        System.out.println("Se elimino? " + eliminar);
        */
        System.out.println("Todos los clientes");
        List<Cliente> clientes = clienteDAO.getClientes();
        clientes.forEach(System.out::println);
        
        // === Test Cuenta ===
        CuentaDAO cuentaDAO = new CuentaDAO();
        
        System.out.println("Obetner Cuenta by numero de cuenta");
        Cuenta cuenta = cuentaDAO.getCuenta("2011234789546");
        System.out.println("Cuenta 2: " + cuenta);
        
        /*
        System.out.println("Modificar saldo de un cuenta");
        cuenta.setSaldo(cuenta.getSaldo().add(new BigDecimal("50.50")));
        boolean modificarSaldo = cuentaDAO
        .modificarSaldo(cuenta.getNumeroCuenta(), cuenta.getSaldo());
        System.out.println("Modificado: " + modificarSaldo);
         */
        /*
        System.out.println("Creando cuenta nueva para cliente con id 1");
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setIdCliente(1);
        nuevaCuenta.setNumeroCuenta("9876543217415");
        nuevaCuenta.setSaldo(BigDecimal.ZERO);
        boolean saveCuenta = cuentaDAO.agregar(nuevaCuenta);
        System.out.println("Se guardo la cuenta? " + saveCuenta);
         */
        /*
        boolean seEliminoLaCuenta = cuentaDAO.eliminar("9876543217415");
        System.out.println("Se elimino la cuenta 9876543217415? " + seEliminoLaCuenta);
        */
        System.out.println("Todas las cuentas");
        List<Cuenta> cuentas = cuentaDAO.getCuentas();
        cuentas.forEach(System.out::println);
        
        
        // === Test Operacion ===
        
        OperacionDAO operacionDAO = new OperacionDAO();
        
        System.out.println("Obetner Operacion by Id");
        Operacion operacion = operacionDAO.getOperacion(1);
        System.out.println("Operacion: " + operacion);
        /*
        boolean saveOperacion = operacionDAO
            .save(new Operacion('D', new BigDecimal("100.00"), null, "2011234789546"));
        System.out.println("Se guardo la operacion? " + saveOperacion);
        */
        System.out.println("10 ultimas operaciones por numero de cuenta");
        operacionDAO.getByNumeroCuenta("2011234789546").forEach(System.out::println);
    }
}
