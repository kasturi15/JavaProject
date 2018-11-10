package com.dt.projects.server.services;

import com.dt.projects.database.api.entity.Login;
import com.dt.projects.database.api.services.LoginService;
import com.dt.projects.database.api.services.MenuService;
import com.dt.projects.server.utilities.DatabaseConnection;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kastu
 */
public class LoginServiceImpl extends UnicastRemoteObject implements LoginService {

    public LoginServiceImpl() throws RemoteException {
    }

    
    @Override
    public boolean getLoginById(Login login) throws RemoteException {
        PreparedStatement statement = null;
        int count = 0;
        
        String sql ="select * from staff_login where staff_id = ? and staff_pass = ?";
        
        try
        {
            statement= DatabaseConnection.getConnection().prepareStatement(sql);
            
            statement.setString(1, login.getStaff_id());
            statement.setString(2, login.getStaff_pass());
            
            ResultSet result = statement.executeQuery();
            
            if(result.next())
                count++;
            
            if(count==1)
            {
                result.close();
                return true;
            }
            else
            {
                result.close();
                return false;
            }    
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }finally
        {
            if(statement != null)
            {
                try 
                {
                    statement.close();
                }catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }
    
}
