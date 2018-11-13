/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pawankumar
 */
public class AdminpageController implements Initializable {
   
    
    @FXML
    private Pane adminStage;
    @FXML
    private AnchorPane adminPane;
    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("staff1.fxml"));
            adminStage.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminpageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void onStaff(Event event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("staff1.fxml"));
        adminStage.getChildren().setAll(pane);
    }

    @FXML
    private void onMenu(Event event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("form.fxml"));
        adminStage.getChildren().setAll(pane);
    }

    @FXML
    private void onBill(Event event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("adminbill.fxml"));
        adminStage.getChildren().setAll(pane);
    }

    @FXML
    private void onTax(Event event) {
    }

    @FXML
    private void onLogout(ActionEvent event) throws IOException {
        
       AnchorPane pane = FXMLLoader.load(getClass().getResource("login.fxml"));
        adminPane.getChildren().setAll(pane);
    }

    

}