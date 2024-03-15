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


        TextField skuRateField = new TextField();
        Label skuRate = new Label("SKU Rate");

        skuRate.getStyleClass().add("sku-label");
        grid.add(skuRate,0,1);
        grid.add(skuRateField,1,1);


        TextField skuSalePriceField = new TextField();
        Label skuSalePrice = new Label("Sale Price");



        skuSalePrice.getStyleClass().add("sku-label");
        grid.add(skuSalePrice,0,2);
        grid.add(skuSalePriceField,1,2);

        TextField skuThresholdQuantityField = new TextField();
        Label skuThresholdQuantity = new Label("ThresholdQuantity");

        skuThresholdQuantity.getStyleClass().add("sku-label");
        grid.add(skuThresholdQuantity,0,3);
        grid.add(skuThresholdQuantityField,1,3);




        // Set the style for the dialog pane
        dialogAddSku.getDialogPane().setContent(grid);
        dialogAddSku.getDialogPane().getStylesheets().add(getClass().getClassLoader().getResource("skudialog.css").toExternalForm());
        dialogAddSku.getDialogPane().getStyleClass().add("skuview-dialog-pane"); // Add a custom style class

        // Request focus on the SKU field by default
        Platform.runLater(skuField::requestFocus);

        // Convert the result to a SKU string when the Add button is clicked
        dialogAddSku.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                saveSku(skuField.getText(),Double.parseDouble(skuRateField.getText()),Double.parseDouble(skuSalePriceField.getText()), (int) 0, Integer.parseInt(skuThresholdQuantityField.getText()));
            }
            return null;
        });

        // Show the dialog and wait for user input
        Optional<String> result = dialogAddSku.showAndWait();

        // Process the result (e.g., add the SKU to the system)
        result.ifPresent(sku -> {
            // Add the SKU to the system or perform other actions as needed
            saveSku(skuField.getText(),Double.parseDouble(skuRateField.getText()),Double.parseDouble(skuSalePriceField.getText()), (int) 0, Integer.parseInt(skuThresholdQuantityField.getText()));
        });
    }

    public void saveSku(String sku,double rate,double salePrice,int quantity,int threasholdStock)
    {

        SkuModel skuModel = new SkuModel(sku,rate,salePrice,threasholdStock);
        skuModel.saveSku();



    }
}
