package com.projectNAS.services.controllers;

import com.projectNAS.services.methods.PostMethod;
import com.projectNAS.services.methods.UserMethod;
import com.projectNAS.services.model.PostWithUsername;
import com.projectNAS.services.model.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "TimelineController", urlPatterns = {"/tl"})
public class TimelineController extends HttpServlet {
    private PostMethod postMethod = null;

    public TimelineController() {
        postMethod = new PostMethod();
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
        try {
            List<PostWithUsername> posts = postMethod.getAllPostsWithUsername();
            request.setAttribute("posts", posts);
            
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("user");
            request.setAttribute("user", currentUser);
            
        } catch (SQLException ex) {
            Logger.getLogger(TimelineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
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
        response.sendRedirect("/");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Home Controller";
    }// </editor-fold>

}
