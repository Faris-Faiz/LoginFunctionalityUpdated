/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Faris Faiz
 * ikhmal
 */
public class ConnectDatabase {
    public static Connection connectdb() {
        Connection con = null;
        String url = "jdbc:derby://localhost:1527/userlogin";
        String username = "app";
        String password = "app";
        try {
            con = DriverManager.getConnection(url, username, password);
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
