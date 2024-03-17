package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BoFactory;
import org.example.bo.custom.BookBO;
import org.example.bo.custom.BorrowBO;
import org.example.bo.custom.BranchBO;
import org.example.dto.Bookdto;
import org.example.dto.BorrowDto;
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
     BorrowBO borrowBO= (BorrowBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.BORROW);
    public void initialize() throws Exception {
        loardCmb();
        loardAllBook();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        coltittle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colauther.setCellValueFactory(new PropertyValueFactory<>("author"));
        colgenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colstates.setCellValueFactory(new PropertyValueFactory<>("status"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branchId"));
    }

    private void loardAllBook() {
   ObservableList<Bookdto>observableList= FXCollections.observableArrayList();
   try {
       List<Bookdto> allBook =bookBO.getAllBook();
       for (Bookdto bookdto : allBook) {
           observableList.add(bookdto);
       }
       tblbook.setItems(observableList);
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

 /*   public void searchOnAction(ActionEvent actionEvent) throws Exception {
        String id=textId.getText();
       *//* String title=textTittle.getText();*//*
        try {
            Bookdto bookdto=bookBO.searchBook(id);
            if (bookdto!=null){
               *//* textId.setText(bookdto.getBookId());*//*
                textTittle.setText(bookdto.getTitle());
                textAuthor.setText(bookdto.getAuthor());
                textGenre.setText(bookdto.getGenre());
                textStates.setText(bookdto.getStatus());
                cmbBranch.setValue(bookdto.getBranchId());
            }else {
                new Alert(Alert.AlertType.WARNING,"Empty").show();
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }*/
    public void searchOnAction(ActionEvent actionEvent) {
        String id = textId.getText();
        try {
            Bookdto bookdto = bookBO.searchBook(id);
            if (bookdto != null) {
                // Clear previous selections
                tblbook.getSelectionModel().clearSelection();

                // Find the index of the book in the table view
                int index = -1;
                ObservableList<Bookdto> items = tblbook.getItems();
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getBookId().equals(bookdto.getBookId())) {
                        index = i;
                        break;
                    }
                }

                // Highlight the row if the book is found
                if (index >= 0) {
                    tblbook.getSelectionModel().select(index);
                    tblbook.scrollTo(index);
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Book not found: " + id).show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while searching: " + e.getMessage()).show();
            e.printStackTrace(); // Print stack trace for debugging purposes
        }
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
           clear();
       }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws Exception {
        String id = textId.getText();
        boolean isDeleted=bookBO.deleteBook(id);
        if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted").show();
            clear();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws Exception {
        String id = textId.getText();
        String tittle=textTittle.getText();
        String author=textAuthor.getText();
        String genre=textGenre.getText();
        String states=textStates.getText();
        String branch=cmbBranch.getValue().toString();
        Bookdto bookdto=new Bookdto(id,tittle,author,genre,states,branch);
        boolean isUpdated=bookBO.updateBook(bookdto);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
            clear();
        }
    }

    public void cmbBranchOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clear();
    }
    private void clear(){
        textId.clear();
        textTittle.clear();
        textAuthor.clear();
        textGenre.clear();
        textStates.clear();
        cmbBranch.getSelectionModel().clearSelection();
    }
}
