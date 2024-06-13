package com.mouvie.library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "reservation_status")
public class ReservationStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
}
