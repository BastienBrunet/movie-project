package com.mouvie.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "rooms")
@JsonIgnoreProperties(value= {"sceances"})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 128)
    @Size(max = 128)
    @NotNull
    private String name;

    @Min(1)
    @NotNull
    private Integer seats;

    @CreationTimestamp
    @Column(updatable = false, name = "created_on")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated_on")
    private Instant updatedAt;

    @ManyToOne
    private Cinema cinema;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Sceance> sceances;
    
}
