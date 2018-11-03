/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.project.client;

import com.dt.project.api.LuckyNumber;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author kastu
 */
public class Main {
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
        
        Registry registry = LocateRegistry.getRegistry("localhost", 52360);
        
        LuckyNumber luckyNumber = (LuckyNumber) registry.lookup("Lucky");
        
        Scanner input = new Scanner(System.in);
        
        String again = "yes";
        
        while(again.equalsIgnoreCase("yes"))
        {
            System.out.println("Please input the lucky number??");
            String response = luckyNumber.process(input.nextLine());
            System.out.println(response);
            
            System.out.println("\nInput again??");
            again = input.nextLine();
        }
    }
    
}
