package com.projectNAS.services.controllers;

import com.projectNAS.services.methods.PostMethod;
import com.projectNAS.services.model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author _
 */
@WebServlet(name = "PostController", urlPatterns = {"/post/*"})
public class PostController extends HttpServlet {

    private PostMethod postMethod = null;
    
    public PostController() {
        postMethod = new PostMethod();
    }
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PostController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        String path = request.getRequestURI();
        
        // use regex to get pattern
        Pattern pattern = Pattern.compile("/post/(\\d+)");
        Matcher matcher = pattern.matcher(path);
        if (matcher.find()) {
            String postIdStr = matcher.group(1);
            int postId = Integer.parseInt(postIdStr);
            
            Post post = null;
            try {
                post = postMethod.getPostById(postId);
            } catch (SQLException ex) {
                Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (post != null) {
                request.setAttribute("post", post);
            }
            
            request.getRequestDispatcher("/post.jsp").forward(request, response);
        } else {
            // Handle the case where the path does not contain a number
        }
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
        String path = request.getRequestURI();
        switch (path) {
            case "/post/new":
                System.out.println("POST NEW STARTED");
                break;
            default:
                System.out.println("POST STARTED");
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
