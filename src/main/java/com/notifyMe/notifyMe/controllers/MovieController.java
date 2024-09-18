package com.notifyMe.notifyMe.controllers;

import com.notifyMe.notifyMe.DTOs.UserDTO;
import com.notifyMe.notifyMe.responses.NotifyMeResponse;
import com.notifyMe.notifyMe.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    //Logger
    Logger logger = LoggerFactory.getLogger(MovieService.class);

    //Sort by :: releaseDate
    @GetMapping("api/v1/movie")
    public ResponseEntity<NotifyMeResponse> getMoviesByReleaseDate(
            @RequestParam(value = "start", required = false, defaultValue = "1") String start,
            @RequestParam(value = "end", required = false, defaultValue = "10") String end
    ) {
        NotifyMeResponse notifyMeResponse = movieService.getMoviesByReleaseDate(Integer.parseInt(start), Integer.parseInt(end));
        return ResponseEntity.status(notifyMeResponse.getStatus_code()).body(notifyMeResponse);
    }

    //Find by :: language
    @GetMapping("api/v1/movie/{language}")
    public ResponseEntity<NotifyMeResponse> getMoviesByLanguage(
            @PathVariable String language,
            @RequestParam(value = "start", required = false, defaultValue = "1") String start,
            @RequestParam(value = "end", required = false, defaultValue = "10") String end
    ) {
        NotifyMeResponse notifyMeResponse = movieService.getMoviesByLanguage(language,Integer.parseInt(start),Integer.parseInt(end));
        return ResponseEntity.status(notifyMeResponse.getStatus_code()).body(notifyMeResponse);
    }


    //Find by :: language , genre
    @GetMapping("api/v1/movie/{language}/{genre}")
    public ResponseEntity<NotifyMeResponse> getMoviesByLanguageAndGenre(
            @PathVariable String language,
            @PathVariable String genre,
            @RequestParam(value = "start", required = false, defaultValue = "1") String start,
            @RequestParam(value = "end", required = false, defaultValue = "10") String end
    ) {
        logger.warn(language+" : : "+genre);
        NotifyMeResponse notifyMeResponse = movieService.getMoviesByLanguageAndGenre(language,genre,Integer.parseInt(start),Integer.parseInt(end));
        return ResponseEntity.status(notifyMeResponse.getStatus_code()).body(notifyMeResponse);
    }


    @PostMapping("/api/v1/movie")
    public ResponseEntity<NotifyMeResponse> addMovieToUser(@RequestBody UserDTO userDTO) {
        logger.info(userDTO.toString());
        NotifyMeResponse notifyMeResponse = movieService.addMovieToUser(userDTO);
        return ResponseEntity.status(notifyMeResponse.getStatus_code()).body(notifyMeResponse);
    }
}
