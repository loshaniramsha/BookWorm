package org.example.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BoFactory;
import org.example.bo.custom.BookBO;
import org.example.bo.custom.UserBO;
import org.example.dto.Bookdto;
import org.example.dto.Userdto;

import java.util.List;

public class Book2Controller {
    public AnchorPane root;
    public JFXTextField textBookname;
    public Button btnsearch;
    public TableColumn colid;
    public TableColumn coltittle;
    public TableColumn colauther;
    public TableColumn colgenre;
    public TableColumn colstates;
    public TableColumn colBranch;
    public Button btnRetern;
    public TableView tblbook;

    BookBO bookBO= (BookBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.BOOK);
    UserBO userBO= (UserBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.USER);

    public void initialize() {
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
        ObservableList<Bookdto> observableList= FXCollections.observableArrayList();
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

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String bookName= textBookname.getText();
        try {
            Bookdto bookdto = bookBO.searchBook(bookName);
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
                new Alert(Alert.AlertType.WARNING, "Book not found: " + bookName).show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while searching: " + e.getMessage()).show();
            e.printStackTrace(); // Print stack trace for debugging purposes
        }

    }

    public void btnReternOnAction(ActionEvent actionEvent) {

    }
}
