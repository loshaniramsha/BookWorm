package org.example.dto;

import lombok.*;
import org.example.dto.tm.Branchtm;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Bookdto {

    private String bookId;
    private String title;
    private String author;
    private String genre;
    private String status;
    private String branchId;


   /* public Bookdto(String bookId, String title, String author, String genre, String status, Branchdto branchdto) {
    }*/
}
