package com.notifyMe.notifyMe.DTOs;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpComingMovieDTO {
    @Id
    private String movieId;
    private String moviePosterUrl;
    private String movieName;
    private String movieLanguage;
    private String movieGenre;
    private String movieReleaseDate;
    private String movieLanguageGroup;
}
