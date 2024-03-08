package org.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Branch {
    @Id
    private String branchId;
    private String location;

    @OneToMany(mappedBy = "branch" ,cascade = CascadeType.ALL)
    private List<Book> books;



}

