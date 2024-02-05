package com.projectNAS.services.methods;
import com.projectNAS.services.model.BaseModel;
import com.projectNAS.services.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author _
 */
public class UserMethod extends BaseModel {
    
    public List<User> getAllUsers() throws SQLException {
       List<User> users = new ArrayList<>();
       String query = "SELECT * FROM `users`";
       ResultSet result_ = runSelectQuery(query);
       while (result_.next()) {
           User user = new User(result_.getInt("user_id"), result_.getString("username"), result_.getString("password"));
           users.add(user);
       }
       return users;
   }
    
    public User getUserById(int id) throws SQLException {
       String query = "SELECT * FROM `users` WHERE user_id = " + id;
       ResultSet result_ = runSelectQuery(query);
       if (result_.next()) {
           return new User(result_.getInt("user_id"), result_.getString("username"), result_.getString("password"));
       } else {
           return null;
       }
    }
    
    public User getUserByUsernamePassword(String username, String password) throws SQLException {
       String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
       ResultSet result_ = runSelectQuery(query);
       if (result_.next()) {
           return new User(result_.getInt("user_id"), result_.getString("username"), result_.getString("password"));
       } else {
           return null;
       }
    }
    
    public boolean doesUsernameExist(String username) throws SQLException {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
        return false;
    }
    
    public void createUser(String username, String password) throws SQLException {
        if (doesUsernameExist(username)) {
            throw new SQLException("Username already exists");
        }
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.executeUpdate();
    }
    
    public void updateUser(int id, String newUsername, String newPassword) throws SQLException {
        String query = "UPDATE users SET username = ?, password = ? WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, newUsername);
        stmt.setString(2, newPassword);
        stmt.setInt(3, id);
        stmt.executeUpdate();
    }

    public void deleteUser(int id) throws SQLException {
        String query = "DELETE FROM users WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
