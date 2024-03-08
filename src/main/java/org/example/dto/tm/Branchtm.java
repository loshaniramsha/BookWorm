package org.example.dto.tm;

import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Branchtm {

    private String branchId;
    private String location;
}

