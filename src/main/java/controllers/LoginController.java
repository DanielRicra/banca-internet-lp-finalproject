
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelodao.ClienteDAO;
import models.Cliente;

/**
 *
 * @author Daniel_pc
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    
    private final String INICIO = "vistas/home.jsp";
    private final String LOGIN = "index.jsp";
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        System.out.println("GET");
        String accion = "";
        String action = request.getParameter("accion");
        
        System.out.println("Action: " + action);
        if ("listar".equalsIgnoreCase(action)) {
            accion = "vistas/listar.jsp";
        } else {
            accion = LOGIN;
        }
        
        RequestDispatcher vista = request.getRequestDispatcher(accion);
        vista.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String accion = "";
        String action = request.getParameter("accion");
        
        if ("login".equalsIgnoreCase(action)) {
            if (iniciarSesion(request, response)) {
                return;
            }
            request.setAttribute("mensaje", "Error: DNI o contraseña incorrecta!");
        } else if ("salir".equals(action)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("cliente", new Cliente());
            accion = LOGIN;
        } else {
            accion = LOGIN;
        }
        
        request.setAttribute("error", "DNI o password incorrectos");
        request.getRequestDispatcher(accion).forward(request, response);
    }

    private boolean iniciarSesion(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException {
        
        String dni = req.getParameter("dni");
        String password = req.getParameter("password");
        
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.getClienteByDNI(dni);
        if (cliente.getDni()!= null && cliente.getPassword().equals(password)){
            HttpSession session = req.getSession(true);
            session.setAttribute("cliente", cliente);
            req.getRequestDispatcher(INICIO).forward(req, res);
            return true;
        } 
        return false;
    }

}
