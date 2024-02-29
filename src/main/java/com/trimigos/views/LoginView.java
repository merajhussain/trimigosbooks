package com.trimigos.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import javafx.scene.layout.AnchorPane;

public class LoginView {
    private Stage stage;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;

    public LoginView(Stage stage) {
        this.stage = stage;
        stage.setTitle("Login");

        AnchorPane root = new AnchorPane();
        root.getStyleClass().add("login-pane");
        Scene scene = new Scene(root, 400, 200);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("login.css").toExternalForm());
        stage.setScene(scene);

        // VBox on the left side
        VBox leftBox = new VBox(10);
        leftBox.getStyleClass().add("left-box");
        leftBox.setAlignment(Pos.TOP_LEFT); // Align to top left

        // Text shape in the middle of the left VBox
        Text text = new Text("Welcome");
        text.setFill(Color.WHITE);

        leftBox.getChildren().addAll(  text); // Add transparent rectangle to create space

        AnchorPane.setTopAnchor(leftBox, 50.0);
        AnchorPane.setLeftAnchor(leftBox, 50.0);

        // Group containing username, password fields, and login button
        VBox loginGroup = new VBox(10);
        loginGroup.setPadding(new Insets(10));
        loginGroup.setAlignment(Pos.CENTER_LEFT);

        usernameField = new TextField();
        usernameField.getStyleClass().add("text-field");

        passwordField = new PasswordField();
        passwordField.getStyleClass().add("password-field");

        loginButton = new Button("Login");
        loginButton.getStyleClass().add("login-button");

        loginGroup.getChildren().addAll(usernameField, passwordField, loginButton);

        AnchorPane.setTopAnchor(loginGroup, 50.0);
        AnchorPane.setLeftAnchor(loginGroup, 220.0); // Adjust left anchor to position on the right side of the VBox

        VBox.setVgrow(leftBox, Priority.ALWAYS);

        // Add leftBox and loginGroup to the root AnchorPane
        root.getChildren().addAll(leftBox, loginGroup);
    }


    public void show() {
        stage.show();
    }
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public void setOnLoginButtonClicked(Runnable handler) {
        loginButton.setOnAction(e -> handler.run());
    }

    public void close() {
        stage.close();
    }
}
