<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="parts/meta.jsp" %>
        <title>Register</title>
        <%@ include file="parts/header.jsp" %>  
    </head>
    <body>
        <div class="container mt-5">
            <h1>Registration Page</h1>
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <form action="/register" method="post">
                        <input type="hidden" name="action" value="register">
                        <div class="mb-3">
                          <label for="username" class="form-label">Username</label>
                          <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="mb-3">
                          <label for="password" class="form-label">Password</label>
                          <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <p><a href="/login">Sudah punya akun? Log in disini.</a></p>
                        <button type="submit" class="btn btn-primary">Register</button>
                    </form>
                </div>
            </div>
        </div>
        
        <%@ include file="parts/footer.jsp" %>  
    </body>
</html>
