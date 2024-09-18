package com.notifyMe.notifyMe.repositories;

import com.notifyMe.notifyMe.entities.UpComingMovie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UpComingMovieRepository extends PagingAndSortingRepository<UpComingMovie,String> {

    @Query("SELECT u FROM UpComingMovie u WHERE u.movieLanguage = :language")
    Page<UpComingMovie> findByLanguage(String language, Pageable pageable);


    @Query("SELECT u FROM UpComingMovie u WHERE u.movieLanguage = :language AND u.movieGenre = :genre")
    Page<UpComingMovie> findByLanguageAndGenre(String language, String genre, Pageable pageable);

}

