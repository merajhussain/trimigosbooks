package com.trimigos.views;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;



public class LoginView {
    private Stage stage;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;

    public LoginView(Stage stage) {
        this.stage = stage;
        stage.setTitle("Login");
        this.stage = stage;
        stage.setTitle("Login");
        // Create a SplitPane
        SplitPane root = new SplitPane();
        root.setDividerPositions(0.15); // Set the divider position to 15% (left pane)

        // Left side pane to hold label and logo
        Pane leftPane = new Pane();
        leftPane.setId("left-box"); // Set ID for left pane

        //Add logo and text shape to the left pane
        AddTextShapeToPane(leftPane);

        // Right side with login form
        VBox rightBox = new VBox(20);
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setPadding(new Insets(20));

        usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setId("text-field");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setId("password-field ");

        loginButton = new Button("Login");
        loginButton.setId("login-button");

        rightBox.getChildren().addAll(usernameField, passwordField, loginButton);

        // Add panes to the SplitPane
        root.getItems().addAll(leftPane, rightBox);

        // Load the CSS file
        Scene scene = new Scene(root, 400, 200);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("login.css").toExternalForm());

        stage.setScene(scene);

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

        // Add an event filter for the Enter key press event
        loginButton.getParent().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handler.run();
                event.consume(); // Consume the event to prevent further processing
            }
        });
    }

    public void close() {
        stage.close();
    }

    private void AddTextShapeToPane(Pane leftPane) {

        Text text = new Text("Trimigos Books");
        text.setId("welcome-text"); // Set ID for text
        text.setFont(Font.font(18));

        // Listen for changes in the size of the left pane and update the position of the text accordingly
        leftPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double textWidth = text.getLayoutBounds().getWidth();
            text.setLayoutX((newWidth.doubleValue() - textWidth) / 2);
        });

        leftPane.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            double textHeight = text.getLayoutBounds().getHeight();
            text.setLayoutY((newHeight.doubleValue() + textHeight) / 2);
        });


        FontAwesomeIconView bookIcon = new FontAwesomeIconView(FontAwesomeIcon.BOOK);
        bookIcon.setFill(Color.DARKGRAY);
        bookIcon.setSize("2em");
        bookIcon.setLayoutX((leftPane.getWidth() - bookIcon.getBoundsInLocal().getWidth()) / 2);
        bookIcon.setLayoutY(text.getBoundsInLocal().getHeight()); // Position the icon just below the text

        text.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                double centerX = newValue.getMinX() + newValue.getWidth() / 2;
                double iconX = centerX - bookIcon.getBoundsInParent().getWidth() / 2;
                double iconY = newValue.getMinY() - bookIcon.getBoundsInParent().getHeight() - 5; // 5 pixels gap
                bookIcon.setLayoutX(iconX);
                bookIcon.setLayoutY(iconY);
            }
        });

        // Add the text shape to the left pane
        leftPane.getChildren().addAll(bookIcon,text);


    }

}
