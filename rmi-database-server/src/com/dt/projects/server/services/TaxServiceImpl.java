/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.server.services;

import com.dt.projects.database.api.entity.Menu;
import com.dt.projects.database.api.entity.Tax;
import com.dt.projects.database.api.services.MenuService;
import com.dt.projects.database.api.services.TaxService;
import com.dt.projects.server.utilities.DatabaseConnection;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kastu
 */
public class TaxServiceImpl extends UnicastRemoteObject implements TaxService {
    
    public TaxServiceImpl() throws RemoteException {
    }

    @Override
    public void updateTax(Tax tax) throws RemoteException {
        PreparedStatement statement = null;
        //UPDATE `tax` SET `tax_id`=[value-1],`gst`=[value-2] WHERE 1
        String sql = "update tax set gst = ? where tax_id = ?";
   
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setInt(1, tax.getGst());
            statement.setInt(2, tax.getTax_id());
            
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            if(statement != null)
                try {
                    statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Tax> getAllTax() throws RemoteException {
        Statement statement = null;
       
       String sql = "select * from tax";
       
       try
       {
           statement = DatabaseConnection.getConnection().createStatement();
           
           ResultSet result = statement.executeQuery(sql);
           
           List<Tax> list = new ArrayList<Tax>();
           Tax tax = null;
           if(result.first())
           {
               tax = new Tax();
               tax.setTax_id(result.getInt("tax_id"));
               tax.setGst(result.getInt("gst"));
               list.add(tax);
           }
           
           result.close();
           return list;
       }catch(SQLException e)
       {
           e.printStackTrace();
           return null;
       }finally
       {
           if(statement != null)
           {
               try{
                   statement.close();
               }catch(SQLException ex)
               {
                   ex.printStackTrace();
               }
           }
       }
    }
    
}
