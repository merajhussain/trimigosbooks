package com.trimigos.controllers;

import com.trimigos.models.DashBoardModel;
import com.trimigos.models.LoginModel;
import com.trimigos.views.DashBoardView;
import com.trimigos.views.LoginView;

public class LoginController {
    private LoginModel model;
    private LoginView view;

    public LoginController(LoginModel model, LoginView view) {
        this.model = model;
        this.view = view;

        view.setOnLoginButtonClicked(this::login);
    }

    private void login() {
        String username = view.getUsername();
        String password = view.getPassword();
        boolean loggedIn = model.login(username, password);
        if (loggedIn) {
            view.close();
            // Show Dashboard
            DashBoardModel dashboardModel = new DashBoardModel();
            DashBoardView dashboardView = new DashBoardView(dashboardModel);
            new DashBoardController(dashboardModel, dashboardView);
             dashboardView.show();
        } else {
            // Display error message or handle invalid login
        }
    }
}
