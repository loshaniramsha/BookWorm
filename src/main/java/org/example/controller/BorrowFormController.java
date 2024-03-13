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
import org.example.dto.Bookdto;
import org.example.dto.BorrowDto;
import org.example.dto.Userdto;

import java.util.Date;
import java.util.List;

public class BorrowFormController {
    public AnchorPane child;
    public TextField textid;
    public ComboBox cmbusername;
    public ComboBox cmbbookId;
    public DatePicker date;
    public DatePicker duedate;
    public Button btnsearch;
    public Button btnsave;
    public Button btndelete;
    public Button btnupdate;
    public TableView tblBorrow;
    public TableColumn colid;
    public TableColumn colUsername;
    public TableColumn colbookid;
    public TableColumn colborrowDate;
    public TableColumn coldueDate;
    public TableColumn colUserid;
    public ComboBox cmbUserId;
    public TextField textUserid;
    public Button btnClear;

    BookBO bookBO= (BookBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.BOOK);
    BorrowBO borrowBO= (BorrowBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.BORROW);
    public void initialize() throws Exception {
        loardcmb();
        loardAllBorrow();
        setCellValueFactory();
    }

    private void setCellValueFactory() {

    }

    private void loardAllBorrow() {
        ObservableList<BorrowDto>observableList= FXCollections.observableArrayList();
        try {
            List<BorrowDto> allBorrow =borrowBO.getAllBorrow();
            for (BorrowDto borrowDto : allBorrow) {
                observableList.add(borrowDto);
            }
            tblBorrow.setItems(observableList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loardcmb() throws Exception {
        cmbbookId.getItems().clear();
        List<Bookdto> allBook =bookBO.getAllBook();
        for (Bookdto bookdto : allBook) {
            cmbbookId.getItems().add(bookdto.getBookId());
        }
    }

    public void cmbBookOnAction(ActionEvent actionEvent) {
    }

    public void searchOnAction(ActionEvent actionEvent) throws Exception {
        String id = textid.getText();
         try {
             BorrowDto borrowDto=borrowBO.searchBorrow(id);
             if (borrowDto != null) {
                 textUserid.setText(borrowDto.getUserId());
              /*   cmbbookId.setValue(borrowDto.getBookId());
                 date.setValue(borrowDto.getBorrowDate());
                 duedate.setValue(borrowDto.getDueDate());*/
             }else {
                 new Alert(Alert.AlertType.WARNING,"Empty").show();
             }
         }catch (Exception e){
             throw new RuntimeException(e);
         }
    }

    public void saveOnAction(ActionEvent actionEvent) throws Exception {
        String id = textid.getText();
        String userId=textUserid.getText();
        String bookId= cmbbookId.getValue().toString();
        String borrowDate= date.getValue().toString();
        String dueDate= duedate.getValue().toString();

        BorrowDto borrowDto=new BorrowDto(id,borrowDate,dueDate,userId,bookId);
        boolean isSaved=borrowBO.saveBorrow(borrowDto);

        Bookdto bookdto = bookBO.searchBook(bookId);
        bookdto.setStatus("borrowed");

        bookBO.updateBook(bookdto);

        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
            clear();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws Exception {
        String id = textid.getText();
        if (borrowBO.deleteBorrow(id)){
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted").show();
            clear();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws Exception {
        String id = textid.getText();
        String userId=textUserid.getText();
        String bookId= cmbbookId.getValue().toString();
        String borrowDate= date.getValue().toString();
        String dueDate= duedate.getValue().toString();
        BorrowDto borrowDto=new BorrowDto(id,userId,bookId,borrowDate,dueDate);
       try {
           boolean isUpdated=borrowBO.updateBorrow(borrowDto);
           if (isUpdated){
               new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
               clear();
           }
       }catch (Exception e){
           throw new RuntimeException(e);
       }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clear();
    }
    private void clear(){
        textid.clear();
        textUserid.clear();
        cmbbookId.getSelectionModel().clearSelection();
        date.setValue(null);
        duedate.setValue(null);
    }
}
