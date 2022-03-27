<%-- 
    Document   : login
    Created on : Mar 24, 2022, 1:15:21 PM
    Author     : Daniel_pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/estilos.css"/>
        <link rel="icon" href="images/create-contact.png">
        <title>Login</title>
    </head>
    <body>
        <div class="contenedor flex-center f-column">
            <h1 class="title">New Perú Bank</h1>
            
            <div class="login flex-center">
                <h2 class="subtitle">Iniciar Sesión</h2>
                
                <form 
                    action="LoginController?accion=login"
                    method="POST"
                    class="form form-login"
                >
                    <div class="campo">
                        <label for="dni">DNI</label>
                        <input 
                            id="dni"
                            name="dni"
                            type="text"
                        >
                    </div>
                   
                    <div class="campo">
                        <label for="password">Contraseña</label>
                        <input 
                            id="password"
                            name="password"
                            type="password"
                        >
                    </div>
                    
                    <input  type="submit" value="Iniciar Sesión" class="boton">
                </form>
            </div>
        </div>
    </body>
</html>
