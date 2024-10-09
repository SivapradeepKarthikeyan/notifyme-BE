package com.notifyMe.notifyMe.repositories;

import com.notifyMe.notifyMe.entities.UpComingMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpComingMovieRepository extends JpaRepository<UpComingMovie,String> { }

