package com.moviehub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a movie entity in the system.
 * This entity stores comprehensive information about a movie including its metadata,
 * cast information, reviews, and awards.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "movies")
public class Movie {

    /**
     * Unique identifier for the movie
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    /**
     * The title of the movie. Must be unique in the database.
     */
    @NotBlank(message = "Movie title is required")
    @Column(nullable = false, length = 200)
    private String title;


    /**
     * Name of the movie's director
     */
    @NotBlank(message = "Director name is required")
    @Column(nullable = false)
    private String director;

    /**
     * studio of the movie's director
     */
    @NotBlank(message = "studio is required")
    @Column(nullable = false)
    private String studio;


    /**
     * Set of actors/actresses who performed in the movie
     */
    @ElementCollection
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCast = new HashSet<>();


    /**
     * The official release date of the movie
     */
    @Column(nullable = false)
    private Integer releaseYear;

    /**
     * URL or path to the movie's poster image
     */
    @NotBlank(message = "Movie poster required")
    @Column(nullable = false)
    private String poster;
}

