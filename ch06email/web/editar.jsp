<%-- 
    Document   : editar
    Created on : 9 nov. 2023, 9:18:33
    Author     : alopezorozco
--%>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Murach's Java Servlets and JSP - Editar Usuario</title>
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class='container mt-5'>
            <div class='row'>
                <div class='col-lg-6'>
                    <h1>Editar usuario</h1>
                </div>
                <div class='col-lg-6 text-end'>
                    <button class="btn btn-info" role="button" onclick='regresar();'>Listado de Usuarios</button>
                </div>
                <div class='col'>
                    <hr>
                </div>
            </div>
        </div>
        
        <div class='container'>
            <div class='row'>
                <div class='col'>
                    <form action='emailList' method='post' accept-charset="UTF-8">
                        <input type="hidden" name="action" value="editar-usuario">

                        <div class='mb-3 mt-3'>
                            <label for="email" class="form-label">Email:</label>
                            <input type="email" class="form-control" id="email" 
                                   placeholder="Enter email" name="email" value="${user.email}" required readonly>
                        </div>

                        <div class='mb-3 mt-3'>
                            <label for="first-name" class="form-label">First Name:</label>
                            <input type="text" class="form-control" id="firstName" 
                                   placeholder="Enter Name" name="firstName" value="${user.firstName}" required>
                        </div>

                        <div class='mb-3 mt-3'>
                            <label for="last-name" class="form-label">Last Name:</label>
                            <input type="text" class="form-control" id="lastName" 
                                   placeholder="Enter Last Name" name="lastName" value="${user.lastName}" required>
                        </div>

                        <button type="submit" class="btn btn-primary">Enviar</button>
                    </form>
                </div>
            </div>
        </div>
        <script>
            function regresar(){
                window.history.back();
            }
        </script>
    </body>
</html>
