/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.client;

import com.dt.projects.database.api.entity.Menu;
import com.dt.projects.database.api.entity.Order;
import com.dt.projects.database.api.services.MenuService;
import com.dt.projects.database.api.services.OrderService;
import com.dt.projects.database.client.api.OrderDetail;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class Order1Controller implements Initializable {
    
    ObservableList<Integer> qtylist = FXCollections.observableArrayList(1,2,3,4,5,6);

    @FXML
    private TextField txtcustname;
    @FXML
    private TextField txtcustphone;
    @FXML
    private DatePicker orderDate;
    @FXML
    private CheckBox checkburger;
    @FXML
    private ComboBox<String> comboburger;
    @FXML
    private ComboBox<Integer> burgerqty;
    @FXML
    private CheckBox checkpizza;
    @FXML
    private CheckBox checksandwich;
    @FXML
    private CheckBox checknoodles;
    @FXML
    private CheckBox checkother;
    @FXML
    private ComboBox<String> combopizza;
    @FXML
    private ComboBox<String> combosandwich;
    @FXML
    private ComboBox<String> combonoodles;
    @FXML
    private ComboBox<String> combodrinks;
    @FXML
    private ComboBox<Integer> pizzaqty;
    @FXML
    private ComboBox<Integer> sandwichqty;
    @FXML
    private ComboBox<Integer> noodlesqty;
    @FXML
    private ComboBox<Integer> otherqty;
    @FXML
    private CheckBox checkdrinks;
    @FXML
    private CheckBox checkshakes;
    @FXML
    private ComboBox<String> comboshakes;
    @FXML
    private ComboBox<String> comboother;
    @FXML
    private ComboBox<Integer> drinksqty;
    @FXML
    private ComboBox<Integer> shakesqty;
    @FXML
    private TextField txtsubtotal;
    @FXML
    private TextField txttax;
    @FXML
    private TextField txtamt;
    @FXML
    private TableView<OrderDetail> tableView;
    @FXML
    private TableColumn<OrderDetail, Integer> colid;
    @FXML
    private TableColumn<OrderDetail, String> colitem;
    @FXML
    private TableColumn<OrderDetail, Integer> colqty;
    @FXML
    private TableColumn<OrderDetail, Integer> colprice;
    
    @FXML
    private TableColumn<OrderDetail, Integer> colItemcost;
    
    private MenuService menuService;
    
    private OrderService orderService;
    
    private OrderDetail orderDetail;
    
    int itemtotal;
    int subtotal=0;
    int id =0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        addqtylist();  
        onStart();
        ComboData();
        
        colid.setCellValueFactory(new PropertyValueFactory<>("colid"));
        colitem.setCellValueFactory(new PropertyValueFactory<>("colname"));
        colprice.setCellValueFactory(new PropertyValueFactory<>("colprice"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("colqty"));
        colItemcost.setCellValueFactory(new PropertyValueFactory<>("colItemcost"));
        
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OrderDetail>(){
            @Override
            public void changed(ObservableValue<? extends OrderDetail> ov, OrderDetail oldOrderDetail, OrderDetail newOrderDetail) {
                
                if(newOrderDetail != null)
                {
                }
                else
                {
                    clearFields();
                }
            }
            
    });
                
    }    

    @FXML
    private void addburger(ActionEvent event) {
        
        try {
            
                orderDetail = new OrderDetail();
                String burgerItem= comboburger.getValue();
                int qty = burgerqty.getValue();
                int price = menuService.ItemPrice(burgerItem);
                
                itemtotal = qty*price;
                subtotal = subtotal+ itemtotal;
                
                orderDetail.setColid(++id);
                orderDetail.setColname(burgerItem);
                orderDetail.setColprice(price);
                orderDetail.setColqty(qty);
                orderDetail.setColItemcost(itemtotal);
            
                tableView.getItems().add(orderDetail);
                
                txtsubtotal.setText(Integer.toString(subtotal)); 
            
        } catch (RemoteException ex) {
                ex.printStackTrace();
        }
    }

    @FXML
    private void addpizza(ActionEvent event) {
        
        try {
            
                orderDetail = new OrderDetail();
                String pizzaItem= combopizza.getValue();
                int qty = pizzaqty.getValue();
                int price = menuService.ItemPrice(pizzaItem);
                
                itemtotal = qty*price;
                subtotal = subtotal+ itemtotal;
                
                orderDetail.setColid(++id);
                orderDetail.setColname(pizzaItem);
                orderDetail.setColqty(qty);
                orderDetail.setColprice(price);
                orderDetail.setColItemcost(itemtotal);
            
                tableView.getItems().add(orderDetail);
                
                txtsubtotal.setText(Integer.toString(subtotal)); 
            
        } catch (RemoteException ex) {
                ex.printStackTrace();
        }
    }

    @FXML
    private void addsandwich(ActionEvent event) {
        
        try {
            
                orderDetail = new OrderDetail();
                String Item= combosandwich.getValue();
                int qty = sandwichqty.getValue();
                int price = menuService.ItemPrice(Item);
                
                itemtotal = qty*price;
                subtotal = subtotal+ itemtotal;
                
                orderDetail.setColid(++id);
                orderDetail.setColname(Item);
                orderDetail.setColqty(qty);
                orderDetail.setColprice(price);
                orderDetail.setColItemcost(itemtotal);
            
                tableView.getItems().add(orderDetail);
                
                txtsubtotal.setText(Integer.toString(subtotal)); 
            
        } catch (RemoteException ex) {
                ex.printStackTrace();
        }
    }

    @FXML
    private void addnoodles(ActionEvent event) {
        
        try {
            
                orderDetail = new OrderDetail();
                String Item= combonoodles.getValue();
                int qty = noodlesqty.getValue();
                int price = menuService.ItemPrice(Item);
                
                itemtotal = qty*price;
                subtotal = subtotal+ itemtotal;
                
                orderDetail.setColid(++id);
                orderDetail.setColname(Item);
                orderDetail.setColqty(qty);
                orderDetail.setColprice(price);
                orderDetail.setColItemcost(itemtotal);
            
                tableView.getItems().add(orderDetail);
                
                txtsubtotal.setText(Integer.toString(subtotal)); 
            
        } catch (RemoteException ex) {
                ex.printStackTrace();
        }
    }

    @FXML
    private void addother(ActionEvent event) {
        
        try {
            
                orderDetail = new OrderDetail();
                String Item= comboother.getValue();
                int qty = otherqty.getValue();
                int price = menuService.ItemPrice(Item);
                
                itemtotal = qty*price;
                subtotal = subtotal+ itemtotal;
                
                orderDetail.setColid(++id);
                orderDetail.setColname(Item);
                orderDetail.setColqty(qty);
                orderDetail.setColprice(price);
                orderDetail.setColItemcost(itemtotal);
            
                tableView.getItems().add(orderDetail);
                
                txtsubtotal.setText(Integer.toString(subtotal)); 
            
        } catch (RemoteException ex) {
                ex.printStackTrace();
        }
    }

    @FXML
    private void adddrinks(ActionEvent event) {
        
        try {
            
                orderDetail = new OrderDetail();
                String Item= combodrinks.getValue();
                int qty = drinksqty.getValue();
                int price = menuService.ItemPrice(Item);
                
                itemtotal = qty*price;
                subtotal = subtotal+ itemtotal;
                
                orderDetail.setColid(++id);
                orderDetail.setColname(Item);
                orderDetail.setColqty(qty);
                orderDetail.setColprice(price);
                orderDetail.setColItemcost(itemtotal);
            
                tableView.getItems().add(orderDetail);
                
                txtsubtotal.setText(Integer.toString(subtotal)); 
            
        } catch (RemoteException ex) {
                ex.printStackTrace();
        }
    }

    @FXML
    private void addshakes(ActionEvent event) {
        
        try {
            
                orderDetail = new OrderDetail();
                String Item= comboshakes.getValue();
                int qty = shakesqty.getValue();
                int price = menuService.ItemPrice(Item);
                
                itemtotal = qty*price;
                subtotal = subtotal+ itemtotal;
                
                orderDetail.setColid(++id);
                orderDetail.setColname(Item);
                orderDetail.setColqty(qty);
                orderDetail.setColprice(price);
                orderDetail.setColItemcost(itemtotal);
            
                tableView.getItems().add(orderDetail);
                
                txtsubtotal.setText(Integer.toString(subtotal)); 
            
        } catch (RemoteException ex) {
                ex.printStackTrace();
        }
    }

    @FXML
    private void onSend(ActionEvent event) {
    }

    @FXML
    private void onPrint(ActionEvent event) {
        
        if(isFieldValid())
        {
           try
            {
                LoginController logi = new LoginController();
                Order order = new Order();
                order.setCustomer_name(txtcustname.getText());
                order.setCustomer_no(Long.valueOf(txtcustphone.getText()));
                order.setStaff_id(logi.Staff_Id);

                order= orderService.insertOrder(order);

                //tableView.getItems().add(menu);

                clearFields();
            }
            catch(RemoteException ex)
            {
                ex.printStackTrace();
            }  
        }
        
        
    }

    @FXML
    private void onRemove(ActionEvent event) {
        
            OrderDetail orderDetail = tableView.getSelectionModel().getSelectedItem();
            if(orderDetail == null)
            {
                return;
            }
            
            //menuService.deleteMenu(menu.getItem_id());
            
            tableView.getItems().remove(orderDetail);
            id--;
            subtotal = subtotal - orderDetail.getColItemcost();
            
            txtsubtotal.setText(Integer.toString(subtotal));
        
    }

    @FXML
    private void onClear(ActionEvent event) {
    }
    
    public void addqtylist()
    {
        burgerqty.setValue(1);
        burgerqty.setItems(qtylist);
        
        pizzaqty.setValue(1);
        pizzaqty.setItems(qtylist);
        
        sandwichqty.setValue(1);
        sandwichqty.setItems(qtylist);
        
        noodlesqty.setValue(1);
        noodlesqty.setItems(qtylist);
        
        otherqty.setValue(1);
        otherqty.setItems(qtylist);
        
        drinksqty.setValue(1);
        drinksqty.setItems(qtylist);
        
        shakesqty.setValue(1);
        shakesqty.setItems(qtylist);
    }
    
    public void onStart()
    {
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry("localhost", 52360);
            menuService = (MenuService) registry.lookup("menuservice");
            orderService = (OrderService) registry.lookup("orderservice");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void ComboData() 
    {
        try {
            List<Menu> list = new ArrayList<Menu>();
            
            List<String> burgerslist = new ArrayList<>();
            List<String> pizzalist = new ArrayList<>();
            List<String> sandwichlist = new ArrayList<>();
            List<String> noodleslist = new ArrayList<>();
            List<String> otherlist = new ArrayList<>();
            List<String> shakeslist = new ArrayList<>();
            List<String> drinkslist = new ArrayList<>();
            
            list = menuService.getAllMenu();
            
            for(int i = 0; i<list.size();i++)
            {
                Menu element = list.get(i);
                if(element.getItem_type().equals("Burger"))
                {
                    burgerslist.add(element.getItem_name());
                    
                }
                
                if(element.getItem_type().equals("Pizza"))
                {
                    pizzalist.add(element.getItem_name());  
                }
                
                if(element.getItem_type().equals("Sandwich"))
                {
                    sandwichlist.add(element.getItem_name());
                    
                }
                
                if(element.getItem_type().equals("Noodles"))
                {
                    noodleslist.add(element.getItem_name());  
                }
                
                if(element.getItem_type().equals("Others"))
                {
                    otherlist.add(element.getItem_name());
                    
                }
                
                if(element.getItem_type().equals("Shakes"))
                {
                    shakeslist.add(element.getItem_name());  
                }
                
                if(element.getItem_type().equals("Drinks"))
                {
                    drinkslist.add(element.getItem_name());  
                }
            }
            
            comboburger.setItems(FXCollections.observableArrayList(burgerslist));
            combopizza.setItems(FXCollections.observableArrayList(pizzalist));
            combosandwich.setItems(FXCollections.observableArrayList(sandwichlist));
            combonoodles.setItems(FXCollections.observableArrayList(noodleslist));
            comboother.setItems(FXCollections.observableArrayList(otherlist));
            comboshakes.setItems(FXCollections.observableArrayList(shakeslist));
            combodrinks.setItems(FXCollections.observableArrayList(drinkslist));
            
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
       
    }
    
    
    private void clearFields() {
        
        txtcustname.setText("");
        txtcustphone.setText("");
        txtsubtotal.setText("");
        txttax.setText("");
        txtamt.setText("");
    }
    
    
    private boolean isFieldValid() {
        
        String errorMessage = "";
        
        if(txtcustname.getText()==null || txtcustname.getText().isEmpty())
        {
            errorMessage += "Name not entered\n";
        }
        
        if(txtcustphone.getText() == null || txtcustphone.getText().isEmpty())
        {
            errorMessage += "Phone number not mentioned\n";
            
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
