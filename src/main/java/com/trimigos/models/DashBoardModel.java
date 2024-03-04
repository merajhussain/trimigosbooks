
package com.trimigos.models;

import com.trimigos.db.OrdersEntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DashBoardModel {


    private OrdersEntityManager ordersEntityManager;
    ObservableList<OrderEntity> data ;

    public DashBoardModel()
    {

        this.ordersEntityManager = new OrdersEntityManager();

        data =  FXCollections.observableArrayList();

    }


    public ObservableList<OrderEntity> getPendingOrder()
    {

        ordersEntityManager.getAllPendingOrders();

        var orders = ordersEntityManager.getOrders();

        this.data.addAll(orders);

        return data;

    }


    public ObservableList<OrderEntity> getAllOrders()
    {

        ordersEntityManager.getAllOrders();

        var orders = ordersEntityManager.getOrders();

        this.data.addAll(orders);

        return data;

    }


}
