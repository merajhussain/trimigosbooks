package com.trimigos.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderEntity {
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String customerName;
    private String orderID;
    private String location;
    private boolean pendingOrder;

    public void setItemsList(List<String> itemsList) {
        this.itemsList = itemsList;
    }

    private  List<String> itemsList;

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public List<String> getItemsList()
    {
        return itemsList;
    }

    private String items;


    public OrderEntity(String customerName, String orderID,String location, boolean pendingOrder,String items) {
        this.customerName = customerName;
        this.orderID = orderID;
        this.location = location;
        this.pendingOrder = pendingOrder;
        this.items = items;

        if(items == null)
        {
            items ="e;e;e;";
        }

      //  this.itemsList = Arrays.asList(this.items.split(";"));
    }

}
