/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.server;

import com.dt.projects.server.utilities.DatabaseConnection;
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
        System.out.println("Server");
        Platform.exit();
    }
    
    public static void main(String[] args) {
        
        launch(args);
    }
    
}
