package org.example.dto;

import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Userdto {
    private String u_id;
    private String u_name;
    private String email;
    private String password;
    private String Status;
}
