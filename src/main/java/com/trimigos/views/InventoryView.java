package com.trimigos.views;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import utils.ViewUtils;

public class InventoryView {

    public  InventoryView(BorderPane root)
    {
        VBox addInventoryView = new VBox();
        Label addInventoryLabel = new Label("Inventory Details");
        addInventoryLabel.setAlignment(Pos.CENTER);

        // Create a GridPane for organizing the buttons
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20); // Horizontal gap between buttons
        gridPane.setVgap(10); // Vertical gap between buttons
        gridPane.setPadding(new Insets(10)); // Padding around the grid

        // Create buttons with Font Awesome icons
        Button button1 = ViewUtils.createSyledButton("Add Inventory", FontAwesomeIcon.SHOPPING_CART, Color.DARKGRAY,"20em","");
        Button button2 = ViewUtils.createSyledButton("View Inventory",FontAwesomeIcon.CART_ARROW_DOWN,Color.DARKGRAY,"20em","");

        // Add buttons to the grid
        gridPane.add(button1, 4, 0);
        gridPane.add(button2, 10, 0);


        // Add UI components for adding inventory...
        addInventoryView.getChildren().addAll(addInventoryLabel, gridPane);
        root.setCenter(addInventoryView);

    }
}
