package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.UserBO;
import org.example.dto.Userdto;
import org.example.util.Regex;

public class UserRegisterController {
    public TextField textPassword;
    public TextField textName;
    public TextField textEmail;
    public TextField textId;
    public Button btnCreate;
    public AnchorPane anch;
    public Button btnBack;
    public ComboBox cmbStates;

    UserBO userBO= (UserBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.USER);
    public void initialize() throws Exception {
        textId.setText(userBO.generateNextId());
        loardCmb();
    }

    private void loardCmb() {
        ObservableList<String> options = FXCollections.observableArrayList(
                "have Book",
                "Not have Book"
        );
        cmbStates.setItems(options);
    }

    public void createOnAction(ActionEvent actionEvent) {
        if (textEmail.getText().isEmpty() || textName.getText().isEmpty() || textPassword.getText().isEmpty() ){
            new Alert(Alert.AlertType.WARNING,"Empty").show();
        } else{

            if(Regex.getEmailPattern().matcher(textEmail.getText()).matches()){
                if (Regex.getNamePattern().matcher(textName.getText()).matches()) {
                    try {
                        Userdto userdto = new Userdto(textId.getText(), textName.getText(), textEmail.getText(), textPassword.getText(), cmbStates.getValue().toString());
                        userBO.saveUser(userdto);
                        new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.WARNING,"Failed").show();
                    }
                }else{
                    new Alert(Alert.AlertType.WARNING,"Invalid Name").show();
                }
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Invalid Email").show();
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

    public void cmbStatesOnAction(ActionEvent actionEvent) {
    }
}
