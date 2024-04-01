package com.trimigos.views;
import com.trimigos.db.SkuEntityManager;
import com.trimigos.entity.SkuEntity;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InventoryViewForm {

    private Stage stage;
    private TableView<SkuEntity> tableView;

    public InventoryViewForm() {
        stage = new Stage();
        stage.setTitle("Inventory View");




        // Create table view
        tableView = new TableView<>();
        TableColumn<String, String> itemColumn = new TableColumn<>("Item");
        TableColumn<String, String> quantityColumn = new TableColumn<>("Quantity");
        // Add more columns as needed

       prepareInventoryView();
        // Create layout


        VBox root = new VBox(tableView);
        root.getStyleClass().add("vbox-form"); // Add style class to set background color
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("inventoryform.css").toExternalForm());

        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }

    private void prepareInventoryView()
    {



        SkuEntityManager skuEntityManager = new SkuEntityManager();


       tableView = new TableView<>();
        TableColumn<SkuEntity, String> idColumn = new TableColumn<>("ID");
        TableColumn<SkuEntity, String> skuNameColumn = new TableColumn<>("SKU Name");
        TableColumn<SkuEntity, Double> rateColumn = new TableColumn<>("Rate");
        TableColumn<SkuEntity, Double> salePriceColumn = new TableColumn<>("Sale Price");
        TableColumn<SkuEntity, Integer> quantityColumn = new TableColumn<>("Quantity");
        TableColumn<SkuEntity, Integer> thresholdStockColumn = new TableColumn<>("Threshold Stock");

        // Set cell value factories
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        skuNameColumn.setCellValueFactory(new PropertyValueFactory<>("skuName"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        salePriceColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        thresholdStockColumn.setCellValueFactory(new PropertyValueFactory<>("thresholdstock"));

        // Add columns to TableView
        tableView.getColumns().addAll(idColumn, skuNameColumn, rateColumn, salePriceColumn, quantityColumn, thresholdStockColumn);

        // Add sample data to TableView
        tableView.getItems().addAll(  skuEntityManager.fetchAllSkus());
    }
}
