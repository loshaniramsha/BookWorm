package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Book {
    @Id
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private String status;

    @ManyToOne
    private Branch branch;

    @OneToMany(mappedBy = "book")
    private List<Borrow> borrows;

}
