package com.mouvie.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
@Table(name = "cinema")
@JsonIgnoreProperties(value= {"rooms"})
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 128)
    @Size(max = 128)
    @NotNull
    private String name;

    @CreationTimestamp
    @Column(updatable = false, name = "created_on")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated_on")
    private Instant updatedAt;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
    private List<Room> rooms;
}
