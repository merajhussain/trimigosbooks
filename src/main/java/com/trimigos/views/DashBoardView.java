package com.trimigos.views;

import com.trimigos.controllers.LoginController;
import com.trimigos.db.OrdersEntityManager;
import com.trimigos.models.DashBoardModel;
import com.trimigos.models.DataEntity;
import com.trimigos.models.LoginModel;
import com.trimigos.models.OrderEntity;
import com.trimigos.web.OrderWatsappWebhookManager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.trimigos.utils.ViewUtils;

public class DashBoardView {
    private Stage stage;

    ObservableList<OrderEntity> orders;
    private Timeline orderUpdateTimeLine;
    private static final int DOUBLE_CLICK_COUNT = 2;
    BorderPane root;
    Button ordersButton, homeButton, inventoryButton, logoutButton, reportsButton;
    private InventoryView inventoryView;

    public void setModel(DashBoardModel model) {
        this.model = model;
    }

    private DashBoardModel model;
    TableView<OrderEntity> ordersTableView;

    public DashBoardView(DashBoardModel dashboardModel) {

        stage = new Stage();
        stage.setTitle("Dashboard");

        this.model = dashboardModel;


        root = new BorderPane();
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("dashboard.css").toExternalForm());

        stage.setScene(scene);

        //left pane
        createOptionsPane();

        createRightPane();

        //center
        createDashboardView();
        homeButton.setOnAction(event -> {
            createDashboardView();
        });


        // Add action handlers for the Inventory buttons
        inventoryButton.setOnAction(event -> {
            createAndShowInventoryView();
        });


        logoutButton.setOnAction(this::logout);


    }

    private void createRightPane() {

        // Create a WebView to display the Google search page
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);

        webEngine.load("https://www.google.com");

        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.FAILED) {
                System.out.println("WebView failed to load: " + webEngine.getLoadWorker().getException());
            }
        });


        // Add the WebView to the right pane
        VBox rightPane = new VBox(10);
        rightPane.setPadding(new Insets(100));
        rightPane.getStyleClass().add("right-pane");
        rightPane.setMaxWidth(100);
        rightPane.getChildren().add(webView);

        // Set the right pane in the root BorderPane
        root.setRight(rightPane);
    }

    private void createOptionsPane() {

        VBox optionsPane = new VBox(10);
        optionsPane.setPadding(new Insets(10));
        optionsPane.getStyleClass().add("options-pane");

        // Add labels for each section
        Label optionsLabel = new Label("Options");
        optionsLabel.getStyleClass().add("section-label");
        homeButton = ViewUtils.createSyledButton("Dashboard", FontAwesomeIcon.DASHBOARD, Color.YELLOWGREEN, "2em", "option-button");
        ordersButton = ViewUtils.createSyledButton("Orders", FontAwesomeIcon.LIST, Color.YELLOWGREEN, "2em", "option-button");
        inventoryButton = ViewUtils.createSyledButton("Inventory", FontAwesomeIcon.HOME, Color.YELLOWGREEN, "2em", "option-button");
        reportsButton = ViewUtils.createSyledButton("Reports", FontAwesomeIcon.PRINT, Color.YELLOWGREEN, "2em", "option-button");
        logoutButton = ViewUtils.createSyledButton("Logout", FontAwesomeIcon.SIGN_OUT, Color.YELLOWGREEN, "2em", "option-button");


        optionsPane.getChildren().addAll(optionsLabel, homeButton, ordersButton, inventoryButton, reportsButton, logoutButton);
        root.setLeft(optionsPane);

    }

    private void handleOrderViewSelection() {
        ordersTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            System.out.println("Handling order Selection");
            if (newSelection != null) {
                orderUpdateTimeLine.stop(); // Stop the timeline when an order is selected

                // Perform some operation on the selected order here

                // Restart the timeline after the operation is performed
                //   orderUpdateTimeLine.play();
            }
        });
    }


    private TableView<DataEntity> createTableView() {
        TableView<DataEntity> tableView = new TableView<>();

        TableColumn<DataEntity, String> nameColumn = new TableColumn<>("SKU");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<DataEntity, Integer> valueColumn = new TableColumn<>("Quantity");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        tableView.getColumns().addAll(nameColumn, valueColumn);

        ObservableList<DataEntity> data = FXCollections.observableArrayList(
                new DataEntity("Entity 1", 10),
                new DataEntity("Entity 2", 20),
                new DataEntity("Entity 3", 30),
                new DataEntity("Entity 4", 40)
        );

        tableView.setItems(data);

        return tableView;
    }


    public TableView<OrderEntity> createOrderTableView() {
        this.ordersTableView = new TableView<>();

        TableColumn<OrderEntity, String> nameColumn = new TableColumn<>("CustomerName");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<OrderEntity, String> idColumn = new TableColumn<>("OrderId");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("orderID"));

        TableColumn<OrderEntity, String> locColumn = new TableColumn<>("Location");
        locColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<OrderEntity, String> itemsColumn = new TableColumn<>("Items");
        itemsColumn.setCellValueFactory(new PropertyValueFactory<>("items")); // Assuming 'items' property in OrderEntity

        //Adjust items string length based on items length
        itemsColumn.setCellFactory(column -> {
            return new TableCell<OrderEntity, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item);

                        // Adjust column width based on text length
                        double textWidth = getText().length() * 7; // Adjust multiplier as needed
                        if (textWidth > 100) { // Adjust minimum width as needed
                            setMinWidth(textWidth + 10); // Adjust padding as needed
                            setPrefWidth(textWidth + 10); // Adjust padding as needed
                        }
                    }
                }
            };
        });

        ordersTableView.getColumns().addAll(nameColumn, idColumn, locColumn, itemsColumn);

        orders = FXCollections.observableArrayList(
                model.getPendingOrder()
        );

        ordersTableView.setItems(orders);


        return ordersTableView;
    }

    private void handleOrderCompletion() {


        ordersTableView.setRowFactory(tv -> {

            TableRow<OrderEntity> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == DOUBLE_CLICK_COUNT && !row.isEmpty()) {
                    orderUpdateTimeLine.stop();
                    System.out.println("Handling order completion");
                    OrderEntity selectedOrder = row.getItem();
                    // Perform the delete operation here
                    OrdersEntityManager ordersEntityManager = new OrdersEntityManager();
                    ordersEntityManager.updateOrderCompleted(selectedOrder.getOrderID());
                    ;
                    ordersTableView.getItems().remove(selectedOrder);
                    orderUpdateTimeLine.play();

                }
            });
            return row;
        });


    }

    public void show() {
        stage.show();
    }

    public void updateDashBoardViewTables() {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Perform time-consuming task in background thread
                OrderWatsappWebhookManager orderPuller = new OrderWatsappWebhookManager();
                orderPuller.pullOrders();
                return null;
            }
        };

        // Set up the task completion handler to update the UI
        task.setOnSucceeded(event -> {
            // Update the UI on the JavaFX Application Thread
            Platform.runLater(() -> {
                ordersTableView.getItems().clear();
                ordersTableView.setItems(model.getPendingOrder());
            });
        });

        // Start the task in a background thread
        Thread thread = new Thread(task);
        thread.setDaemon(true); // Daemonize the thread to prevent it from blocking application shutdown
        thread.start();


    }


    private void logout(ActionEvent actionEvent) {
        orderUpdateTimeLine.stop(); // stop the order poller


        OrderWatsappWebhookManager orderPuller = new OrderWatsappWebhookManager();
        orderPuller.clearOrdersFromServer();
        stage.close();

        LoginModel loginModel = new LoginModel();
        LoginView loginView = new LoginView(new Stage());
        new LoginController(loginModel, loginView);

        loginView.show();
    }

    private InventoryView createAndShowInventoryView() {

        inventoryView = new InventoryView(this.root, this.stage);

        return inventoryView;
    }


    private void createDashboardView() {

        stage.setTitle("Dashboard");
        VBox tableViewContainer = new VBox(20);
        tableViewContainer.setPadding(new Insets(20)); // Set padding for the VBox
        tableViewContainer.getStyleClass().add("table-view-container");

        // Create a label and TableView in each VBox
        VBox tableView1Box = new VBox(10);
        Label tableView1Label = new Label("Low on Stock  ");
        tableView1Label.getStyleClass().add("section-label");
        tableView1Box.getChildren().addAll(tableView1Label, createTableView());

        VBox ordersViewBox = new VBox(10);
        Label tableView2Label = new Label("Orders to Deliver");
        tableView2Label.getStyleClass().add("section-label");
        ordersViewBox.getChildren().addAll(tableView2Label, createOrderTableView());
        ordersViewBox.setMaxWidth(1000);

        HBox row1 = new HBox(10);
        row1.getChildren().addAll(tableView1Box, ordersViewBox);


        VBox tableView3Box = new VBox(10);
        Label tableView3Label = new Label("Table View 3");
        tableView3Box.getStyleClass().add("section-label");
        tableView3Box.getChildren().addAll(tableView3Label, createTableView());

        VBox tableView4Box = new VBox(10);
        Label tableView4Label = new Label("Table View 4");
        tableView4Label.getStyleClass().add("section-label");
        tableView4Box.getChildren().addAll(tableView4Label, createTableView());

        HBox row2 = new HBox(10);
        row2.getChildren().addAll(tableView3Box, tableView4Box);


        tableViewContainer.getChildren().addAll(row1, row2);


        orderUpdateTimeLine = new Timeline(new KeyFrame(Duration.seconds(2), event -> updateDashBoardViewTables()));

        orderUpdateTimeLine.setCycleCount(Timeline.INDEFINITE);

        orderUpdateTimeLine.play();
        root.setCenter(tableViewContainer);


        handleOrderViewSelection();

        handleOrderCompletion();
    }


}
