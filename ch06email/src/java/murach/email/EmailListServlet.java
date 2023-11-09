package murach.email;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import murach.business.User;
import murach.data.UserDB;

/**
 *
 * @author alopezorozco
 */
public class EmailListServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/index.html";

        //get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; //default action
        }

        // perform action and set URL to appropiate page
        if (action.equals("join")) {            
            url = "/index.html";
        } else if(action.equals("modificar")){ //condicional para realizar modificaciones
            //obtenemos el email pasado como parámetro
            String email = request.getParameter("email");
            
            //creamos un objeto de tipo user y buscamos sus datos en la bd
            User user = UserDB.findUserById(email);
            
            // establecemos el atributo user para mostrar sus datos en la página
            request.setAttribute("user", user);           
            
            url = "/editar.jsp";
        } else if (action.equals("editar-usuario")){ // se almacenan los cambios en la bd
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

           
            // store data in User object and save User Object in database
            User user = new User(firstName, lastName, email);
            int result = UserDB.update(user);

            //si resultado es mayor que 0 entonces se realizó con éxito la operación
            if (result > 0) {
                request.setAttribute("mensaje", "Los cambios han sido guardados");
                request.setAttribute("user", user);
                url = "/thanks.jsp"; // the "thanks" page
            } else {
                request.setAttribute("mensaje", "Hubo un problema al querer editar la información en la base de datos");
                request.setAttribute("error", murach.data.Error.descripcion);
                url = "/error.jsp";
            }
            
        }else if (action.equals("eliminar")) { //pide confirmación para eliminar el usuario
            //obtenemos el email del usuario a eliminar
            String email = request.getParameter("email");

            // pasamos a la página confirmar-eliminación el email a eliminar
            request.setAttribute("email", email);

            url = "/confirmar-eliminacion.jsp";
        } else if (action.equals("aceptar-eliminacion")) { //efectua la eliminación del usuario
            String email = request.getParameter("email");

            int result = UserDB.delete(email);

            if (result > 0) {
                //volvemos a realizar una consulta para actualizar la lista de usuarios
                List<User> users = UserDB.getAllUsers();

                //establecemos el valor del atributo users con la lista obtenida d
                //usuarios
                request.setAttribute("users", users);

                url = "/listado-de-usuarios.jsp";
            } else {
                request.setAttribute("mensaje", "Hubo un problema al querer eliminar el usuario de la base de datos");
                request.setAttribute("error", murach.data.Error.descripcion);
                url = "/error.jsp";
            }
        } else if (action.equals("listado")) {
            //declaramos un objeto de tipo ArrayList
            List<User> users = UserDB.getAllUsers();

            //establecemos el valor del atributo users con la lista obtenida d
            //usuarios
            request.setAttribute("users", users);

            // especificamos la página a mostrar
            url = "/listado-de-usuarios.jsp";
        } else if (action.equals("add")) {
            //getParameters from the request
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

           
            // store data in User object and save User Object in database
            User user = new User(firstName, lastName, email);
            int result = UserDB.insert(user);

            //si resultado es mayor que 0 entonces se realizó con éxito la operación
            if (result > 0) {
                request.setAttribute("mensaje", "El usuario fue dado de alta en la bd");
                request.setAttribute("user", user);
                url = "/thanks.jsp"; // the "thanks" page
            } else {
                request.setAttribute("mensaje", "Hubo un problema al querer guardar la información en la base de datos");
                request.setAttribute("error", murach.data.Error.descripcion);
                url = "/error.jsp";
            }
        }

        //forward request and response objects to specified URL
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
