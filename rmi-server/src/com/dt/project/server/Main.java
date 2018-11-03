/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.project.server;

import com.dt.project.api.LuckyNumber;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author kastu
 */
public class Main {

    public static void main(String[] args) throws RemoteException
    {
        
            Registry registry = LocateRegistry.createRegistry(52360);
        
            LuckyNumberImpl luckyNumberImpl = new LuckyNumberImpl();
        
            LuckyNumber luckyNumber = (LuckyNumber) UnicastRemoteObject.exportObject(luckyNumberImpl,0);
        
            registry.rebind("Lucky",luckyNumber);
        
            System.out.println("Server is running");
        
        
    }
    
    
    
    
}
