package org.example.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminUnlouckFormController {
    public AnchorPane root;
    public TextField textUsen;
    public TextField textPass;
    public Button btnUnlouck;
    private final static String userName1 = "admin";
    private final static String password1 = "1234";
    private final static String userName2 = "loshani";
    private final static String password2 = "loshani";

    private DashboardController dashboardController;

    public void unlouckOnAction(ActionEvent actionEvent) {
        String username = textUsen.getText();
        String password = textPass.getText();
        System.out.println(username+password);
        if (username == null || password == null){
            // alert ekak dann user password empty kyl
            return;
        }
        if (
                (username.equals(userName1) && password.equals(password1)) ||
                        (username.equals(userName2) && password.equals(password2))
        ){
            DashboardController.isAdminUnlock = true;
            dashboardController.goUserForm();
            ((Stage)textPass.getScene().getWindow()).close();
        }else {
            new Alert(Alert.AlertType.WARNING,"Wrong user name or password").show();
        }
    }

    public void init(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
}
