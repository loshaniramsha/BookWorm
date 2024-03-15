package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Book2 {
    @Id
    private String reternId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;
}
