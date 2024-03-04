package com.trimigos.controllers;

import com.trimigos.db.OrdersEntityManager;
import com.trimigos.models.DashBoardModel;
import com.trimigos.models.OrderEntity;
import com.trimigos.views.DashBoardView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.util.Duration;

public class DashBoardController {
    private DashBoardModel model;
    private DashBoardView view;




    public DashBoardController(DashBoardModel model, DashBoardView view) {
        this.model = model;
        this.view = view;

        System.out.println("Dashboard controller created");

        view.setModel(model);



    }

}
