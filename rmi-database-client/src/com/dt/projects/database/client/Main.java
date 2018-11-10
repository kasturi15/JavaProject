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
        
        Registry registry = LocateRegistry.getRegistry("localhost", 52360);
        
        //menuService = (MenuService) registry.lookup("service");
        loginService = (LoginService) registry.lookup("service");
        
        /*Menu m = new Menu();
         m.setItem_name("Chicken Biryani boneless");
         m.setItem_type("Non-Veg");
         m.setItem_price(225);
         
         m = menuService.insertMenu(m);
         
         System.out.println(m.getItem_Id());
         System.out.println(m.getItem_name());*/
        
        
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        loader.setController(new MainController(path));
        Pane mainPane = loader.load();*/
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        //loader.setController(new FormController()); 
        
        Parent root = loader.load();
        
        LoginController controller = loader.getController();
        
        controller.setMain(this);
        
        stage.setScene(new Scene(root));
        
        stage.setTitle("Menu");
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