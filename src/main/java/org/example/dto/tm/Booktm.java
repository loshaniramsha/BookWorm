package org.example.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Booktm {

    private String bookId;
    private String title;
    private String author;
    private String genre;
    private String status;
    private String branchId;
}
