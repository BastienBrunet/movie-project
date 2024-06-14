package com.mouvie.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "sceances")
public class Sceance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private Date date;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Room room;

    @OneToMany(mappedBy = "sceance", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}
