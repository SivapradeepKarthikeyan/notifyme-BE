package com.notifyMe.notifyMe.services;

import com.notifyMe.notifyMe.DTOs.UserDTO;
import com.notifyMe.notifyMe.entities.Movie;
import com.notifyMe.notifyMe.entities.User;
import com.notifyMe.notifyMe.entities.UsersMoviesMapping;
import com.notifyMe.notifyMe.repositories.MovieRepository;
import com.notifyMe.notifyMe.repositories.UserRepository;
import com.notifyMe.notifyMe.repositories.UsersMoviesMappingRepository;
import com.notifyMe.notifyMe.responses.NotifyMeResponse;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.notifyMe.notifyMe.constants.Constants.*;

@Service
@Transactional
public class MovieService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    UsersMoviesMappingRepository usersMoviesMappingRepository;

    //Logger
    Logger logger = LoggerFactory.getLogger(MovieService.class);

    public NotifyMeResponse addMovieToUser(UserDTO userDTO) {
        logger.info("Movie DTO :: " + userDTO.toString());
        try {
            User user = userRepository.findByUserId(userDTO.getUserId()).orElseGet(() -> {
                User newUser = new User();
                newUser.setUserId(userDTO.getUserId());
                newUser.setUserName(userDTO.getUserName());
                newUser.setUserNotificationTypes(userDTO.getUserNotificationTypes());
                return userRepository.save(newUser);
            });

            Movie movie = movieRepository.findByMovieId(userDTO.getUserMovieId()).orElseGet(() -> {
                Movie newMovie = new Movie();
                newMovie.setMovieId(userDTO.getUserMovieId());
                newMovie.setMovieName(userDTO.getUserMovieName());
                newMovie.setMovieUrl(userDTO.getUserMovieUrl());
                return movieRepository.save(newMovie);
            });

            //Check if this pair is already exist
            boolean exists = usersMoviesMappingRepository.existsByUserIdAndMovieId(user.getUserId(), movie.getMovieId());
            if (exists) {
                return new NotifyMeResponse(CONFLICT, MOVIE_CONFLICT, 409,null);
            }
            UsersMoviesMapping usersMoviesMapping = new UsersMoviesMapping();
            usersMoviesMapping.setUserId(user.getUserId());
            usersMoviesMapping.setMovieId(movie.getMovieId());
            usersMoviesMappingRepository.save(usersMoviesMapping);
            return new NotifyMeResponse(SUCCESS, MOVIE_ADDED_SUCCESS, 200, userDTO);
        } catch (Exception e) {
            return new NotifyMeResponse(FAILED, e.getMessage(), 500, null);
        }
    }

}
