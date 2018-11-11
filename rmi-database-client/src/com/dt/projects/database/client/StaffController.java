/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.client;

import com.dt.projects.database.api.entity.Menu;
import com.dt.projects.database.api.entity.Staff;
import com.dt.projects.database.api.services.MenuService;
import com.dt.projects.database.api.services.StaffService;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author kastu
 */
public class StaffController implements Initializable {

    @FXML
    private TextField txtstaffname;
    @FXML
    private TextField txtstaffid;
    @FXML
    private TextField txtstaffpass;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtno;
    @FXML
    private TextField txtsalary;
    @FXML
    private TableColumn<Staff, String> colId;
    @FXML
    private TableColumn<Staff, String> colname;
    @FXML
    private TableColumn<Staff, String> colpass;
    @FXML
    private TableColumn<Staff, String> colemail;
    @FXML
    private TableColumn<Staff, Integer> colphone;
    @FXML
    private TableColumn<Staff, Integer> colsalary;
    @FXML
    private TableView<Staff> tableView;
    
    private StaffService staffService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        onStart();
        colId.setCellValueFactory(new PropertyValueFactory<Staff, String>("staff_id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("staff_name"));
        colpass.setCellValueFactory(new PropertyValueFactory<>("staff_pass"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("staff_email"));
        colphone.setCellValueFactory(new PropertyValueFactory<>("staff_no"));
        colsalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Staff>(){
            @Override
            public void changed(ObservableValue<? extends Staff> ov, Staff oldStaff, Staff newStaff) {
                
                if(newStaff != null)
                {
                    txtstaffid.setText(newStaff.getStaff_id());
                    txtstaffname.setText(newStaff.getStaff_name());
                    txtstaffpass.setText(newStaff.getStaff_pass());
                    txtemail.setText(newStaff.getStaff_email());
                    txtno.setText(Integer.toString(newStaff.getStaff_no()));
                    txtsalary.setText(Integer.toString(newStaff.getSalary()));
                }
                else
                {
                    clearFields();
                }
            }
            
    });
    }    

    @FXML
    private void onInsertStaff(ActionEvent event) {
        
        if(isFieldValid())
        {
           try
            {
                Staff staff = new Staff();
                staff.setStaff_id(txtstaffid.getText());
                staff.setStaff_name(txtstaffname.getText());
                staff.setStaff_pass(txtstaffpass.getText());
                staff.setStaff_email(txtemail.getText());
                staff.setStaff_no(Integer.parseInt(txtno.getText()));
                staff.setSalary(Integer.parseInt(txtsalary.getText()));

                staff= staffService.insertStaff(staff);

                tableView.getItems().add(staff);

                clearFields();
            }
            catch(RemoteException ex)
            {
                ex.printStackTrace();
            }  
        }
    }

    @FXML
    private void onDeleteStaff(ActionEvent event) {
        
        try
        {
            Staff staff= tableView.getSelectionModel().getSelectedItem();
            if(staff == null)
            {
                return;
            }
            
            staffService.deleteStaff(staff.getStaff_id());
            
            tableView.getItems().remove(staff);
            clearFields();
        }catch(RemoteException ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onUpdateStaff(ActionEvent event) {
        int index = tableView.getSelectionModel().getSelectedIndex();
        
        if(index == -1)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Update");
            alert.setHeaderText("No item selected");
            alert.setContentText("Please select an item in the table");
            alert.showAndWait();
            return;
        }
        
        if(isFieldValid())
        {
            try
            {
                Staff staff = new Staff();
                staff.setStaff_id(txtstaffid.getText());
                staff.setStaff_name(txtstaffname.getText());
                staff.setStaff_pass(txtstaffpass.getText());
                staff.setStaff_email(txtemail.getText());
                staff.setStaff_no(Integer.parseInt(txtno.getText()));
                staff.setSalary(Integer.parseInt(txtsalary.getText()));
                
                staffService.updateStaff(staff);
                
                tableView.getItems().set(index, staff);
                
                clearFields();
                
            }catch(RemoteException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void onRefreshStaff(ActionEvent event) {
        try{
            tableView.getItems().setAll(staffService.getAllStaff());
        }catch(RemoteException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void onStart()
    {
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry("localhost", 52360);
            staffService = (StaffService) registry.lookup("staffservice");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            tableView.getItems().setAll(staffService.getAllStaff());
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        
        txtstaffid.setText("");
        txtstaffname.setText("");
        txtemail.setText("");
        txtno.setText("");
        txtsalary.setText("");
        txtstaffpass.setText("");
    }
    
    private boolean isFieldValid() {
        
        String errorMessage = "";
        
        if(txtstaffid.getText()==null || txtstaffid.getText().isEmpty())
        {
            errorMessage += "Staff id not entered\n";
        }
        
        if(txtstaffname.getText() == null || txtstaffname.getText().isEmpty())
        {
            errorMessage += "Staff name not entered\n";
            
        }
        
        if(txtstaffpass.getText()== null || txtstaffpass.getText().isEmpty())
        {
            errorMessage += "Password not entered\n";
        }
        
        if(txtemail.getText()== null || txtemail.getText().isEmpty())
        {
            errorMessage += "Email not entered\n";
        }
        
        if(txtno.getText()== null || txtno.getText().isEmpty())
        {
            errorMessage += "Phone number not entered\n";
        }
        
        if(txtsalary.getText()== null || txtsalary.getText().isEmpty())
        {
            errorMessage += "salary not entered";
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
