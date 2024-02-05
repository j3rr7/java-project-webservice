<%@page import="com.projectNAS.services.model.Post"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% Post currentPost = (Post) request.getAttribute("post"); %>

<!DOCTYPE html>
<html>
    
    <head>
        <%@ include file="parts/meta.jsp" %>
        <title>Post Detail</title>
        <%@ include file="parts/header.jsp" %>  
    </head>
    <body>
        <% if (currentPost != null) { %>
        <div class="container mt-5">
            <h1><%= currentPost.getTitle() %></h1>
            
            <p>
                <%= currentPost.getDescription()%>
            </p>
        </div>
        <% } else { %>
        <div><h2>Post Not Found</h2></div>
        <% } %>
        <%@ include file="parts/footer.jsp" %>  
    </body>
</html>
