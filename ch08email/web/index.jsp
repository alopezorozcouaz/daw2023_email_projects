<%-- 
    Document   : index
    Created on : 10 nov. 2023, 13:14:17
    Author     : alopezorozco
--%>
<%@ page language="java" import="java.util.*, murach.email.*" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Murach's Java Servlets and JSP</title>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Latest compiled and minified CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class='container mt-5'>
            <div class='row'>
                <div class='col'>
                    <h1>Join our email list</h1>
                    <p>To join our email list, enter your name and email address below.</p>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row text-end">
                <div class="col">                    
                    <a href="emailList?action=listado" class="btn btn-info" role="button">Listado de Usuarios</a>
                    <hr>
                </div>
            </div>
        </div>

        <div class='container'>
            <div class='row'>
                <div class='col'>
                    <form action='emailList' method='post' accept-charset="UTF-8">
                        <input type="hidden" name="action" value="add">

                        <div class='mb-3 mt-3'>
                            <label for="email" class="form-label">Email:</label>
                            <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" required>
                        </div>

                        <div class='mb-3 mt-3'>
                            <label for="first-name" class="form-label">First Name:</label>
                            <input type="text" class="form-control" id="firstName" placeholder="Enter Name" name="firstName" required>
                        </div>

                        <div class='mb-3 mt-3'>
                            <label for="last-name" class="form-label">Last Name:</label>
                            <input type="text" class="form-control" id="lastName" placeholder="Enter Last Name" name="lastName" required>
                        </div>
                        
                        <div class='mb-3 mt-3'>
                            <label for='miSelect'>Rol: </label>
                            <select class='form-control' id='miRol' name='miRol'>
                                <!-- uso de JSTL para iterar el ArrayList -->
                                <c:forEach var="opcion" items="${roles}">
                                    <option value='${opcion.id}'>${opcion.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-primary">Enviar</button>
                    </form>
                </div>
            </div>
        </div>


    </body>
</html>
