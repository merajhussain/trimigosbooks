package com.trimigos.views;

import com.trimigos.utils.ViewUtils;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class PurchaseBillFormView {

    public static class Item {
        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        String sku;
        int quantity;
        double rate;
        double discount;

        public double getTaxableValue() {
            return taxableValue;
        }

        public void setTaxableValue(double taxableValue) {
            this.taxableValue = taxableValue;
        }

        public double getIgstr() {
            return igstr;
        }

        public void setIgstr(double igstr) {
            this.igstr = igstr;
        }

        public double getIgstv() {
            return igstv;
        }

        public void setIgstv(double igstv) {
            this.igstv = igstv;
        }

        public double getFinalPrice() {
            return finalPrice;
        }

        public void setFinalPrice(double finalPrice) {
            this.finalPrice = finalPrice;
        }

        double taxableValue;
        double igstr;
        double igstv;
        double finalPrice;

        public Item(String sku, int quantity, double rate, double discount, double taxableValue, double igstr, double igstv, double finalPrice) {
            this.sku = sku;
            this.quantity = quantity;
            this.rate = rate;
            this.discount = discount;
            this.taxableValue = taxableValue;
            this.igstr = igstr;
            this.igstv = igstv;
            this.finalPrice = finalPrice;
        }


    }

    private Stage formStage;
    private TableView<Item> itemTable;
    private ObservableList<Item> itemList;


    private String labelText;
    private Label totalLabel;

    private double totalPrice;



    public PurchaseBillFormView() {
        // Create a new stage for the form
        formStage = new Stage();
        formStage.initModality(Modality.APPLICATION_MODAL); // Prevent interactions with other windows while this form is open
        formStage.setTitle("Inventory/Purchase bill Form");


       totalPrice =0.0;

        // Add a label to display the total price
        totalLabel = new Label();

        totalLabel.getStyleClass().add("total-label");



        // Create layout for the label
        HBox totalBox = new HBox(totalLabel);
        totalBox.setAlignment(Pos.BASELINE_RIGHT);
        totalBox.setPadding(new Insets(10));
        totalBox.getStyleClass().add("total-box");


        // Create the main layout
        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("background");

        // Create the form fields
        VBox formFields = new VBox();
        formFields.setSpacing(10);

        Label vehicleNoLabel = createLabel("Vehicle No:");
        TextField vehicleNoField = new TextField();

        Label carrierNameLabel = createLabel("Carrier Name:");
        TextField carrierNameField = new TextField();

        Label modeOfTransportLabel = createLabel("Mode of Transport:");
        TextField modeOfTransportField = new TextField();

        Label grNoLabel = createLabel("GR No:");
        TextField grNoField = new TextField();

        Label billDateLabel = createLabel("Bill Date:");
        DatePicker billDatePicker = new DatePicker();
        billDatePicker.getStyleClass().add("date-picker");


        formFields.getChildren().addAll(
                vehicleNoLabel, vehicleNoField,
                carrierNameLabel, carrierNameField,
                modeOfTransportLabel, modeOfTransportField,
                grNoLabel, grNoField,
                billDateLabel, billDatePicker
        );

        // Initialize the ObservableList
        itemList = FXCollections.observableArrayList();

        // Create TableView and define columns
        itemTable = new TableView<>();


        itemTable.getStyleClass().add("table-view");
        itemTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Item, String> skuColumn = new TableColumn<>("SKU");
        TableColumn<Item, Integer> quantityColumn = new TableColumn<>("Quantity");
        TableColumn<Item, Double> rateColumn = new TableColumn<>("Rate");
        TableColumn<Item, Double> discountColumn = new TableColumn<>("Discount");


        TableColumn<Item, Double> taxableValueColumn = new TableColumn<>("Taxable Value");
        TableColumn<Item, Double> igstRateColumn = new TableColumn<>("IGST Rate");
        TableColumn<Item, Double> igstAmountColumn = new TableColumn<>("IGST Amount");
        TableColumn<Item, Double> finalPriceColumn = new TableColumn<>("Final Price");

        //style the column headers
        skuColumn.getStyleClass().add("column-header-background");
        quantityColumn.getStyleClass().add("column-header-background");
        rateColumn.getStyleClass().add("column-header-background");
        discountColumn.getStyleClass().add("column-header-background");
        taxableValueColumn.getStyleClass().add("column-header-background");
        igstRateColumn.getStyleClass().add("column-header-background");
        igstAmountColumn.getStyleClass().add("column-header-background");
        finalPriceColumn.getStyleClass().add("column-header-background");


        skuColumn.setCellValueFactory(new PropertyValueFactory<>("sku"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
        taxableValueColumn.setCellValueFactory(new PropertyValueFactory<>("taxableValue"));
        igstRateColumn.setCellValueFactory(new PropertyValueFactory<>("igstr"));
        igstAmountColumn.setCellValueFactory(new PropertyValueFactory<>("igstv"));
        finalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));


        itemTable.getColumns().addAll(
                skuColumn, quantityColumn, rateColumn, discountColumn,
                taxableValueColumn, igstRateColumn, igstAmountColumn, finalPriceColumn
        );


        itemTable.setItems(itemList);

        Button addButton = ViewUtils.createSyledButton("ADD", FontAwesomeIcon.PLUS, Color.DARKGRAY, "2em", "add-button");
        addButton.setOnAction(e -> addItem(itemTable));

        HBox tableControls = new HBox(addButton);
        tableControls.setAlignment(Pos.CENTER);
        tableControls.setPadding(new Insets(10));

        Button submitButton = ViewUtils.createSyledButton("SUBMIT", FontAwesomeIcon.CHECK, Color.DARKGRAY, "2em", "submit-button");
        submitButton.setOnAction(e -> submitForm(itemTable));

        HBox buttonBox = new HBox(addButton, submitButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10));


        VBox tableContainer = new VBox(itemTable, tableControls, buttonBox);
        VBox.setVgrow(tableContainer, Priority.ALWAYS);

        // Add form fields and table to the main layout
        root.getChildren().addAll(formFields, tableContainer, totalBox);

        // Set the scene
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("inventory.css").toExternalForm());
        formStage.setScene(scene);

    }

    private void submitForm(TableView<Item> itemTable) {
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("label");
        return label;
    }

    private void addItem(TableView<Item> table) {


        // Create a dialog box for entering item details
        Dialog<Item> dialog = new Dialog<>();
        dialog.setTitle("Add Item");

        // Set the button types (OK and Cancel)
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        // Create a ComboBox for SKU selection
        ComboBox<String> skuComboBox = new ComboBox<>();
        skuComboBox.getItems().addAll("SKU1", "SKU2", "SKU3"); // Add your SKUs here
        skuComboBox.setEditable(true); // Allow manual input in addition to the predefined options


        // Create form fields
        TextField skuField = new TextField();
        TextField quantityField = new TextField();
        TextField rateField = new TextField();
        TextField discountField = new TextField();
        TextField igstField = new TextField();

        // Add form fields to dialog box
        GridPane grid = new GridPane();
        // Add the ComboBox to the grid
        grid.add(new Label("SKU:"), 0, 0);
        grid.add(skuComboBox, 1, 0);
        grid.add(new Label("Quantity:"), 0, 1);
        grid.add(quantityField, 1, 1);
        grid.add(new Label("Rate:"), 0, 2);
        grid.add(rateField, 1, 2);
        grid.add(new Label("Discount:"), 0, 3);
        grid.add(discountField, 1, 3);
        grid.add(new Label("igst:"), 0, 4);
        grid.add(igstField, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // Request focus on the SKU field by default
        Platform.runLater(skuField::requestFocus);

        // Convert the result to an Item object when the Add button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                try {
                    // Parse user input and create an Item object
                    String sku = skuComboBox.getValue();
                    int quantity = Integer.parseInt(quantityField.getText());
                    double rate = Double.parseDouble(rateField.getText());
                    double discount = Double.parseDouble(discountField.getText());
                    double igstr = Double.parseDouble(igstField.getText());

                    double taxableValue = quantity * rate + discount;
                    double igstv = (double) (taxableValue * igstr) / (double) 100;
                    double finalPrice = taxableValue - igstv;


                    Platform.runLater(() -> {
                        totalPrice += finalPrice;
                        totalLabel.setText(String.format("Total: Rs%.2f/-", totalPrice));
                    });
                    // Calculate taxable value, IGST amount, and final price here if needed
                    itemList.add(new Item(sku, quantity, rate, discount, taxableValue, igstr, igstv, finalPrice));
                    labelText ="Total: Rs"+totalPrice+"/-";



                } catch (NumberFormatException e) {
                    // Show an error message if input validation fails
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Input Error");
                    alert.setHeaderText("Invalid Input");
                    alert.setContentText("Please enter valid numeric values.");
                    alert.showAndWait();
                    return null;
                }
            }
            return null;
        });

        // Show the dialog and wait for user input
        Optional<Item> result = dialog.showAndWait();

        // Add the new item to the table if the user clicked Add
        result.ifPresent(table.getItems()::add);

        dialog.close();

    }


    public void showForm() {
        this.formStage.show();
    }


}
