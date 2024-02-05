package com.projectNAS.services.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author _
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        
        if (!uri.startsWith("/login") && !uri.startsWith("/register")) {
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                // If session is null or user data is missing, redirect to login
                RequestDispatcher dispatcher = req.getRequestDispatcher("/login");
                dispatcher.forward(request, response);
                return;
            }
        }
        
        // If session exists and user data is present, continue with the request
        chain.doFilter(request, response);
    }
    
}
