/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.api.services;

import com.dt.projects.database.api.entity.Menu;
import com.dt.projects.database.api.entity.Tax;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author kastu
 */
public interface TaxService extends Remote{
    
    //Tax getTaxById(int tax_id) throws RemoteException;
    
    List<Tax> getAllTax()throws RemoteException;
    
    void updateTax(Tax tax) throws RemoteException;
    
}
