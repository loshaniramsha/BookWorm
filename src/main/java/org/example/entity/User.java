package org.example.entity;

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
public class User {
    @Id
    private String u_id;
    private String u_name;
    private String email;
    private String password;
    private String Status;


    @OneToMany(mappedBy = "user" )
    private List<Borrow> borrows;

    @OneToMany(mappedBy = "user")
    private List<Book2> reterns;
}
