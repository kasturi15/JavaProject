/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.client;

import com.dt.projects.database.api.entity.Menu;
import com.dt.projects.database.api.entity.Tax;
import com.dt.projects.database.api.services.TaxService;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author kastu
 */
public class TaxController implements Initializable {

    @FXML
    private TextField txtgst;
    
    @FXML
    private TextField txtcurrentgst;
    
    private TaxService taxService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        onStart();
        
        setGst();
        
    }    

    @FXML
    private void onConfirm(ActionEvent event) {
        if(isFieldValid())
        {
            try
            {
                Tax tax = new Tax();
                tax.setTax_id(1);
                tax.setGst(Integer.parseInt(txtgst.getText()));
                
                taxService.updateTax(tax);
                
                txtcurrentgst.setText(Integer.toString(tax.getGst())); 
                
                clearFields();
                
            }catch(RemoteException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    private void clearFields() {
        txtgst.setText("");
               
    }
    
    private boolean isFieldValid() {
        String errorMessage = "";
        
        if(txtgst.getText()==null || txtgst.getText().isEmpty())
        {
            errorMessage += "GST not entered!!";
        }
        
        if(errorMessage.length()==0)
        {
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Enter GST");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
    
    public void onStart()
    {
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry("localhost", 52360);
            taxService = (TaxService) registry.lookup("taxservice");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

   public void setGst()
    {
       try
       {
           List<Tax> list = new ArrayList<Tax>();
           
           int tax=0;
           
           list = taxService.getAllTax();
           
           for(int i = 0; i<list.size();i++)
            {
                Tax element = list.get(i);
                
                tax = element.getGst();
            }
           
           txtcurrentgst.setText(Integer.toString(tax));
       }catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
    

}