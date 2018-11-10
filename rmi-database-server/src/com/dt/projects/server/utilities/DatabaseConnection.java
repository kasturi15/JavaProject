/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.server.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


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
/*public class DatabaseConnection {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DatabaseConnection() throws SQLException
    {
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/billing", "root", "");
            st= con.createStatement();
        }catch(Exception e)
        {
            System.out.println("Error: "+e);
        }
    }
    
    public void getdata()
    {
        try
        {
            String query = "select * from staff_login";
            rs = st.executeQuery(query);
            System.out.println("Records from Database");
            while(rs.next())
            {
                String username = rs.getString("staff_id");
                String password = rs.getString("staff_pass");
                System.out.println("Username: "+username+"\tPassword: "+password);
            }
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
    public void verify_user(String user,String pass)
    {
        try
        {
            //SELECT `staff_id`, `staff_pass` FROM `staff_login` WHERE staff_id = "kasturi"
            String query = "select * from staff_login where staff_id = '"+user+"' and staff_pass = '"+pass+"' ";
            rs = st.executeQuery(query);
            int count = 0;
            while(rs.next())
            {
                count=count + 1;
            }
            
            if(count==1)
            {
                System.out.println("Login Succesfull");
            }
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public static void main(String[] args) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        db.getdata();
        
    
    
}*/
