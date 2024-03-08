package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BoFactory;
import org.example.bo.custom.BookBO;
import org.example.bo.custom.BranchBO;
import org.example.dto.Bookdto;
import org.example.dto.Branchdto;

import java.util.List;

public class BookFormController {
    public AnchorPane child;
    public Button btnsearch;
    public Button btnsave;
    public Button btndelete;
    public Button btnupdate;
    public TableView tblbook;
    public TableColumn colid;
    public TableColumn coltittle;
    public TableColumn colauther;
    public TableColumn colgenre;
    public TableColumn colstates;
    public TableColumn colBranch;
    public ComboBox cmbBranch;
    public TextField textId;
    public TextField textTittle;
    public TextField textAuthor;
    public TextField textGenre;
    public TextField textStates;
    BookBO bookBO= (BookBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.BOOK);
     BranchBO branchBO= (BranchBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.BRANCH);
    public void initialize() throws Exception {
        loardCmb();
        loardAllBook();
    }

    private void loardAllBook() {
        ObservableList<Bookdto> observableList= FXCollections.observableArrayList();
        try {
            List<Bookdto> bookdtoList= bookBO.getAllBook();
            if (bookdtoList == null) {
                new Alert(Alert.AlertType.WARNING, "Empty").show();
            } else {
                for (Bookdto bookdto: bookdtoList) {
                observableList.add(new Bookdto(bookdto.getBookId(),bookdto.getTitle(),bookdto.getAuthor(),bookdto.getGenre(),bookdto.getStatus(),bookdto.getBranchId()));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loardCmb() throws Exception {
   cmbBranch.getItems().clear();
        List<Branchdto> allBranch =branchBO.getAllBranch();
        for (Branchdto branchdto : allBranch) {
            cmbBranch.getItems().add(branchdto.getBranchId());
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
    }

    public void saveOnAction(ActionEvent actionEvent) throws Exception {
       String id=textId.getText();
       String tittle=textTittle.getText();
       String author=textAuthor.getText();
       String genre=textGenre.getText();
       String states=textStates.getText();
       String branch=cmbBranch.getValue().toString();
       Bookdto bookdto=new Bookdto(id,tittle,author,genre,states,branch);
        System.out.println("branch1: "+branch);
       boolean isSaved=bookBO.saveBook(bookdto);
       if (isSaved){
           new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
       }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void cmbBranchOnAction(ActionEvent actionEvent) {
    }
}
