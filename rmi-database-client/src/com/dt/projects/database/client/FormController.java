/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.client;

import com.dt.projects.database.api.entity.Menu;
import com.dt.projects.database.api.services.LoginService;
import com.dt.projects.database.api.services.MenuService;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FormController implements Initializable {

    @FXML
    private TextField txtname;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtprice;
    @FXML
    private TextField txttype;
    
    @FXML
    private TableView<Menu> tableView;
    @FXML
    private TableColumn<Menu, Long> colId;
    @FXML
    private TableColumn<Menu, String> colName;
    @FXML
    private TableColumn<Menu, String> colType;
    @FXML
    private TableColumn<Menu, Integer> colRate;
    
    private LoginController controller;
    private Main main;
    private MenuService menuService;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * @throws java.rmi.RemoteException
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        onStart();
        colId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("item_type"));
        colRate.setCellValueFactory(new PropertyValueFactory<>("item_price"));
        
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Menu>(){
            @Override
            public void changed(ObservableValue<? extends Menu> ov, Menu oldMenu, Menu newMenu) {
                
                if(newMenu != null)
                {
                    txtid.setText(Long.toString(newMenu.getItem_id()));
                    txtname.setText(newMenu.getItem_name());
                    txttype.setText(newMenu.getItem_type());
                    txtprice.setText(Integer.toString(newMenu.getItem_price())); 
                }
                else
                {
                    clearFields();
                }
            }
            
    });
    }    

    @FXML
    private void onInsert(ActionEvent event) {
        
        if(isFieldValid())
        {
           try
            {
                Menu menu = new Menu();
                menu.setItem_name(txtname.getText());
                menu.setItem_type(txttype.getText());
                menu.setItem_price(Integer.parseInt(txtprice.getText()));

                menu= menuService.insertMenu(menu);

                tableView.getItems().add(menu);

                clearFields();
            }
            catch(RemoteException ex)
            {
                ex.printStackTrace();
            }  
        }
        
    }

    @FXML
    private void onUpdate(ActionEvent event) {
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
                Menu menu = new Menu();
                menu.setItem_id(Long.valueOf(txtid.getText()));
                menu.setItem_name(txtname.getText());
                menu.setItem_type(txttype.getText());
                menu.setItem_price(Integer.parseInt(txtprice.getText()));
                
                menuService.updateMenu(menu);
                
                tableView.getItems().set(index, menu);
                
                clearFields();
                
            }catch(RemoteException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void onDelete(ActionEvent event) {
        
        try
        {
            Menu menu = tableView.getSelectionModel().getSelectedItem();
            if(menu == null)
            {
                return;
            }
            
            menuService.deleteMenu(menu.getItem_id());
            
            tableView.getItems().remove(menu);
            clearFields();
        }catch(RemoteException ex)
        {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void onRefresh(ActionEvent event) {
        try{
            tableView.getItems().setAll(menuService.getAllMenu());
        }catch(RemoteException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void setMain(Main main)
    {
        this.main = main;
        this.menuService = main.getMenuService();
        
        try {
            tableView.getItems().setAll(menuService.getAllMenu());
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
    
    public void onStart()
    {
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry("localhost", 52360);
            menuService = (MenuService) registry.lookup("menuservice");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            tableView.getItems().setAll(menuService.getAllMenu());
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        
        txtid.setText("");
        txtname.setText("");
        txttype.setText("");
        txtprice.setText(""); 
    }

    private boolean isFieldValid() {
        
        String errorMessage = "";
        
        if(txtname.getText()==null || txtname.getText().isEmpty())
        {
            errorMessage += "No item entered\n";
        }
        
        if(txttype.getText() == null || txttype.getText().isEmpty())
        {
            errorMessage += "Type not mentioned\n";
            
        }
        
        if(txtprice.getText()== null || txtprice.getText().isEmpty())
        {
            errorMessage += "Price not entered";
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
