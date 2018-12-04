/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.api.services;

import com.dt.projects.database.api.entity.Menu;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author kastu
 */
public interface MenuService extends Remote{
    
    Menu insertMenu(Menu menu) throws RemoteException;
    
    void updateMenu(Menu menu) throws RemoteException;
    
    void deleteMenu(Long item_id) throws RemoteException;
    
    Menu getMenuById(Long item_id) throws RemoteException;
    
    List<Menu> getAllMenu()throws RemoteException;
    
    int ItemPrice(String item_name)throws RemoteException;
}
