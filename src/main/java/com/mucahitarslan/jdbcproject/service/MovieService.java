package com.mucahitarslan.jdbcproject.service;

import com.mucahitarslan.jdbcproject.converter.MovieConverter;
import com.mucahitarslan.jdbcproject.dto.request.MovieRequest;
import com.mucahitarslan.jdbcproject.dto.response.MovieResponse;
import com.mucahitarslan.jdbcproject.model.Movie;
import com.mucahitarslan.jdbcproject.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieConverter movieConverter;

    public MovieService(MovieRepository movieRepository, MovieConverter movieConverter) {
        this.movieRepository = movieRepository;
        this.movieConverter = movieConverter;
    }

    public List<MovieResponse> findAllMovies(){
        return StreamSupport.stream(movieRepository.findAll().spliterator(),false)
                .map(movieConverter::toMovieResponse)
                .collect(Collectors.toList());
    }

    public MovieResponse save(MovieRequest movieRequest){

        return movieConverter.toMovieResponse(movieRepository.save(movieConverter.toMovie(movieRequest)));
    }

    public List<MovieResponse> findAllSorting(){
        Sort sort = Sort.by(Sort.Direction.fromString("DESC"), "name");
        return StreamSupport.stream(movieRepository.findAll(sort).spliterator(),false)
                .map(movieConverter::toMovieResponse)
                .collect(Collectors.toList());
    }

    public Page<Movie> getPageableMovies(){
        PageRequest pageRequest = PageRequest.of(1,1);
        Page<Movie> pagedMovies = movieRepository.findAll(pageRequest);

        return pagedMovies;
    }
}
