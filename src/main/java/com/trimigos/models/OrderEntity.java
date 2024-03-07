package com.trimigos.models;

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


    public OrderEntity(String customerName, String orderID,String location, boolean pendingOrder) {
        this.customerName = customerName;
        this.orderID = orderID;
        this.location = location;
        this.pendingOrder = pendingOrder;
    }

}
