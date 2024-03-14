package com.trimigos.controllers;

import com.trimigos.models.DashBoardModel;
import com.trimigos.views.DashBoardView;

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
