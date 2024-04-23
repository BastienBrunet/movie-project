package com.mouvie.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
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

    
    // Relationships

    @ManyToMany
    @JoinTable(
            uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id", "categories_id"}),
            name = "movies_categories",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private List<Category> categories;
}
