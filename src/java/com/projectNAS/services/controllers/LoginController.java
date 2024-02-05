package com.projectNAS.services.controllers;

import com.projectNAS.services.methods.UserMethod;
import com.projectNAS.services.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author _
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private UserMethod userMethod = null;
    
    public LoginController() {
        userMethod = new UserMethod();
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = null;
        try {
            user = userMethod.getUserByUsernamePassword(username, password);
        } catch (SQLException ex) {
            request.setAttribute("javax.servlet.error.exception", ex);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        
        if (user != null) {
            // If a user is found, start a new session and set the user as an attribute
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/tl");
        } else {
            // If no user is found, redirect to login.jsp
            response.sendRedirect("/login");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servelet to handle login";
    }// </editor-fold>

}
