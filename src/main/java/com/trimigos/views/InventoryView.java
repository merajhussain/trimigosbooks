package com.trimigos.views;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import com.trimigos.utils.ViewUtils;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.PropertySheet;

public class InventoryView {

    public InventoryView(BorderPane root, Stage primaryStage) {

        createInventoryView(root, primaryStage);

    }

    private void createInventoryView(BorderPane root, Stage primaryStage) {
        primaryStage.setTitle("Inventory Details");
        VBox addInventoryView = new VBox();


        addInventoryView.getStylesheets().add(getClass().getClassLoader().getResource("inventory.css").toExternalForm());

        // Create a GridPane for organizing the buttons
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20); // Horizontal gap between buttons
        gridPane.setVgap(10); // Vertical gap between buttons
        gridPane.setPadding(new Insets(10)); // Padding around the grid

        // Create buttons with Font Awesome icons
        Button button1 = ViewUtils.createSyledButton("Add Purchase bill", FontAwesomeIcon.CART_ARROW_DOWN, Color.DARKGRAY, "25em", "inventory-button");
        Button button2 = ViewUtils.createSyledButton("View Inventory", FontAwesomeIcon.SHOPPING_CART, Color.DARKGRAY, "25em", "inventory-button");
        Button button3 = ViewUtils.createSyledButton("Add SKU", FontAwesomeIcon.TAGS, Color.DARKGRAY, "25em", "inventory-button");

        // Add buttons to the grid
        gridPane.add(button1, 4, 0);
        gridPane.add(button2, 10, 0);
        gridPane.add(button3, 4, 2);

        button1.setOnAction(e->addInventoryForm());


        // Add UI components for adding inventory...
        addInventoryView.getChildren().addAll(gridPane);
        root.setCenter(addInventoryView);

    }

    private void addInventoryForm() {

        PurchaseBillFormView purchaseBillFormView = new PurchaseBillFormView();

        purchaseBillFormView.showForm();

    }




}
