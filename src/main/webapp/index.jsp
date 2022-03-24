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
        <title>Login</title>
    </head>
    <body>
        <div class="contenedor flex-center">
            <div class="login flex-center">
                <h1 class="title">Iniciar Sesión</h1>
                <form 
                    class="form form-login"
                >
                    <div class="campo">
                        <label for="dni"></label>
                        <input 
                            id="dni"
                            name="dni"
                            type="text"
                        >
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
