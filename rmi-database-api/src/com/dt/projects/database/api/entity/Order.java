/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dt.projects.database.api.entity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kastu
 */
public class Order implements Externalizable{

    private final IntegerProperty order_id = new SimpleIntegerProperty();
    private final StringProperty customer_name = new SimpleStringProperty();
    private final LongProperty customer_no = new SimpleLongProperty();
    private final StringProperty orders = new SimpleStringProperty();
    private final IntegerProperty bill = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> order_date = new SimpleObjectProperty<>();
    private final StringProperty staff_id = new SimpleStringProperty();
    private final StringProperty order_status = new SimpleStringProperty();

    public String getOrder_status() {
        return order_status.get();
    }

    public void setOrder_status(String value) {
        order_status.set(value);
    }

    public StringProperty order_statusProperty() {
        return order_status;
    }
    

    public String getStaff_id() {
        return staff_id.get();
    }

    public void setStaff_id(String value) {
        staff_id.set(value);
    }

    public StringProperty staff_idProperty() {
        return staff_id;
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
    

    public int getBill() {
        return bill.get();
    }

    public void setBill(int value) {
        bill.set(value);
    }

    public IntegerProperty billProperty() {
        return bill;
    }

    public String getOrders() {
        return orders.get();
    }

    public void setOrders(String value) {
        orders.set(value);
    }

    public StringProperty ordersProperty() {
        return orders;
    }
    

    public long getCustomer_no() {
        return customer_no.get();
    }

    public void setCustomer_no(long value) {
        customer_no.set(value);
    }

    public LongProperty customer_noProperty() {
        return customer_no;
    }
    

    public String getCustomer_name() {
        return customer_name.get();
    }

    public void setCustomer_name(String value) {
        customer_name.set(value);
    }

    public StringProperty customer_nameProperty() {
        return customer_name;
    }
    

    public int getOrder_id() {
        return order_id.get();
    }

    public void setOrder_id(int value) {
        order_id.set(value);
    }

    public IntegerProperty order_idProperty() {
        return order_id;
    }
    
    

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(getOrder_id());
        out.writeObject(getCustomer_name());
        out.writeLong(getCustomer_no());
        out.writeObject(getOrders());
        out.writeInt(getBill());
        out.writeObject(getOrder_date());
        out.writeObject(getStaff_id());
        out.writeObject(getOrder_status()); 
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setOrder_id(in.readInt());
        setCustomer_name((String)in.readObject());
        setCustomer_no(in.readLong());
        setOrders((String)in.readObject());
        setBill(in.readInt());
        setOrder_date((LocalDate)in.readObject());
        setStaff_id((String)in.readObject());
        setOrder_status((String)in.readObject());        
    }
    
    
    
    
}
