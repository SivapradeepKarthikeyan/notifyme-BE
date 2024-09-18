package com.notifyMe.notifyMe.services;

import com.notifyMe.notifyMe.DTOs.UserDTO;
import com.notifyMe.notifyMe.entities.Movie;
import com.notifyMe.notifyMe.entities.UpComingMovie;
import com.notifyMe.notifyMe.entities.User;
import com.notifyMe.notifyMe.repositories.MovieRepository;
import com.notifyMe.notifyMe.repositories.UpComingMovieRepository;
import com.notifyMe.notifyMe.repositories.UserRepository;
import com.notifyMe.notifyMe.responses.NotifyMeResponse;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


import java.util.HashSet;
import java.util.Set;

import static com.notifyMe.notifyMe.constants.Constants.*;

@Service
@Transactional
public class MovieService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    UpComingMovieRepository upComingMovieRepository;

    //Logger
    Logger logger = LoggerFactory.getLogger(MovieService.class);

    public NotifyMeResponse addMovieToUser(UserDTO userDTO) {
        logger.info("Movie DTO :: " + userDTO.toString());
        try {
            User user=new User();
            user.setUserId(userDTO.getUserId());
            user.setUserName(userDTO.getUserName());
            user.setUserNotificationTypes(user.getUserNotificationTypes());

            Movie movie=new Movie();
            movie.setMovieId(userDTO.getUserMovieId());
            movie.setMovieName(userDTO.getUserMovieName());
            movie.setMovieUrl(userDTO.getUserMovieUrl());

            Set<Movie> movies=new HashSet<>();
            movies.add(movie);
            user.setMovie(movies);
            logger.warn("User entity to be saved :: "+user.toString());
            logger.warn("before saving ::");
            userRepository.save(user);

            return new NotifyMeResponse(SUCCESS, MOVIE_ADDED_SUCCESS, 200, userDTO);
        } catch (Exception e) {
            return new NotifyMeResponse(FAILED, e.getMessage(), 500, null);
        }
    }

    public NotifyMeResponse getMoviesByReleaseDate(int start, int end) {
        Pageable pageRequest = PageRequest.of(start, end, Sort.by("movieReleaseDate").ascending());
        Page<UpComingMovie> page = upComingMovieRepository.findAll(pageRequest);
        if (page.isEmpty())
            return new NotifyMeResponse(FAILED, PAGINATION_FAILED, 500, null);
        return new NotifyMeResponse(SUCCESS, PAGINATION_SUCCESS, 200, page);
    }

    public NotifyMeResponse getMoviesByLanguage(String language, int start, int end) {
        Pageable pageRequest = PageRequest.of(start, end);
        Page<UpComingMovie> page = upComingMovieRepository.findByLanguage(language,pageRequest);
        if (page.isEmpty())
            return new NotifyMeResponse(FAILED, PAGINATION_FAILED, 500, null);
        return new NotifyMeResponse(SUCCESS, PAGINATION_SUCCESS, 200, page);
    }


    public NotifyMeResponse getMoviesByLanguageAndGenre(String language, String genre, int start, int end) {
        Pageable pageRequest = PageRequest.of(start, end);
        Page<UpComingMovie> page = upComingMovieRepository.findByLanguageAndGenre(language, genre, pageRequest);
        if (page.isEmpty())
            return new NotifyMeResponse(FAILED, PAGINATION_FAILED, 500, null);
        return new NotifyMeResponse(SUCCESS, PAGINATION_SUCCESS, 200, page);
    }


}
