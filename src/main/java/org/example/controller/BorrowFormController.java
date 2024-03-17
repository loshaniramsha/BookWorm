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
import org.example.bo.custom.UserBO;
import org.example.dto.Bookdto;
import org.example.dto.BorrowDto;
import org.example.dto.Userdto;

import java.time.LocalDate;
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

    UserBO userBO= (UserBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.USER);
    public void initialize() throws Exception {
        loardcmb();
        loardAllBorrow();
        setCellValueFactory();
        textid.setText(borrowBO.generateNextId());
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("borrowId"));
        colUserid.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colbookid.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colborrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        coldueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

    }

    private void loardAllBorrow() {
        try {
            List<BorrowDto> allBorrow = borrowBO.getAllBorrow();
            if (allBorrow != null) {
                ObservableList<BorrowDto> observableList = FXCollections.observableArrayList(allBorrow);
                tblBorrow.setItems(observableList);
            } else {
                // Handle the case where allBorrow is null
                System.out.println("No borrow data available.");
            }
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
            BorrowDto borrowDto = borrowBO.searchBorrow(id);
            if (borrowDto != null) {
                textUserid.setText(borrowDto.getUserId());
                cmbbookId.setValue(borrowDto.getBookId());
                date.setValue(LocalDate.parse(borrowDto.getBorrowDate()));
                duedate.setValue(LocalDate.parse(borrowDto.getDueDate()));
            } else {
                new Alert(Alert.AlertType.WARNING, "No borrow found with ID: " + id).show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while searching: " + e.getMessage()).show();
            e.printStackTrace(); // Print stack trace for debugging purposes
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

        Userdto userdto= userBO.searchUser(userId);
        userdto.setStatus("Pending");
        userBO.updateUser(userdto);

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
