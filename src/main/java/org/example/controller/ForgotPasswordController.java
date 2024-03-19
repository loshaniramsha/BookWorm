package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.UserBO;
import org.example.dto.Userdto;

import java.io.IOException;
import java.util.Random;

public class ForgotPasswordController {
    public TextField textEmail;
    public TextField textNewPW;
    public TextField textOTP;
    public Button btnDone;
    public TextField textEmail1;
    public TextField textNewPW2;
    public AnchorPane anch;

    public String otp;

    public Userdto user;
    public Button btnBack;

    UserBO userBO = (UserBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.USER);

    public void initialize() {
        textNewPW.setVisible(false);
        textNewPW2.setVisible(false);
        btnDone.setVisible(false);
    }

    public void doneOnAction(ActionEvent actionEvent) throws Exception {

        if (textNewPW.getText().equals(textNewPW2.getText())) {
            user.setPassword(textNewPW2.getText());
            userBO.updateUser(user);
            new Alert(Alert.AlertType.INFORMATION, "Password Changed").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Password Mismatch").show();
        }

    }

    public void otpTypedOnAction(KeyEvent keyEvent) {
        if (textOTP.getText().equals(otp)) {
            textNewPW.setVisible(true);
            textNewPW2.setVisible(true);
            btnDone.setVisible(true);
        }
    }

    public void sendOTPOnAction(ActionEvent actionEvent) throws Exception {
        String mail = textEmail.getText();
        user = userBO.searchByEmail(mail);
        if (user != null) {
            int i = new Random().nextInt(900000) + 100000;
            otp = String.valueOf(i);
            System.out.println(otp);
        }else {
            new Alert(Alert.AlertType.ERROR, "Wrong Email").show();
        }
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/Login_Form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)anch.getScene().getWindow();
        stage.setScene(scene);
        Stage.getWindows();
        stage.centerOnScreen();
    }
}
