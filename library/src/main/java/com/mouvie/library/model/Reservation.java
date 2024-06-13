package com.mouvie.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Min(0)
    @NotNull
    private Integer rank;
    
    @Min(1)
    @NotNull
    private Integer seats;

    @ManyToOne
    private ReservationStatus status;
    
    @CreationTimestamp
    @Column(updatable = false, name = "created_on")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated_on")
    private Instant updatedAt;
    
    @UpdateTimestamp
    @Column(name = "expires_at")
    private Instant expiresAt;

    @ManyToOne
    private Sceance sceance;

    @ManyToOne
    private User user;
}
