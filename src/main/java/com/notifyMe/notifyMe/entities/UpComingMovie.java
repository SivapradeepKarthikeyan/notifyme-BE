package com.notifyMe.notifyMe.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UpcomingMovies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpComingMovie {
    @Id
    private String movieId;
    private String moviePosterUrl;
    private String movieName;
    private String movieLanguage;
    private String movieGenre;
    private String movieReleaseDate;
    private String movieLanguageGroup;
}
