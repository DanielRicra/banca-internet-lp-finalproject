<%-- 
    Document   : home
    Created on : Mar 24, 2022, 1:57:23 PM
    Author     : Daniel_pc
--%>

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
                <h2>Bienvenido <span class="cliente"><%= cliente.getNombre() %></span></h2>
                
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
                <div class="operaciones flex-center">
                    <div class="deposito">
                        <form 
                            action="LoginController?accion=login"
                            method="POST"
                            class="form"
                        >
                            <div class="campo">
                                <label for="monto">Ingrese monto</label>
                                <input 
                                    id="monto"
                                    name="monto"
                                    type="number"
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
                </div>
            </div>
            
        </div>
        
        <script>
            const botones = document.querySelectorAll('.home__menu li');
            
            botones.forEach(boton => boton.addEventListener('click', (e) => {
                borrarBotonesActivos();
                if (e.target.tagName === 'LI') {
                    e.target.classList.add('active');
                } else if (e.target.tagName === 'P') {
                    e.target.parentElement.classList.add('active');
                }
            }));
            
            const borrarBotonesActivos = () => {
                botones.forEach(boton => boton.classList.remove('active'));
            };
           
        </script>
    </body>
</html>
