package com.mouvie.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 128)
    @Size(max = 128)
    @NotNull
    private String name;

    @Column(length = 4096)
    @Size(max = 4096)
    @NotNull
    private String description;

    @NotNull
    private Date releaseDate;

    @Min(1)
    @Max(5)
    @NotNull
    private Integer rating;
    
    @Min(1)
    @Max(239)
    @NotNull
    private Integer duration;

    private String cover;
    
    @CreationTimestamp
    @Column(updatable = false, name = "created_on")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated_on")
    private Instant updatedAt;
    
    // Relationships

    //fetch = FetchType.EAGER
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id", "category_id"}),
            name = "category_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
}
