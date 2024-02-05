package com.projectNAS.services.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author _
 */
public class BaseModel {

    protected static Connection conn;
    protected Statement statement;
    protected ResultSet result;

    public BaseModel() {
        BaseModel.conn = this._getConnection();
        
        this.statement = null;
        this.result = null;
    }

    public final Connection _getConnection() {
        if (BaseModel.conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver")
                        .newInstance();
                return DriverManager.getConnection(
                        "jdbc:mysql://192.168.219.133:3306/dbdisprog",
                        "root", "password");
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
                System.out.println("Error di MyModel " + e);
            }
        }
        return BaseModel.conn;
    }
    
    public ResultSet runSelectQuery(String query) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    public int runNonSelectQuery(String query) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(query);
    }
}
