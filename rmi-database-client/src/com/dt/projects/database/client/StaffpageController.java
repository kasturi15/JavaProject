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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author kastu
 */
public class StaffpageController implements Initializable {

    @FXML
    private AnchorPane adminPane;
    @FXML
    private Pane adminStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("order.fxml"));
            adminStage.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminpageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void onTakeOrder(Event event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("order.fxml"));
        adminStage.getChildren().setAll(pane);
    }

    @FXML
    private void onPendingOrder(Event event) {
    }

    @FXML
    private void onBill(Event event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("adminbill.fxml"));
        adminStage.getChildren().setAll(pane);
    }

    @FXML
    private void onLogout(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("login.fxml"));
        adminPane.getChildren().setAll(pane);
    }
    
}
