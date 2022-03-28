
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelodao.ClienteDAO;
import modelodao.CuentaDAO;
import modelodao.OperacionDAO;
import models.Cliente;
import models.Cuenta;
import models.Operacion;

public class HomeController extends HttpServlet {
    
    private final String INICIO = "vistas/home.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomeController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String accion = "";
        String action =  request.getParameter("accion");
        String  mensajes = "";
        
        if ("deposito".equals(action)) {
            if (depositar(request, response)) {
                request.setAttribute("mensaje", "Se deposito correctamente");
                request.getRequestDispatcher(INICIO).forward(request, response);
                return;
            } 
            mensajes = "Error al depositar";
        }
        
        request.setAttribute("mensaje", mensajes);
        request.getRequestDispatcher(INICIO).forward(request, response);
    }

    private boolean depositar(HttpServletRequest req, HttpServletResponse res) {
        String dni = req.getParameter("dni");
        BigDecimal monto = new BigDecimal(req.getParameter("monto"));
        boolean isSave = false;
         boolean isUpdate = false;
        if (monto.compareTo(BigDecimal.ZERO) == 1) {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.getClienteByDNI(dni);
            CuentaDAO cuentaDAO = new CuentaDAO();
            Cuenta cuenta = cuentaDAO.getCuentaByIdCliente(cliente.getId());
            OperacionDAO operacionDAO = new OperacionDAO();
            Operacion operacion = new Operacion('D', monto, null, cuenta.getNumeroCuenta());
            isSave = operacionDAO.save(operacion);
            if (isSave){
                isUpdate = cuentaDAO.modificarSaldo(cuenta.getNumeroCuenta(), 
                                                            cuenta.getSaldo().add(monto));
            }
            return isSave && isUpdate;
        }
        
        return false;
    }  
}
