/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.client.api;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author kastu
 */
public class BillSummary {

    private final ObjectProperty<LocalDate> order_date = new SimpleObjectProperty<>();
    private final IntegerProperty totalorder = new SimpleIntegerProperty();
    private final IntegerProperty totalamt = new SimpleIntegerProperty();

    public int getTotalamt() {
        return totalamt.get();
    }

    public void setTotalamt(int value) {
        totalamt.set(value);
    }

    public IntegerProperty totalamtProperty() {
        return totalamt;
    }
    

    public int getTotalorder() {
        return totalorder.get();
    }

    public void setTotalorder(int value) {
        totalorder.set(value);
    }

    public IntegerProperty totalorderProperty() {
        return totalorder;
    }
    
    
    
    public LocalDate getOrder_date() {
        return order_date.get();
    }

    public void setOrder_date(LocalDate value) {
        order_date.set(value);
    }

    public ObjectProperty order_dateProperty() {
        return order_date;
    }
    
    
    
}
