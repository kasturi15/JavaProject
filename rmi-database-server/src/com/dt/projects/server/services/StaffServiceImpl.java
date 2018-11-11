/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.server.services;

import com.dt.projects.database.api.entity.Menu;
import com.dt.projects.database.api.entity.Staff;
import com.dt.projects.database.api.services.StaffService;
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
public class StaffServiceImpl extends UnicastRemoteObject implements StaffService{

    public StaffServiceImpl() throws RemoteException {
    }
    
    
    @Override
    public Staff insertStaff(Staff staff) throws RemoteException {
        
        PreparedStatement statement = null;
        PreparedStatement statement1 = null;
        
        //INSERT INTO `staff_detail`(`staff_id`, `staff_name`, `staff_pass`, `staff_email`, `staff_no`, `salary`) VALUES ()
        
        String sql = "insert into staff_detail(staff_id, staff_name, staff_pass, staff_email, staff_no, salary) values (?,?,?,?,?,?)";
        
        //INSERT INTO `staff_login`(`staff_id`, `staff_pass`) VALUES ([value-1],[value-2])
        String sql1 = "insert into staff_login(staff_id, staff_pass) values (?,?)";
        try
        {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            
            statement1 = DatabaseConnection.getConnection().prepareStatement(sql1);
            
            statement.setString(1, staff.getStaff_id());
            statement.setString(2, staff.getStaff_name());
            statement.setString(3, staff.getStaff_pass());
            statement.setString(4, staff.getStaff_email());
            statement.setInt(5, staff.getStaff_no());
            statement.setInt(6, staff.getSalary());
            
            statement.executeUpdate();
            
            statement1.setString(1, staff.getStaff_id());
            statement1.setString(2, staff.getStaff_pass());
            
            statement1.executeUpdate();
            
            /*ResultSet result = statement.executeQuery();
            
            if(result.next())
            {
                staff.setStaff_id(result.getString("staff_id"));
                staff.setStaff_name(result.getString("staff_name"));
                staff.setStaff_pass(result.getString("staff_pass"));
                staff.setStaff_email(result.getString("staff_email"));
                staff.setStaff_no(result.getInt("item_price"));
            }*/
            //result.close();
            
            return staff;
            
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
    public void updateStaff(Staff staff) throws RemoteException {
        
        PreparedStatement statement = null;
        
        PreparedStatement statement1 = null;
        
        //UPDATE `staff_detail` SET `staff_id`=[value-1],`staff_name`=[value-2],`staff_pass`=[value-3],`staff_email`=[value-4],`staff_no`=[value-5],`salary`=[value-6] WHERE 1
        String sql = "update staff_detail set staff_name = ?, staff_pass= ?, staff_email = ?, staff_no = ?, salary = ? where staff_id = ?";
    
        //UPDATE `staff_login` SET `staff_id`=[value-1],`staff_pass`=[value-2] WHERE 1
        String sql1 = "update staff_login set staff_pass = ? where staff_id = ?";
        
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            
            statement1 = DatabaseConnection.getConnection().prepareStatement(sql1);
        
            statement.setString(1, staff.getStaff_name());
            statement.setString(2, staff.getStaff_pass());
            statement.setString(3, staff.getStaff_email());
            statement.setInt(4, staff.getStaff_no());
            statement.setInt(5, staff.getSalary());
            statement.setString(6, staff.getStaff_id());
            
            statement.executeUpdate();
            
            statement1.setString(1, staff.getStaff_pass());
            statement1.setString(2, staff.getStaff_id());
            
            statement1.executeUpdate();
            
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
    public void deleteStaff(String staff_id) throws RemoteException {
        PreparedStatement statement = null;
        
        PreparedStatement statement1 = null;
        //DELETE FROM `staff_detail` WHERE 0
        String sql = "delete from staff_detail where staff_id = ?";
        
        String sql1 = "delete from staff_login where staff_id = ?";
        
        try
        {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            
            statement1 = DatabaseConnection.getConnection().prepareStatement(sql1);
            
            statement.setString(1, staff_id);
            statement.executeUpdate();
            
            statement1.setString(1, staff_id);
            statement1.executeUpdate();
            
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
    public Staff getStaffById(String staff_id) throws RemoteException {
       PreparedStatement statement = null;
        
        String sql = "select * from staff_detail where staff_id = ?";
        
        try
        {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setString(1, staff_id);
            
            ResultSet result = statement.executeQuery();
            
            Staff staff = null;
            
            if(result.next())
            {
                staff.setStaff_id(result.getString("staff_id"));
                staff.setStaff_name(result.getString("staff_name"));
                staff.setStaff_pass(result.getString("staff_pass"));
                staff.setStaff_email(result.getString("staff_email"));
                staff.setStaff_no(result.getInt("staff_no"));
                staff.setSalary(result.getInt("salary"));
            }
            
            result.close();
            
            return staff;
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
    public List<Staff> getAllStaff() throws RemoteException {
        Statement statement = null;
       
       String sql = "select * from staff_detail";
       
       try
       {
           statement = DatabaseConnection.getConnection().createStatement();
           
           ResultSet result = statement.executeQuery(sql);
           
           List<Staff> list = new ArrayList<Staff>();
           Staff staff = null;
           while(result.next())
           {
               staff = new Staff();
               staff.setStaff_id(result.getString("staff_id"));
                staff.setStaff_name(result.getString("staff_name"));
                staff.setStaff_pass(result.getString("staff_pass"));
                staff.setStaff_email(result.getString("staff_email"));
                staff.setStaff_no(result.getInt("staff_no"));
                staff.setSalary(result.getInt("salary"));
               list.add(staff);
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
