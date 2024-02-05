package com.projectNAS.services.methods;

import com.projectNAS.services.model.BaseModel;
import com.projectNAS.services.model.Post;
import com.projectNAS.services.model.PostWithUsername;
import com.projectNAS.services.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author _
 */
public class PostMethod extends BaseModel {
    public List<Post> getAllPosts() throws SQLException {
       List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("post_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Timestamp createdDate = rs.getTimestamp("created_date");
                Timestamp updatedDate = rs.getTimestamp("updated_date");
                int userId = rs.getInt("user_id");
                posts.add(new Post(id, title, description, createdDate, updatedDate, userId));
            }
        }
        return posts;
    }
    
    public List<PostWithUsername> getAllPostsWithUsername() throws SQLException {
       List<PostWithUsername> posts = new ArrayList<>();
        String sql = "SELECT p.*, u.username FROM posts p JOIN users u ON p.user_id = u.user_id";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("post_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Timestamp createdDate = rs.getTimestamp("created_date");
                Timestamp updatedDate = rs.getTimestamp("updated_date");
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                Post post = new Post(id, title, description, createdDate, updatedDate, userId);
                posts.add(new PostWithUsername(post, username));
            }
        }
        return posts;
    }
    
    public Post getPostById(int id) throws SQLException {
       try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM posts WHERE post_id = ?")) {
           stmt.setInt(1, id);
           ResultSet rs = stmt.executeQuery();
           if (rs.next()) {
               String title = rs.getString("title");
               String description = rs.getString("description");
               Timestamp createdDate = rs.getTimestamp("created_date");
               Timestamp updatedDate = rs.getTimestamp("updated_date");
               int userId = rs.getInt("user_id");
               return new Post(id, title, description, createdDate, updatedDate, userId);
            }
        }
       return null;
    }
    
    public PostWithUsername getPostByIdWithUsername(int id) throws SQLException {
        String sql = "SELECT p.*, u.username FROM posts p JOIN users u ON p.user_id = u.user_id WHERE p.post_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                Timestamp createdDate = rs.getTimestamp("created_date");
                Timestamp updatedDate = rs.getTimestamp("updated_date");
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                Post post = new Post(id, title, description, createdDate, updatedDate, userId);
                return new PostWithUsername(post, username);
            }
        }
        return null;
    }
    
    public void createPost(Post post) throws SQLException {
    String sql = "INSERT INTO posts (title, description, created_date, updated_date, user_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getDescription());
            pstmt.setTimestamp(3, post.getCreatedDate());
            pstmt.setTimestamp(4, post.getUpdatedDate());
            pstmt.setInt(5, post.getOwnerID());
            pstmt.executeUpdate();
        }
    }
    
    public void updatePost(Post post) throws SQLException {
        String sql = "UPDATE posts SET title = ?, description = ?, created_date = ?, updated_date = ?, user_id = ? WHERE post_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getDescription());
            pstmt.setTimestamp(3, post.getCreatedDate());
            pstmt.setTimestamp(4, post.getUpdatedDate());
            pstmt.setInt(5, post.getOwnerID());
            pstmt.setInt(6, post.getId());
            pstmt.executeUpdate();
        }
    }
    public void deletePost(int id) throws SQLException {
        String sql = "DELETE FROM posts WHERE post_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
