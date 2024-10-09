package com.notifyMe.notifyMe.controllers;

import com.notifyMe.notifyMe.DTOs.UpComingMovieDTO;
import com.notifyMe.notifyMe.DTOs.UserMovieDTO;
import com.notifyMe.notifyMe.responses.NotifyMeResponse;
import com.notifyMe.notifyMe.services.MovieService;
import jdk.jfr.Name;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    //Logger
    Logger logger = LoggerFactory.getLogger(MovieService.class);

    @Name("Adds all upcoming movies to DB")
    @PostMapping("api/v1/movie/upcoming")
    public ResponseEntity<NotifyMeResponse> addUpcomingMovies(@RequestBody List<UpComingMovieDTO> upComingMovieDTOList) {
        logger.info(upComingMovieDTOList.toString());
        NotifyMeResponse notifyMeResponse = movieService.addUpComingMovies(upComingMovieDTOList);
        return ResponseEntity.status(notifyMeResponse.getStatusCode()).body(notifyMeResponse);
    }

    @Name("Adds a movies to user")
    @PostMapping("/api/v1/movie")
    public ResponseEntity<NotifyMeResponse> addMovieToUser(@RequestBody UserMovieDTO userMovieDTO) {
        logger.info(userMovieDTO.toString());
        NotifyMeResponse notifyMeResponse = movieService.addMovieToUser(userMovieDTO);
        return ResponseEntity.status(notifyMeResponse.getStatusCode()).body(notifyMeResponse);
    }


    @Name("Get all users")
    @GetMapping("/api/v1/user")
    public ResponseEntity<NotifyMeResponse> getAllUsers() {
        NotifyMeResponse notifyMeResponse = movieService.getAllUsers();
        return ResponseEntity.status(notifyMeResponse.getStatusCode()).body(notifyMeResponse);
    }


    @Name("Get all movies")
    @GetMapping("/api/v1/movie")
    public ResponseEntity<NotifyMeResponse> getAllMovies() {
        NotifyMeResponse notifyMeResponse = movieService.getAllMovies();
        return ResponseEntity.status(notifyMeResponse.getStatusCode()).body(notifyMeResponse);
    }
}
