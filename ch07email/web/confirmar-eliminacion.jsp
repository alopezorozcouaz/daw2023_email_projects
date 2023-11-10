<%-- 
    Document   : confirmar-eliminacion
    Created on : 9 nov. 2023, 7:47:35
    Author     : alopezorozco
--%>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Murach's Java Servlets and JSP - Confirmación de Eliminación</title>
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            .texto-negrita{
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class='container mt-5'>
            <div class='row'>
                <div class='col-lg-6'>
                    <h1>Confirmar Eliminación</h1>
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
                    <p>Esta seguro que desea eliminar el usuario con el<br>
                        email: <span class="texto-negrita">${email}</span></p>
                </div>
            </div>
        </div>
                
        <div class="container">
            <div class="row text-start">
                <div class="col">                    
                    <td><a href="emailList?action=aceptar-eliminacion&email=${email}" class="btn btn-danger" role="button">Eliminar</a></td>
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
