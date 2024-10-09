package com.notifyMe.notifyMe.repositories;

import com.notifyMe.notifyMe.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    @Query(value = "SELECT * FROM movies",nativeQuery = true)
    ArrayList<Movie> getAllMovies();

}
