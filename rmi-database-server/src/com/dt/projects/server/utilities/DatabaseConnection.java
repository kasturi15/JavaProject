/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.server.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kastu
 */
public class DatabaseConnection {
    
    private static Connection con;
    
    public static Connection getConnection()
    {
        if(con==null)
        {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                con= DriverManager.getConnection("jdbc:mysql://localhost:3306/billing", "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return con;
    }
    
}
