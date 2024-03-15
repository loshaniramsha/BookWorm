package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashboardUserController {
    public AnchorPane root;
    public AnchorPane changepain;
    public Button btnBook;
    public Button btnborrow;

    public void initialize() {
        setForm("/view/Dashboard02_Form.fxml");
    }

    private void setForm(String form) {
        URL resource = getClass().getResource(form);
        assert resource != null;
        try {
            Parent load = FXMLLoader.load(resource);
            changepain.getChildren().clear();
            changepain.getChildren().add(load);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void bookOnAction(ActionEvent actionEvent) throws Exception {
        setForm("/view/Book2_Form.fxml");
    }

    public void borrowOnAction(ActionEvent actionEvent) throws Exception {
        setForm("/view/Borrow_Form.fxml");
    }

    public void lblLogOut(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void lblHome(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/Login_Form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);
        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void lblDashboard(MouseEvent mouseEvent) {
        setForm("/view/Dashboard02_Form.fxml");
    }

    public void lblNotification(MouseEvent mouseEvent) {
    }

    public void lblSetting(MouseEvent mouseEvent) {
    }
}
