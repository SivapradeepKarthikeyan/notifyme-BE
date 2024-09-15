package com.notifyMe.notifyMe.controllers;

import com.notifyMe.notifyMe.DTOs.UserDTO;
import com.notifyMe.notifyMe.responses.NotifyMeResponse;
import com.notifyMe.notifyMe.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    //Logger
    Logger logger = LoggerFactory.getLogger(MovieService.class);

    @PostMapping("/api/v1/movie")
    public ResponseEntity<NotifyMeResponse> addMovieToUser(@RequestBody UserDTO userDTO){
        logger.info(userDTO.toString());
        NotifyMeResponse notifyMeResponse=movieService.addMovieToUser(userDTO);
        return ResponseEntity.status(notifyMeResponse.getStatus_code()).body(notifyMeResponse);
    }
}
