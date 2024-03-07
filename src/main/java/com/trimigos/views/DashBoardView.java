package com.trimigos.views;

import com.trimigos.controllers.LoginController;
import com.trimigos.models.DashBoardModel;
import com.trimigos.models.DataEntity;
import com.trimigos.models.LoginModel;
import com.trimigos.models.OrderEntity;
import com.trimigos.web.OrderPuller;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DashBoardView {
    private Stage stage;

    ObservableList<OrderEntity> orders;
    private Timeline orderUpdateTimeLine;




    public void setModel(DashBoardModel model) {
        this.model = model;
    }

    private DashBoardModel model;
    TableView<OrderEntity> ordersTableView;




    public DashBoardView( DashBoardModel dashboardModel) {

        stage = new Stage();
        stage.setTitle("Dashboard");

        this.model = dashboardModel;
        

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("dashboard.css").toExternalForm());
        stage.setScene(scene);

        VBox optionsPane = new VBox(10);
        optionsPane.setPadding(new Insets(10));
        optionsPane.getStyleClass().add("options-pane");

        // Add labels for each section
        Label optionsLabel = new Label("Options");
        optionsLabel.getStyleClass().add("section-label");

        Button option1Button = new Button("Orders");
        option1Button.getStyleClass().add("option-button");
        Button option2Button = new Button("Inventory");
        option2Button.getStyleClass().add("option-button");
        Button option3Button = new Button("Reports");
        option3Button.getStyleClass().add("option-button");
        Button logoutButton = new Button("Logout");
        logoutButton.getStyleClass().add("option-button");

        addStyleToButton(option1Button,FontAwesomeIcon.LIST,Color.DARKGRAY);
        addStyleToButton(option2Button,FontAwesomeIcon.HOME,Color.DARKGRAY);
        addStyleToButton(option3Button,FontAwesomeIcon.PRINT,Color.DARKGRAY);
        addStyleToButton(logoutButton,FontAwesomeIcon.SIGN_OUT,Color.DARKGRAY);



        optionsPane.getChildren().addAll(optionsLabel, option1Button, option2Button, option3Button, logoutButton);
        root.setLeft(optionsPane);

        VBox tableViewContainer = new VBox(20);
        tableViewContainer.setPadding(new Insets(40)); // Set padding for the VBox
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

       orderUpdateTimeLine = new Timeline(new KeyFrame(Duration.seconds(2), event -> updateDashBoardViewTables( )));

       orderUpdateTimeLine.setCycleCount(Timeline.INDEFINITE);

       orderUpdateTimeLine.play();
        root.setCenter(tableViewContainer);





        logoutButton.setOnAction(this::logout);


    }



    private TableView<DataEntity> createTableView( ) {
        TableView<DataEntity> tableView = new TableView<>();

        TableColumn<DataEntity, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<DataEntity, Integer> valueColumn = new TableColumn<>("Value");
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




    private void addStyleToButton(Button button, FontAwesomeIcon icon, Color fillfolor)
    {
        FontAwesomeIconView iconView = new FontAwesomeIconView(icon);
        iconView.setSize("2em"); // Set icon size
        iconView.setFill(fillfolor); // Set icon color
        button.setGraphic(iconView);

    }



    public TableView<OrderEntity> createOrderTableView()
    {
        this.ordersTableView = new TableView<>();

        TableColumn<OrderEntity, String> nameColumn = new TableColumn<>("CustomerName");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<OrderEntity, String> idColumn = new TableColumn<>("OrderId");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("orderID"));

        TableColumn<OrderEntity, String> locColumn = new TableColumn<>("Location");
        locColumn.setCellValueFactory(new PropertyValueFactory<>("location"));


        ordersTableView.getColumns().addAll(nameColumn, idColumn,locColumn);

        orders = FXCollections.observableArrayList(
                model.getAllOrders()
        );

        ordersTableView.setItems(orders);




        return ordersTableView;
    }

    public void show() {
        stage.show();
    }

     public void updateDashBoardViewTables( )
    {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Perform time-consuming task in background thread
                OrderPuller orderPuller = new OrderPuller();
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


    private void logout(ActionEvent actionEvent)
    {
        orderUpdateTimeLine.stop(); // stop the order poller


        OrderPuller orderPuller = new OrderPuller();
        orderPuller.clearOrdersFromServer();
        stage.close();

        LoginModel loginModel = new LoginModel();
        LoginView loginView = new LoginView(new Stage());
        new LoginController(loginModel, loginView);

        loginView.show();
    }
}
