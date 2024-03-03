package com.trimigos;

import com.trimigos.models.LoginModel;
import com.trimigos.views.LoginView;
import com.trimigos.controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

public class trimigosbooks extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginModel loginModel = new LoginModel();
        LoginView loginView = new LoginView(primaryStage);
        new LoginController(loginModel, loginView);
        loginView.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
