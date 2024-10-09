package com.notifyMe.notifyMe.services;

import com.notifyMe.notifyMe.DTOs.UpComingMovieDTO;
import com.notifyMe.notifyMe.DTOs.UserMovieDTO;
import com.notifyMe.notifyMe.entities.Movie;
import com.notifyMe.notifyMe.entities.UpComingMovie;
import com.notifyMe.notifyMe.entities.User;
import com.notifyMe.notifyMe.repositories.MovieRepository;
import com.notifyMe.notifyMe.repositories.UpComingMovieRepository;
import com.notifyMe.notifyMe.repositories.UserRepository;
import com.notifyMe.notifyMe.responses.NotifyMeResponse;
import jdk.jfr.Name;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.context.*;

import java.util.*;

import static com.notifyMe.notifyMe.constants.Constants.*;

@Service
public class MovieService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UpComingMovieRepository upComingMovieRepository;

    //Logger
    Logger logger = LoggerFactory.getLogger(MovieService.class);

    @Name("To add all upcoming movies to DB")
    public NotifyMeResponse addUpComingMovies(List<UpComingMovieDTO> upComingMovieDTOList) {
        try {
            for (UpComingMovieDTO upComingMovieDTO : upComingMovieDTOList) {

                UpComingMovie upComingMovie = new UpComingMovie();
                upComingMovie.setMovieId(upComingMovieDTO.getMovieId());
                upComingMovie.setMovieName(upComingMovieDTO.getMovieName());
                upComingMovie.setMoviePosterUrl(upComingMovieDTO.getMoviePosterUrl());
                upComingMovie.setMovieLanguage(upComingMovieDTO.getMovieLanguage());
                upComingMovie.setMovieGenre(upComingMovieDTO.getMovieGenre());
                upComingMovie.setMovieReleaseDate(upComingMovieDTO.getMovieReleaseDate());
                upComingMovie.setMovieLanguageGroup(upComingMovieDTO.getMovieLanguageGroup());

                upComingMovieRepository.save(upComingMovie);
            }
            return new NotifyMeResponse(SUCCESS, UPCOMING_MOVIES_ADD_SUCCESS, 200, null);
        } catch (Exception e) {
//            logger.warn(e.getMessage());
            return new NotifyMeResponse(FAILED, UPCOMING_MOVIES_ADD_FAILED, 500, null);
        }
    }

    @Name("To add a movie to user")
    public NotifyMeResponse addMovieToUser(UserMovieDTO userMovieDTO) {

        if (isMovieAlreadyRegistered(userMovieDTO))
            return new NotifyMeResponse(FAILED, MOVIE_ALREADY_REGISTERED, 409, null);

        try {
            User user = userRepository.findByUserId(userMovieDTO.getUserId()).orElse(new User());
            user.setUserId(userMovieDTO.getUserId());
            user.setUserName(userMovieDTO.getUserName());
            user.setUserNotificationTypes(userMovieDTO.getUserNotificationTypes());

            Movie movie = new Movie();
            movie.setMovieId(userMovieDTO.getUserMovieId());
            movie.setMovieName(userMovieDTO.getUserMovieName());
            movie.setMovieUrl(userMovieDTO.getUserMovieUrl());
            List<Movie> userMovies = Objects.isNull(user.getMovie()) ? new ArrayList<>() : new ArrayList<>(user.getMovie());
            userMovies.add(movie);
            user.setMovie(userMovies);

            userRepository.save(user);

            return new NotifyMeResponse(SUCCESS, MOVIE_ADDED_SUCCESS, 200, userMovieDTO);
        } catch (Exception e) {
            return new NotifyMeResponse(FAILED, e.getMessage(), 500, null);
        }
    }

    boolean isMovieAlreadyRegistered(UserMovieDTO userMovieDTO) {
        logger.warn("IS ALREADY REGISTERED CHECK :: ");
        Optional<User> optionalUser = userRepository.findByUserId(userMovieDTO.getUserId());
        logger.warn("OP USER :: " + optionalUser);
        optionalUser.ifPresent(user -> System.out.println("IS ALREADY REGISTERED :: " + optionalUser));
        if (optionalUser.isPresent()) {
            Optional<Movie> movie = optionalUser.get().getMovie().stream()
                    .filter(m -> m.getMovieId().equals(userMovieDTO.getUserMovieId()))
                    .findFirst();
            return movie.isPresent();
        }
        return false;
    }


    @Name("To get all users")
    public NotifyMeResponse getAllUsers() {
        List<User> users = userRepository.findAll();
        return new NotifyMeResponse(SUCCESS, MOVIES_FETCH_SUCCESS, 200, users);
    }

    @Name("To get all movies")
    public NotifyMeResponse getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return new NotifyMeResponse(SUCCESS, MOVIES_FETCH_SUCCESS, 200, movies);
    }
}
