package org.example.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BorrowDto {
    private String borrowId;
    private String borrowDate;
    private String dueDate;
    private String userId;
    private String bookId;
}
