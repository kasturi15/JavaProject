/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.server;

import com.dt.projects.database.api.services.MenuService;
import com.dt.projects.server.services.LoginServiceImpl;
import com.dt.projects.server.services.MenuServiceImpl;
import com.dt.projects.server.services.OrderServiceImpl;
import com.dt.projects.server.services.StaffServiceImpl;
import com.dt.projects.server.utilities.DatabaseConnection;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 *
 * @author kastu
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        DatabaseConnection.getConnection();
        
        Registry registry = LocateRegistry.createRegistry(52360);
        
        MenuServiceImpl menuServiceImpl = new MenuServiceImpl();
        LoginServiceImpl loginServiceImpl = new LoginServiceImpl();
        StaffServiceImpl staffServiceImpl = new StaffServiceImpl();
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        
        //MenuService menuService = (MenuService) UnicastRemoteObject.exportObject(menuServiceImpl,0);
        
        registry.rebind("menuservice", menuServiceImpl);
        registry.rebind("service", loginServiceImpl);
        registry.rebind("staffservice", staffServiceImpl);
        registry.rebind("orderservice", orderServiceImpl);
        System.out.println("Server is running");
    }
    
    public static void main(String[] args) {
        
        launch(args);
        
    }
    
}
