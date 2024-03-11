package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashboardController {
    public AnchorPane root;
    public Button btnUser;
    public Button btnBook;
    public Button btnborrow;
    public Button btnbranch;
    public AnchorPane changepain;
    public static boolean isAdminUnlock = false;

    public void initialize(){
        setForm("/view/Dashboard02_Form.fxml");
    }

    public void setForm(String form){
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

    public void userOnAction(ActionEvent actionEvent) throws Exception {

        if (isAdminUnlock) {
            goUserForm();
        }else {

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/Admin_Form.fxml"));
            Parent load = fxmlLoader.load();
            AdminUnlouckFormController adminUnlouckFormController = fxmlLoader.getController();
            adminUnlouckFormController.init(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Admin Login");
            stage.centerOnScreen();
            stage.show();

        }
    }

    public void goUserForm() {
        setForm("/view/user_form.fxml");
    }
    public void goBranchForm() {
        setForm("/view/Branch_form.fxml");
    }


    public void bookOnAction(ActionEvent actionEvent) throws Exception {
        setForm("/view/Book_Form.fxml");
    }

    public void borrowOnAction(ActionEvent actionEvent) throws Exception {
        setForm("/view/Borrow_Form.fxml");
    }

    public void branchOnAction(ActionEvent actionEvent) throws IOException {
        if (isAdminUnlock) {
            goBranchForm();
        }else {

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/Admin_Form.fxml"));
            Parent load = fxmlLoader.load();
            AdminUnlouckFormController adminUnlouckFormController = fxmlLoader.getController();
            adminUnlouckFormController.init(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Admin Login");
            stage.centerOnScreen();
            stage.show();

        }
    }

    public void lblHome(MouseEvent mouseEvent) {
    }

    public void lblDashboard(MouseEvent mouseEvent) {
    }

    public void lblNotification(MouseEvent mouseEvent) {
    }

    public void lblSetting(MouseEvent mouseEvent) {
    }

    public void lblLogOut(MouseEvent mouseEvent) throws Exception {
        System.exit(0);
    }

    public void lblLogout(MouseEvent mouseEvent) throws Exception {
        System.exit(0);
    }
}
