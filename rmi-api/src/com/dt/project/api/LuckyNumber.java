/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.project.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author kastu
 */
public interface LuckyNumber extends Remote {
    
    String process(String value) throws RemoteException;
    
}
