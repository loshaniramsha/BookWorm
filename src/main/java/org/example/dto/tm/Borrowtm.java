package org.example.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Borrowtm {
    private String borrowId;
    private String borrowDate;
    private String dueDate;
    private String userId;
    private String bookId;
}
