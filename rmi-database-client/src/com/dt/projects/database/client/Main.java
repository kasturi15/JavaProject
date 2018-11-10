/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.client;

import com.dt.projects.database.api.entity.Menu;
import com.dt.projects.database.api.services.LoginService;
import com.dt.projects.database.api.services.MenuService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author kastu
 */
public class Main extends Application{

    private MenuService menuService;
    private LoginService loginService;
    @Override
    public void start(Stage stage) throws Exception {
        
        /*Registry registry = LocateRegistry.getRegistry("localhost", 52360);
        
        menuService = (MenuService) registry.lookup("service");
        loginService = (LoginService) registry.lookup("service");*/
        
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }
    
    public MenuService getMenuService()
    {
        return menuService;
    }
    
    public LoginService getLoginService()
    {
        return loginService;
    }
    
    public static void main(String[] args) {
        
        launch(args);
    }

       
}