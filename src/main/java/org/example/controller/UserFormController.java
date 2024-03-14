package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BoFactory;
import org.example.bo.custom.UserBO;
import org.example.dto.Userdto;

import java.util.List;

public class UserFormController {
    public AnchorPane root;
    public Label lbl;
    public TextField textPassword;
    public TextField textId;
    public TextField textName;
    public TextField textEmail;
    public Button btnsearch;
    public Button btnSave;
    public Button btndelete;
    public Button btnupdate;
    public Button btnClear;
    public TableView tblUser;
    public TableColumn colUserid;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colPassword;
    public TableColumn colStates;
    public ComboBox cmbStates;

    UserBO userBO= (UserBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.USER);

    public void initialize() {

        loardAllUser();
        setCellValueFactory();
        loardCmb();
    }

    private void loardCmb() {
        ObservableList<String> options = FXCollections.observableArrayList(
                "have Book",
                "Not have Book"
        );
          cmbStates.setItems(options);
       /* comboBox.setItems(options);*/

    }

    private void setCellValueFactory() {
        colUserid.setCellValueFactory(new PropertyValueFactory<>("u_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("u_name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        colStates.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loardAllUser() {
        ObservableList<Userdto> observableList= FXCollections.observableArrayList();
        try {
            List<Userdto> userdtoList= userBO.getAllUser();
            for (Userdto userdto: userdtoList) {
                observableList.add(new Userdto(userdto.getU_id(),userdto.getU_name(),userdto.getEmail(),userdto.getPassword(),userdto.getStatus()));
            }
            tblUser.setItems(observableList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void searchOnAction(ActionEvent actionEvent) throws Exception{
        String id=textId.getText();
        try {
            Userdto userdto=userBO.searchUser(id);
            if (userdto!=null){
                textName.setText(userdto.getU_name());
                textEmail.setText(userdto.getEmail());
                textPassword.setText(userdto.getPassword());
            }else {
                new Alert(Alert.AlertType.WARNING,"Empty").show();
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void saveOnAction(ActionEvent actionEvent) throws Exception {
        String id = textId.getText();
        String name = textName.getText();
        String email = textEmail.getText();
        String password = textPassword.getText();
        String status = (String) cmbStates.getValue();
        Userdto userdto=new Userdto(id,name,email,password,status);
        boolean isSaved=userBO.saveUser(userdto);
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
            clear();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws Exception{
        String id=textId.getText();
        if (userBO.deleteUser(id)){
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted").show();
            clear();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws Exception {
     String id=textId.getText();
     String name=textName.getText();
     String email=textEmail.getText();
     String password=textPassword.getText();
     String status=(String) cmbStates.getValue();
     Userdto userdto=new Userdto(id,name,email,password,status);
     try {
         boolean isUpdated=userBO.updateUser(userdto);
         if (isUpdated){
             new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
             clear();
         }
     } catch (Exception e) {
         throw new RuntimeException(e);
     }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clear();
    }
    private void clear(){
        textId.clear();
        textName.clear();
        textEmail.clear();
        textPassword.clear();
    }

    public void cmbStatesOnAction(ActionEvent actionEvent) {
    }
}
