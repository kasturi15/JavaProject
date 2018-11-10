/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.api.services;

import com.dt.projects.database.api.entity.Login;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author kastu
 */
public interface LoginService extends Remote {
    
    boolean getLoginById(Login login) throws RemoteException;  
}
