package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserRegisterController {
    public TextField textPassword;
    public TextField textName;
    public TextField textEmail;
    public TextField textId;
    public Button btnCreate;
    public AnchorPane anch;

    public void createOnAction(ActionEvent actionEvent) {
    }

    public void backOnAction(ActionEvent actionEvent) throws Exception {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/Login_Form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)anch.getScene().getWindow();
        stage.setScene(scene);
        Stage.getWindows();
        stage.centerOnScreen();
    }
}
