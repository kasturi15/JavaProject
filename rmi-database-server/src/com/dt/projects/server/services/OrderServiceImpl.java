/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.server.services;

import com.dt.projects.database.api.entity.Order;
import com.dt.projects.database.api.entity.Tax;
import com.dt.projects.database.api.services.MenuService;
import com.dt.projects.database.api.services.OrderService;
import com.dt.projects.server.utilities.DatabaseConnection;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kastu
 */
public class OrderServiceImpl extends UnicastRemoteObject implements OrderService {

    public OrderServiceImpl() throws RemoteException {
    }
    
    @Override
    public Order insertOrder(Order order) throws RemoteException {
        PreparedStatement statement = null;
        //INSERT INTO `order_detail`(`order_id`, `customer_name`, `customer_no`, `orders`, `bill`, `order_date`, `staff_id`, `order_status`) VALUES ()
        String sql = "insert into order_detail(order_id, customer_name, customer_no, orders, bill, order_date, staff_id, order_status) values (null,?, ?, ?, ?, ?, ?, ?)";
   
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        
            statement.setString(1, order.getCustomer_name());
            statement.setLong(2, order.getCustomer_no());
            statement.setString(3, order.getOrders());
            statement.setInt(4, order.getBill());
            statement.setDate(5, Date.valueOf(order.getOrder_date().toString()));
            statement.setString(6, order.getStaff_id());
            statement.setString(7, order.getOrder_status());
            
            statement.executeUpdate();
            
            ResultSet result = statement.getGeneratedKeys();
            
            if(result.next())
            {
                order.setOrder_id(result.getInt(1));
            }
            
            result.close();
            
            return order;
            
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
    public void updateOrder(Order order) throws RemoteException {
        PreparedStatement statement = null;
        //UPDATE `order_detail` SET `order_id`=,`customer_name`=,`customer_no`=,`orders`=,`bill`=,`order_date`=,`staff_id`=,`order_status`=
        String sql = "update order_detail set customer_name = ?, customer_no = ?, orders = ?, bill = ?, order_date = ?, staff_id = ?, order_status = ? where order_id = ?";
   
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
        
            statement.setString(1, order.getCustomer_name());
            statement.setLong(2, order.getCustomer_no());
            statement.setString(3, order.getOrders());
            statement.setInt(4, order.getBill());
            statement.setDate(5, Date.valueOf(order.getOrder_date().toString()));
            statement.setString(6, order.getStaff_id());
            statement.setString(7, order.getOrder_status());
            statement.setInt(8, order.getOrder_id());
            
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
    public void deleteOrder(int order_id) throws RemoteException {
        PreparedStatement statement = null;
        //DELETE FROM `order_detail` WHERE 0
        String sql = "delete from order_detail where order_id = ?";
        
        try
        {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            
            statement.setInt(1, order_id);
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
    public Order getOrderById(int order_id) throws RemoteException {
        PreparedStatement statement = null;
        
        String sql = "select * from order_detail where order_id = ?";
        
        try
        {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setLong(1, order_id);
            
            ResultSet result = statement.executeQuery();
            
            Order order = null;
            
            if(result.next())
            {
                order.setOrder_id(result.getInt("order_id"));
                order.setCustomer_name(result.getString("customer_name"));
                order.setCustomer_no(result.getLong("customer_no"));
                order.setOrders(result.getString("orders"));
                order.setBill(result.getInt("bill"));
                order.setOrder_date((LocalDate)result.getObject("order_date"));
                order.setStaff_id(result.getString("staff_id"));
                order.setOrder_status(result.getString("order_status"));
            }
            
            result.close();
            
            return order;
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
    public List<Order> getAllOrder() throws RemoteException {
        Statement statement = null;
       
       String sql = "select * from order_detail";
       
       try
       {
           statement = DatabaseConnection.getConnection().createStatement();
           
           ResultSet result = statement.executeQuery(sql);
           
           List<Order> list = new ArrayList<Order>();
           Order order = null;
           while(result.next())
           {
               order = new Order();
               order.setOrder_id(result.getInt("order_id"));
                order.setCustomer_name(result.getString("customer_name"));
                order.setCustomer_no(result.getLong("customer_no"));
                order.setOrders(result.getString("orders"));
                order.setBill(result.getInt("bill"));
                order.setOrder_date(LocalDate.parse(result.getDate("order_date").toString()) );
                order.setStaff_id(result.getString("staff_id"));
                order.setOrder_status(result.getString("order_status"));
               list.add(order);
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

    @Override
    public int getCurrentGst() throws RemoteException {
        
        try {
            Statement statement = null;
            
            String sql = "select gst from tax where tax_id = 1 ";
            
            statement = DatabaseConnection.getConnection().createStatement();
            
            ResultSet result = statement.executeQuery(sql);
            
            Tax tax = null;
            
            if(result.first())
            {
                tax.setGst(result.getInt("gst"));
            }
            
            int t = tax.getGst();
           
          result.close();
          return t;  
            
        } catch (SQLException ex) {
            
            
            ex.printStackTrace();
            return 0;
            
        }
        
        
    }

    
}
