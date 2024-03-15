package org.example.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.example.bo.BoFactory;
import org.example.bo.custom.BookBO;

import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class Dashboard02Controller implements Initializable {
    public AnchorPane child;
    public Text lbltime;
    public Text lbldate;
    public Text lblBook;
    public Text lblwritter;

    BookBO bookBO= (BookBO) BoFactory.getBoFactory().getBO(BoFactory.BOType.BOOK);

/*    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        loadDateandTime();
        try {
            setValues();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        loadDateandTime();
        try {
            setValues();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void setValues() throws Exception {
        lblBook.setText(String.valueOf(bookBO.getAllBook().size()));
    }

    private void loadDateandTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lbldate.setText(format.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter format2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            lbltime.setText(LocalTime.now().format(format2));
        }), new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

}
