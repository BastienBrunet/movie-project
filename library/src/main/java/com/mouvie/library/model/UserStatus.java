package com.mouvie.library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Entity
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "user_status")
public class UserStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
}
