package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Borrow {
    @Id
    private String borrowId;
    private String borrowDate;
    private String dueDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;
}
