package org.example.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BoFactory;
import org.example.bo.custom.UserBO;
import org.example.dto.Userdto;

public class LoginFormController {
    public AnchorPane root;
    public Label lblLogin;
    public TextField textName;
    public TextField textPassword;
    public Button btnsing;
    public Button btnLogin;
    public ComboBox cmb;
    public TextField txtPassword;
    public PasswordField passWord;
    public ImageView imgEye;
    private FontAwesomeIconView fxEyeIcon;
    private boolean isHidden = true;

    UserBO userBO= (UserBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.USER);

    public void initialize() {
        txtPassword.setVisible(false);
    }
    public void lblforgotPassword(MouseEvent mouseEvent) {
    }

    public void signinOnAction(ActionEvent actionEvent) {
    }

    public void loginOnAction(ActionEvent actionEvent)throws Exception {

       /*Admin*/
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/Dashboard_Form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);
        Stage.getWindows();
        stage.centerOnScreen();

       /* User*/
      /*  AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/DashboardUser.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);
        Stage.getWindows();
        stage.centerOnScreen();*/

    }

    public void cbmOnAction(ActionEvent actionEvent) {
    }





    public void lblSingUp(MouseEvent mouseEvent) throws Exception {
        AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/view/UserRegister_Form.fxml"));
        Scene scene=new Scene(anchorPane);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);
        Stage.getWindows();
        stage.centerOnScreen();
    }

    public void userNameOnAction(ActionEvent actionEvent) {
    }

    public void passWordOnAction(ActionEvent actionEvent) {
    }




    public void passWordRelease(KeyEvent keyEvent) {
        String text = passWord.getText();
        txtPassword.setText(text);
    }

    public void lblEyeOnAction(MouseEvent mouseEvent) {
        if (passWord.isVisible()) {
            passWord.setVisible(false);
            txtPassword.setVisible(true);
        } else {
            passWord.setVisible(true);
            txtPassword.setVisible(false);
        }
    }

    public void textPassword(KeyEvent keyEvent) {
        String text = txtPassword.getText();
        passWord.setText(text);
    }
}
