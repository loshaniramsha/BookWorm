package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.UserBO;
import org.example.dto.Userdto;

public class UserRegisterController {
    public TextField textPassword;
    public TextField textName;
    public TextField textEmail;
    public TextField textId;
    public Button btnCreate;
    public AnchorPane anch;

    UserBO userBO= (UserBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.USER);

    public void createOnAction(ActionEvent actionEvent) {
        if (textEmail.getText().isEmpty() || textName.getText().isEmpty() || textPassword.getText().isEmpty() ){
            new Alert(Alert.AlertType.WARNING,"Empty").show();
        } else{
            try {
                Userdto userdto = new Userdto(textId.getText(), textName.getText(), textEmail.getText(), textPassword.getText());
                userBO.saveUser(userdto);
                new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.WARNING,"Failed").show();
            }
        }
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
