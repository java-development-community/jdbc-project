package com.mucahitarslan.jdbcproject.repository;

import com.mucahitarslan.jdbcproject.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface IMovieRepository extends CrudRepository<Movie,Long> {
}
