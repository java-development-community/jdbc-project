package com.mucahitarslan.jdbcproject.controller;

import com.mucahitarslan.jdbcproject.dto.request.MovieRequest;
import com.mucahitarslan.jdbcproject.dto.response.MovieResponse;
import com.mucahitarslan.jdbcproject.model.Movie;
import com.mucahitarslan.jdbcproject.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponse> findAllMovies(){
        return movieService.findAllMovies();
    }

    @GetMapping("/sort")
    public List<MovieResponse> findAllMoviesSorting(){
        return movieService.findAllSorting();
    }
    @GetMapping("/page")
    public Page<Movie> getPagingMovies(){
        return movieService.getPageableMovies();
    }

    @PostMapping
    public MovieResponse saveMovie(@RequestBody MovieRequest movieRequest){
        return movieService.save(movieRequest);
    }

}
