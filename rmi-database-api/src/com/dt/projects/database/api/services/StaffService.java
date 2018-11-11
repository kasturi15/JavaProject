/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.api.services;

import com.dt.projects.database.api.entity.Staff;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author kastu
 */
public interface StaffService extends Remote{
    
    Staff insertStaff(Staff staff) throws RemoteException;
    
    void updateStaff(Staff staff) throws RemoteException;
    
    void deleteStaff(String staff_id) throws RemoteException;
    
    Staff getStaffById(String staff_id) throws RemoteException;
    
    List<Staff> getAllStaff() throws RemoteException;
}
