package com.trimigos.views;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import java.util.ArrayList;
import java.util.List;

public class ListViewTableCell<S, T> extends TableCell<S, T> {

    private final ListView<T> listView;

    public ListViewTableCell() {
        this.listView = new ListView<>();
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        } else {
            List<T> itemList = new ArrayList<>(); // Create a mutable list
            itemList.addAll((List<T>) item); // Assuming item is a list of items
            listView.getItems().setAll(itemList);
            setGraphic(listView);
        }
    }

    public static <S, T> Callback<TableColumn<S, T>, TableCell<S, T>> forListView() {
        return param -> new ListViewTableCell<>();
    }
}
