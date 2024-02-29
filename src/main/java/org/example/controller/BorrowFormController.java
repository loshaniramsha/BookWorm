package org.example.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class BorrowFormController {
    public AnchorPane child;
    public TextField textid;
    public ComboBox cmbusername;
    public ComboBox cmbbookId;
    public DatePicker date;
    public DatePicker duedate;
    public Button btnsearch;
    public Button btnsave;
    public Button btndelete;
    public Button btnupdate;
    public TableView tblBorrow;
    public TableColumn colid;
    public TableColumn colUsername;
    public TableColumn colbookid;
    public TableColumn colborrowDate;
    public TableColumn coldueDate;
    public TableColumn colUserid;
    public ComboBox cmbUserId;

    public void cmbOnAction(ActionEvent actionEvent) {
    }

    public void cmbBookOnAction(ActionEvent actionEvent) {
    }

    public void searchOnAction(ActionEvent actionEvent) {
    }

    public void saveOnAction(ActionEvent actionEvent) {
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }
}
