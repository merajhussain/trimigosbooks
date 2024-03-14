package com.trimigos.views;

import com.trimigos.models.SkuModel;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class AddSkuView {

    public AddSkuView() {

        // Create a dialog box for adding a new SKU
        Dialog<String> dialogAddSku = new Dialog<>();
        dialogAddSku.setTitle("Add SKU");


        // Set the button types (OK and Cancel)
        ButtonType addButton = new ButtonType("Add SKU", ButtonBar.ButtonData.OK_DONE);

        dialogAddSku.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);


        // Create a text input field for entering the SKU
        TextField skuField = new TextField();

        // Add form fields to dialog box
        GridPane grid = new GridPane();
        Label addSkuLabel = new Label("SKU name");
        addSkuLabel.getStyleClass().add("sku-label");
        grid.add(addSkuLabel, 0, 0);
        grid.add(skuField, 1, 0);

        // Set the style for the dialog pane
        dialogAddSku.getDialogPane().setContent(grid);
        dialogAddSku.getDialogPane().getStylesheets().add(getClass().getClassLoader().getResource("skudialog.css").toExternalForm());
        dialogAddSku.getDialogPane().getStyleClass().add("skuview-dialog-pane"); // Add a custom style class

        // Request focus on the SKU field by default
        Platform.runLater(skuField::requestFocus);

        // Convert the result to a SKU string when the Add button is clicked
        dialogAddSku.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                saveSku(skuField.getText());
            }
            return null;
        });

        // Show the dialog and wait for user input
        Optional<String> result = dialogAddSku.showAndWait();

        // Process the result (e.g., add the SKU to the system)
        result.ifPresent(sku -> {
            // Add the SKU to the system or perform other actions as needed
            saveSku(skuField.getText());
        });
    }

    public void saveSku(String sku)
    {

        SkuModel skuModel = new SkuModel(sku);
        skuModel.saveSku();



    }
}
