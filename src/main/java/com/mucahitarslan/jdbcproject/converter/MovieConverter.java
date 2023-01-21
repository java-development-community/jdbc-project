package com.mucahitarslan.jdbcproject.converter;

import com.mucahitarslan.jdbcproject.dto.request.MovieRequest;
import com.mucahitarslan.jdbcproject.dto.response.MovieResponse;
import com.mucahitarslan.jdbcproject.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieConverter {

    public MovieResponse toMovieResponse(Movie movie){
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setName(movie.getName());
        movieResponse.setRating(movie.getRating());
        movieResponse.setCost(movie.getCost());
        movieResponse.setSceneTime(movie.getSceneTime());
        return movieResponse;
    }

    public Movie toMovie(MovieRequest movieRequest){
        Movie movie = new Movie();
        movie.setName(movieRequest.getName());
        movie.setCost(movieRequest.getCost());
        movie.setRating(movieRequest.getRating());
        movie.setImdb(movieRequest.getImbd());
        movie.setSceneTime(movieRequest.getSceneTime());
        return movie;
    }
}
