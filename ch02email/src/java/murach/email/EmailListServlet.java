package murach.email;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
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
        if (action == null){
            action = "join"; //default action
        }
        
        // perform action and set URL to appropiate page
        if (action.equals("join")){
            url = "/index.html";
        }else if (action.equals("add")){
            //getParameters from the request
            request.setCharacterEncoding("UTF-8");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            
            // store data in User object and save User Object in database
            User user = new User(firstName, lastName, email);
            UserDB.insert(user);
            
            // set User object in request object and set URL
            request.setAttribute("user", user);
            url = "/thanks.jsp"; // the "thanks" page
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
