package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginFormController {
    public AnchorPane root;
    public Label lblLogin;
    public TextField textName;
    public TextField textPassword;
    public Button btnsing;
    public Button btnLogin;
    public ComboBox cmb;

    public void lblforgotPassword(MouseEvent mouseEvent) {
    }

    public void signinOnAction(ActionEvent actionEvent) {
    }

    public void loginOnAction(ActionEvent actionEvent)throws Exception {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/Dashboard_Form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);
        Stage.getWindows();
        stage.centerOnScreen();

    }

    public void cbmOnAction(ActionEvent actionEvent) {
    }
}
