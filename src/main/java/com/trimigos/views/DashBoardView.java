package com.trimigos.views;

import com.trimigos.models.DataEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashBoardView {
    private Stage stage;

    public DashBoardView() {
        stage = new Stage();
        stage.setTitle("Dashboard");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("dashboard.css").toExternalForm());
        stage.setScene(scene);

        VBox optionsPane = new VBox(10);
        optionsPane.setPadding(new Insets(10));
        optionsPane.getStyleClass().add("options-pane");

        Button option1Button = new Button("Option 1");
        option1Button.getStyleClass().add("option-button");
        Button option2Button = new Button("Option 2");
        option2Button.getStyleClass().add("option-button");
        Button option3Button = new Button("Option 3");
        option3Button.getStyleClass().add("option-button");
        Button option4Button = new Button("Option 4");
        option4Button.getStyleClass().add("option-button");

        optionsPane.getChildren().addAll(option1Button, option2Button, option3Button, option4Button);
        root.setLeft(optionsPane);

        VBox tableViewContainer = new VBox(10);
        tableViewContainer.getStyleClass().add("table-view-container");

        HBox row1 = new HBox(10);
        row1.getChildren().addAll(createTableView(), createTableView());

        HBox row2 = new HBox(10);
        row2.getChildren().addAll(createTableView(), createTableView());

        tableViewContainer.getChildren().addAll(row1, row2);

        root.setCenter(tableViewContainer);
    }

    private TableView<DataEntity> createTableView() {
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

    public void show() {
        stage.show();
    }
}
