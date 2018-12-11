/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.client;

import com.dt.projects.database.api.entity.Order;
import com.dt.projects.database.api.services.OrderService;
import com.dt.projects.database.client.api.BillSummary;
import com.dt.projects.database.client.api.OrderDetail;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author kastu
 */
public class AdminbillController implements Initializable {

    ObservableList<String> list = FXCollections.observableArrayList("January","February","March","April","May","June","July","August","September","October","November","December");
    
    @FXML
    private TableView<BillSummary> tableView;
    
    @FXML
    private TableColumn<BillSummary, Object> coldate;
    @FXML
    private TableColumn<BillSummary, Integer> colordertotal;
    @FXML
    private TableColumn<BillSummary, Integer> colAmt;
    @FXML
    private TextField txttotalamt;
    @FXML
    private TextField txttotalorder;
    @FXML
    private ComboBox<String> combomonth;
    
    private OrderService orderService;
    
    BillSummary billSummary = null;
    
    int count = 0;
    
    int totalamt = 0;
    
    int nooforder = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onStart();
        getBill();
        
        combomonth.setItems(list);
        
        coldate.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        colordertotal.setCellValueFactory(new PropertyValueFactory<>("totalorder"));
        colAmt.setCellValueFactory(new PropertyValueFactory<>("totalamt"));
        
        
        
    }    
    
    public void onStart()
    {
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry("localhost", 52360);
            orderService = (OrderService) registry.lookup("orderservice");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void getBill()
    {
        try {
            List<Order> list = new ArrayList<Order>();
            List<BillSummary> blist = new ArrayList<BillSummary>();
            
            
            list = orderService.getAllOrder();
            
            for(int j = 0; j<list.size();j++)
            {
                Order element = list.get(j);
                
                count = 0;
                billSummary = new BillSummary();
                
                billSummary.setOrder_date(element.getOrder_date());
                billSummary.setTotalorder(++count);
                billSummary.setTotalamt(element.getBill());
                
                blist.add(billSummary);
                
                totalamt=totalamt+element.getBill();
                nooforder=nooforder+count;
                
                //tableView.getItems().add(billSummary);
            }
            
            tableView.getItems().setAll(blist);
            
            txttotalamt.setText(Integer.toString(totalamt));
            txttotalorder.setText(Integer.toString(nooforder));
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
    
}
