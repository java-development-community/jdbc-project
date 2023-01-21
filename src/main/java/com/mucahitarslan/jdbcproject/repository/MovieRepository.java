package com.mucahitarslan.jdbcproject.repository;

import com.mucahitarslan.jdbcproject.model.Movie;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public class MovieRepository implements IMovieRepository, PagingAndSortingRepository{
    private static final String FIND_ALL_SQL = "select * from movies";
    private static final String SAVE_SQL = "insert into movies (name,scene_time,rating,cost,imdb) values (:name, :sceneTime, :rating, :cost, :imdb)";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MovieRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void fillMovies(ResultSet resultSet, ArrayList<Movie> movies) throws SQLException {
        do {
            long id = resultSet.getLong(1);
            var name = resultSet.getString(2);
            var sceneTime = resultSet.getDate(3).toLocalDate();
            var rating = resultSet.getLong(4);
            var cost = resultSet.getBigDecimal(5);
            var imdb = resultSet.getFloat(6);

            movies.add(new Movie(id,name,sceneTime,rating,cost,imdb));
        }while (resultSet.next());
    }

    @Override
    public Iterable<Movie> findAll() {
        ArrayList<Movie> movies = new ArrayList<>();
        jdbcTemplate.query(FIND_ALL_SQL,(ResultSet rs) -> fillMovies(rs,movies));
        return movies;
    }

    @Override
    public <S extends Movie> S save(S movie) {

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(movie);

        jdbcTemplate.update(SAVE_SQL,parameterSource,generatedKeyHolder,new String[]{"movie_id"});
        movie.setId(generatedKeyHolder.getKey().longValue());
        return movie;
    }

    @Override
    public Iterable<Movie> findAll(Sort sort) {
        var order = sort.toList().get(0);
        List<Movie> movies = StreamSupport.stream(findAll().spliterator(), false).toList();
        return jdbcTemplate.query("SELECT * FROM movies ORDER BY " + order.getProperty() + " " + order.getDirection().name(), (rs, rowNum) -> {
            long id = rs.getLong(1);
            String name = rs.getString(2);
            LocalDate sceneTime = rs.getDate(3).toLocalDate();
            long rating = rs.getLong(4);
            BigDecimal cost = rs.getBigDecimal(5);
            float imdb = rs.getFloat(6);
            return new Movie(id,name,sceneTime,rating,cost,imdb);
        });
    }


    @Override
    public Page<Movie> findAll(Pageable pageable) {
        var order = !pageable.getSort().isEmpty() ? pageable.getSort().toList().get(0) : Sort.Order.by("name");

        var movies = jdbcTemplate.query("SELECT * FROM movies ORDER BY " + order.getProperty() + " " + order.getDirection().name() +
                " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset(),(rs,rowNum) -> {
            long id = rs.getLong(1);
            String name = rs.getString(2);
            LocalDate sceneTime = rs.getDate(3).toLocalDate();
            long rating = rs.getLong(4);
            BigDecimal cost = rs.getBigDecimal(5);
            float imdb = rs.getFloat(6);
            return new Movie(id,name,sceneTime,rating,cost,imdb);
        });
        return new PageImpl<>(movies,pageable,count());
    }

    @Override
    public <S extends Movie> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Movie> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Movie> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Movie entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Movie> entities) {

    }

    @Override
    public void deleteAll() {

    }

}
