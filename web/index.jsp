<%@page import="com.projectNAS.services.model.PostWithUsername"%>
<%@page import="com.projectNAS.services.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="parts/meta.jsp" %>
        <title>Timeline</title>
        <%@ include file="parts/header.jsp" %>  
        <style>
        .timeline-card {
            border: 1px solid #dee2e6;
            border-radius: 0.25rem;
            padding: 1rem;
            margin-bottom: 1.5rem;
        }

        .timeline-card:hover {
            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
        }

        .timeline-item {
            margin-bottom: 1.5rem;
        }

        .timeline-item:last-child {
            margin-bottom: 0;
        }

        .timeline-badge {
            position: absolute;
            top: 0;
            left: -2.5rem;
            width: 2.5rem;
            height: 2.5rem;
            line-height: 2.5rem;
            background-color: #007bff;
            color: #fff;
            border-radius: 50%;
            border: 1px solid #007bff;
            font-size: 1rem;
            text-align: center;
        }
        
        .timeline-item a {
           text-decoration: none;
           color: inherit;
        }
        
        @media (max-width: 767.98px) {
            .timeline-badge {
                position: static;
                margin-right: 1rem;
                margin-top: 0.5rem;
            }

            .timeline-item {
                flex-direction: column;
            }

            .timeline-item .timeline-card {
                margin-top: 1rem;
            }
        }  
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
              <a class="navbar-brand" href="#"><h2>Timeline</h2></a>
              <div class="d-flex">
                <div class="dropdown dropstart">
                  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                    <%= ((User)request.getAttribute("user")).getUsername() %>
                  </button>
                  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <li><a class="dropdown-item" href="/profile">View Profile</a></li>
                    <li><a class="dropdown-item" href="/logout">Logout</a></li>
                  </ul>
                </div>
              </div>
            </div>
         </nav>
        
        <div class="container mt-5">
            <form action="" method="post">
              <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Post Title</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Enter post title">
              </div>
              <div class="mb-3">
                <label for="exampleFormControlTextarea1" class="form-label">Post Details</label>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="Enter post details"></textarea>
              </div>
              <button type="submit" class="btn btn-primary">Create Post</button>
            </form>
        </div>
        
        <hr class="my-5"/>
        
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="timeline">
                        <%
                        List<PostWithUsername> posts = (List<PostWithUsername>) request.getAttribute("posts");
                        if (posts != null && !posts.isEmpty()) {
                            int i = 1;
                            for (PostWithUsername post : posts) {
                        %>
                        <div class="timeline-item">
                            <div class="timeline-badge"><%= i++ %></div>
                            <div class="timeline-card">
                                <a href="/user/<%= post.getPost().getOwnerID() %>"><h5 class="timeline-title"><%= post.getUsername() %></h5></a>
                                <h6 class="timeline-subtitle">Posted on July 21, 2023</h6>
                                <a href="/post/<%= post.getPost().getId() %>">
                                    <h2><%= post.getPost().getTitle() %></h2>
                                    <p class="timeline-text"><%= post.getPost().getDescription() %></p>
                                </a>
                                <div class="d-flex align-items-center">
                                    <div class="d-flex align-items-center">
                                        <div class="bi bi-chat-left-text me-2"></div>
                                        <small>12 comments</small>
                                    </div>
                                    <div class="d-flex align-items-center">
                                        <div class="bi bi-hand-thumbs-up me-2"></div>
                                        <small>23 likes</small>
                                    </div>
                                    <div class="d-flex align-items-center mx-5">
                                        <button id="likeButton<%= i %>" class="btn btn-primary" data-liked="false">Like</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                           }
                        } else {
                        %>
                        <p>No posts found.</p>
                        <%
                        }
                        %>
                    </div>
                </div>
            </div>
        </div>
        
        <%@ include file="parts/footer.jsp" %>  
    </body>
</html>
