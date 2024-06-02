package com.mouvie.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;

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
    
    
    
    // Relationships

   // @ManyToMany
   // @JoinTable(
//            uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id", "category_id"}),
//            name = "category_movie",
//            joinColumns = @JoinColumn(name = "movie_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id")
//            )
    private String movie; //un UUID V4 pour le format, correspond à l'UID d'un film
}
