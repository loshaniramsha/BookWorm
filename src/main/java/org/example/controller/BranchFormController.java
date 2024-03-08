package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BoFactory;
import org.example.bo.custom.BranchBO;
import org.example.dto.Branchdto;

import java.util.List;

public class BranchFormController {
    BranchBO branchBO= (BranchBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.BRANCH);
    public AnchorPane child;
    public TextField textid;
    public TextField textbranch;
    public Button btnsearch;
    public Button btnsave;
    public Button btndelete;
    public Button btnupdate;
    public TableColumn colid;
    public TableColumn colbranch;

    public void initialize() {
        loardAllBranch();

    }

    private void loardAllBranch() {
        ObservableList<Branchdto> observableList= FXCollections.observableArrayList();
        try {
            List<Branchdto> branchdtoList = branchBO.getAllBranch();
            for (Branchdto branchdto : branchdtoList) {
               observableList.add(new Branchdto(branchdto.getBranchId(),branchdto.getLocation()));
            }
        } catch (Exception e) {
      throw new RuntimeException(e);
        }
        }


    public void searchOnAction(ActionEvent actionEvent) {
    }

    public void saveOnAction(ActionEvent actionEvent) throws Exception {
        String id = textid.getText();
        String branch = textbranch.getText();
        Branchdto branchdto= new Branchdto(id,branch);
        boolean isSaved=branchBO.saveBranch(branchdto);
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void init(DashboardController dashboardController) {
    }
}
