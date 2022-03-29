<%-- 
    Document   : home
    Created on : Mar 24, 2022, 1:57:23 PM
    Author     : Daniel_pc
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.List"%>
<%@page import="models.Operacion"%>
<%@page import="modelodao.OperacionDAO"%>
<%@page import="models.Cuenta"%>
<%@page import="modelodao.CuentaDAO"%>
<%@page import="models.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/estilos.css"/>
    <link rel="icon" href="images/create-contact.png">
    <title>New Peru Bank</title>
  </head>
  <body>
    <%
        Cliente cliente = new Cliente();
        if (session.getAttribute("cliente") != null) {
            cliente = (Cliente) session.getAttribute("cliente");
        }
    %>
    <div class="contenedor contenedor-home">
      <div class="home f-column">
        <h1 class="title">New Peru Bank</h1>
        <h2>Bienvenido <span class="cliente"><%= cliente.getNombre()%></span></h2>

        <p class="home__subtitle">Elija la operacion que desea realizar</p>
        <ul class="home__menu">
          <li>
            <p>Deposito</p>
          </li>
          <li>
            <p>Retiro</p>
          </li>
          <li>
            <p>Consulta de saldo</p>
          </li>
          <li>
            <a href="LoginController?accion=salir">Salir</a>
          </li>
        </ul>
        <div class="operaciones flex-center f-column">
          <div>
            <%
              Object msj = request.getAttribute("mensaje");
              if (msj != null) {
            %>
              <p id="mensaje" class="mensaje <%=msj.toString().contains("Error")? "error":"exito" %>">
                <%=msj.toString()%>
              </p>
            <% } %>
          </div>
          
          <div class="operacion" id="deposito">
            <h3>Deposito</h3>
            <form 
              action="HomeController?accion=deposito"
              method="POST"
              class="form"
              >
              <div class="campo">
                <label for="monto">Ingrese monto</label>
                <input 
                  id="monto"
                  name="monto"
                  type="number"
                  step="any"
                >
              </div>

              <input 
                name="dni"
                value="<%=cliente.getDni()%>"
                type="hidden"
                >

              <input  type="submit" value="Despositar" class="boton">
            </form>
          </div>

          <div class="operacion" id="retiro">
            <h3>Retiro</h3>
            <form 
              action="HomeController?accion=retiro"
              method="POST"
              class="form"
              >
              <div class="campo">
                <label for="monto">Ingrese monto a retirar</label>
                <input 
                  id="monto"
                  name="monto"
                  type="number"
                  step="any"
                >
              </div>

              <input 
                name="dni"
                value="<%=cliente.getDni()%>"
                type="hidden"
                >

              <input  type="submit" value="Retirar" class="boton">
            </form>
          </div>

          <div class="operacion" id="consulta">
            <h3>Consulta de Saldo</h3>
            <%
                CuentaDAO cuentaDAO = new CuentaDAO();
                Cuenta cuenta = cuentaDAO.getCuentaByIdCliente(cliente.getId());
            %>
            <p>Su saldo actual es: <span>S/ <%=cuenta.getSaldo()%></span></p>

            <div class="consulta-movimientos">
              <h3>Movimientos</h3>
              <table>
                <thead>
                  <tr>
                    <th>Tipo</th>
                    <th>Fecha y hora</th>
                    <th>Monto</th>
                 </tr>
                </thead>
                <tbody>
                  <%
                      OperacionDAO operacionDAO = new OperacionDAO();
                      List<Operacion> operaciones = operacionDAO
                          .getByNumeroCuenta(cuenta.getNumeroCuenta());
                      for (Operacion operacion : operaciones){
                  %>
                  <tr>
                    <td><%=operacion.getTipo()=='R'? "Retiro":"Desposito" %></td>
                    <td>
                      <%=
                        operacion.getFecha()
                          .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
                      %>
                    </td>
                    <td 
                      class="mov-monto <%=operacion.getTipo()=='R'?"rojo":"verde"%>"
                    >
                      S/ <%=operacion.getTipo()=='R'? "-" + operacion.getMonto():
                      "+" + operacion.getMonto() %>
                    </td>
                  </tr>
                  <% } %>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>

    </div>

    <script>
      const botones = document.querySelectorAll('.home__menu li');

      botones.forEach(boton => boton.addEventListener('click', (e) => {
          borrarBotonesActivos();
          const borrarMensajes = setTimeout(borrarMsj, 3000);
          if (e.target.tagName === 'LI') {
            e.target.classList.add('active');
          } else if (e.target.tagName === 'P') {
            e.target.parentElement.classList.add('active');
          }
          mostrarOperacion(e.target.innerHTML);
        }));

      const borrarBotonesActivos = () => {
        botones.forEach(boton => boton.classList.remove('active'));
      };

      const mostrarOperacion = (elemento) => {
        const deposito = document.querySelector('#deposito');
        const retiro = document.querySelector('#retiro');
        const consulta = document.querySelector('#consulta');

        if (elemento.includes('Deposito')) {
          deposito.classList.add('show');
          retiro.classList.remove('show');
          consulta.classList.remove('show');
        } else if (elemento.includes('Retiro')) {
          retiro.classList.add('show');
          consulta.classList.remove('show');
          deposito.classList.remove('show');
        } else if (elemento.includes('Consulta')) {
          consulta.classList.add('show');
          deposito.classList.remove('show');
          retiro.classList.remove('show');
        }
      };
      
      
      function borrarMsj() {
        const mensaje = document.querySelector('#mensaje');
        if (mensaje) {
          setTimeout(mensaje.remove(), 2300);
        }
      }
    </script>
  </body>
</html>
