package com.notifyMe.notifyMe.repositories;

import com.notifyMe.notifyMe.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,String> {
    Optional<Movie> findByMovieId(String movieId);
}