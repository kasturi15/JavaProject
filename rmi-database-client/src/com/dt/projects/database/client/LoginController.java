/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.client;

import com.dt.projects.database.api.entity.Login;
import com.dt.projects.database.api.services.LoginService;
import com.dt.projects.database.api.services.MenuService;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author kastu
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtlogid;
    @FXML
    private PasswordField txtpass;
    
    @FXML
    private AnchorPane loginPage;
    
    private Main main;
    private LoginService loginService;
    private MenuService menuService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*Login login = new Login();
        txtlogid.setText(login.getStaff_id());
        txtpass.setText(login.getStaff_pass());*/
        clearFields();
    }    

    @FXML
    private void onLogin(ActionEvent event) throws RemoteException, NotBoundException, IOException {
        
        Registry registry = LocateRegistry.getRegistry("localhost", 52360);
        loginService = (LoginService) registry.lookup("service");
        
        if(isFieldValid())
        {
            try
            { 
                Login login = new Login();
                login.setStaff_id(txtlogid.getText());
                login.setStaff_pass(txtpass.getText());

                boolean result = loginService.getLoginById(login);

                if(result)
                {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
                    loginPage.getChildren().setAll(pane);
                    
                    /*Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.setTitle("Success");
                    alert.setHeaderText("Welcome");
                    alert.setContentText("Thank God ho gya");
                    alert.showAndWait();*/
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.setTitle("Failed");
                    alert.setHeaderText("Incorrect!!");
                    alert.setContentText("Incorrect Id or password");
                    alert.showAndWait();
                }
                
                clearFields();
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }

    }

    @FXML
    private void onReset(ActionEvent event) {
        
        clearFields();
    }
    
    public void setMain(Main main)
    {
        this.main = main;
        this.loginService = main.getLoginService();
    }

    private void clearFields() {
        txtlogid.setText("");
        txtpass.setText("");
        
    }

    private boolean isFieldValid() {
        String errorMessage = "";
        
        if(txtlogid.getText()==null || txtlogid.getText().isEmpty())
        {
            errorMessage += "Login Id not entered!!";
        }
        if(txtpass.getText() == null || txtpass.getText().isEmpty())
        {
            errorMessage += "Password not Entered!!";
        }
        
        if(errorMessage.length()==0)
        {
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Enter all the fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
    
}