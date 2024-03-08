package org.example.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BoFactory;
import org.example.bo.custom.BookBO;
import org.example.bo.custom.BorrowBO;
import org.example.dto.Bookdto;
import org.example.dto.BorrowDto;

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

    BookBO bookBO= (BookBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.BOOK);
    BorrowBO borrowBO= (BorrowBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.BORROW);
    public void initialize() throws Exception {
        loardcmb();
        loardAllBorrow();
    }

    private void loardAllBorrow() {
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

    public void searchOnAction(ActionEvent actionEvent) {
    }

    public void saveOnAction(ActionEvent actionEvent) throws Exception {
        String id = textid.getText();
        String userId=textUserid.getText();
        String bookId= cmbbookId.getValue().toString();
        String borrowDate= date.getValue().toString();
        String dueDate= duedate.getValue().toString();

        BorrowDto borrowDto=new BorrowDto(id,userId,bookId,borrowDate,dueDate);
        boolean isSaved=borrowBO.saveBorrow(borrowDto);

        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }
}
