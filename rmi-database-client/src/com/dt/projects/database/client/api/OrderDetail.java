/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.client.api;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kastu
 */
public class OrderDetail {

    private final IntegerProperty colid = new SimpleIntegerProperty();
    private final StringProperty colname = new SimpleStringProperty();
    private final IntegerProperty colqty = new SimpleIntegerProperty();
    private final IntegerProperty colprice = new SimpleIntegerProperty();
    private final IntegerProperty colItemcost = new SimpleIntegerProperty();

    public int getColItemcost() {
        return colItemcost.get();
    }

    public void setColItemcost(int value) {
        colItemcost.set(value);
    }

    public IntegerProperty colItemcostProperty() {
        return colItemcost;
    }
    

    public int getColprice() {
        return colprice.get();
    }

    public void setColprice(int value) {
        colprice.set(value);
    }

    public IntegerProperty colpriceProperty() {
        return colprice;
    }
    

    public int getColqty() {
        return colqty.get();
    }

    public void setColqty(int value) {
        colqty.set(value);
    }

    public IntegerProperty colqtyProperty() {
        return colqty;
    }
    

    public String getColname() {
        return colname.get();
    }

    public void setColname(String value) {
        colname.set(value);
    }

    public StringProperty colnameProperty() {
        return colname;
    }
    

    public int getColid() {
        return colid.get();
    }

    public void setColid(int value) {
        colid.set(value);
    }

    public IntegerProperty colidProperty() {
        return colid;
    }
    
    
    
}
