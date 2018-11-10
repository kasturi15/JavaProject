/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.server.services;

import com.dt.projects.database.api.entity.Menu;
import com.dt.projects.database.api.services.MenuService;
import com.dt.projects.server.utilities.DatabaseConnection;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kastu
 */
public class MenuServiceImpl extends UnicastRemoteObject implements MenuService {

    public MenuServiceImpl() throws RemoteException {
    }
    
    @Override
    public Menu insertMenu(Menu menu) throws RemoteException {
        PreparedStatement statement = null;
        //INSERT INTO `menu`(`item_id`, `item_name`, `item_type`, `item_price`) VALUES ()
        String sql = "insert into menu(item_id, item_name, item_type, item_price) values (null,?, ?, ?)";
   
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        
            statement.setString(1, menu.getItem_name());
            statement.setString(2, menu.getItem_type());
            statement.setInt(3,menu.getItem_price());
            
            statement.executeUpdate();
            
            ResultSet result = statement.getGeneratedKeys();
            
            if(result.next())
            {
                menu.setItem_id(result.getLong(1));
            }
            
            result.close();
            
            return menu;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
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
    public void updateMenu(Menu menu) throws RemoteException {
        
        PreparedStatement statement = null;
        //UPDATE `menu` SET `item_id`=[value-1],`item_name`=[value-2],`item_type`=[value-3],`item_price`=[value-4] WHERE 1
        //String sql;
        String sql = "update menu set item_name = ?, item_type = ?, item_price = ? where item_id = ?";
   
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
        
            statement.setString(1, menu.getItem_name());
            statement.setString(2, menu.getItem_type());
            statement.setInt(3, menu.getItem_price());
            statement.setLong(4, menu.getItem_id());
            
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
    public void deleteMenu(Long item_id) throws RemoteException {
        
        PreparedStatement statement = null;
        //DELETE FROM `menu` WHERE 0
        String sql = "delete from menu where item_id = ?";
        
        try
        {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            
            statement.setLong(1, item_id);
            statement.executeUpdate();
            
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }finally
        {
            if(statement!=null)
            {
                try
                {
                    statement.close();
                }catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Menu getMenuById(Long item_id) throws RemoteException {
        PreparedStatement statement = null;
        
        String sql = "select * from menu where item_id = ?";
        
        try
        {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setLong(1, item_id);
            
            ResultSet result = statement.executeQuery();
            
            Menu menu = null;
            
            if(result.next())
            {
                menu.setItem_id(result.getLong("item_id"));
                menu.setItem_name(result.getString("item_name"));
                menu.setItem_type(result.getString("item_type"));
                menu.setItem_price(result.getInt("item_price"));
            }
            
            result.close();
            
            return menu;
        }catch(SQLException e)
        {
            e.printStackTrace();
            return null;
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

    @Override
    public List<Menu> getAllMenu() throws RemoteException {
       Statement statement = null;
       
       String sql = "select * from menu";
       
       try
       {
           statement = DatabaseConnection.getConnection().createStatement();
           
           ResultSet result = statement.executeQuery(sql);
           
           List<Menu> list = new ArrayList<Menu>();
           Menu menu = null;
           while(result.next())
           {
               menu = new Menu();
               menu.setItem_id(result.getLong("item_id"));
               menu.setItem_name(result.getString("item_name"));
               menu.setItem_type(result.getString("item_type"));
               menu.setItem_price(result.getInt("item_price"));
               list.add(menu);
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
