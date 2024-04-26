package com.mouvie.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;
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
    private String name;

    @Column(length = 2048)
    @Size(max = 2048)
    private String description;

    @NotNull
    private Date releaseDate;

    @Min(0)
    @Max(5)
    private Integer rating;

    private String cover;
    
    // Relationships

    //fetch = FetchType.EAGER
    @ManyToMany
    @JoinTable(
            uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id", "category_id"}),
            name = "category_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
}
