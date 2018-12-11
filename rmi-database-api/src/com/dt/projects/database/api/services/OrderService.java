/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.api.services;

import com.dt.projects.database.api.entity.Order;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author kastu
 */
public interface OrderService extends Remote{
    
    Order insertOrder(Order order) throws RemoteException;
    
    void updateOrder(Order order) throws RemoteException;
    
    void deleteOrder(int order_id) throws RemoteException;
    
    Order getOrderById(int order_id) throws RemoteException;
    
    int getCurrentGst()throws RemoteException;
    
    List<Order> getAllOrder() throws RemoteException;
    
    //List<BillSummary> getBillDetail()throws RemoteException;
    
}
