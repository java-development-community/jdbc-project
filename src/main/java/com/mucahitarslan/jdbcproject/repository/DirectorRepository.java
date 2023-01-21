package com.mucahitarslan.jdbcproject.repository;

import com.mucahitarslan.jdbcproject.model.Director;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class DirectorRepository implements IDirectorRepository{
    private static final String FIND_ALL_SQL = "select * from directors";
    private static final String SAVE_SQL = "insert into directors (name,birth_date) values (:name, :birthDate)";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DirectorRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Director> findAll() {
        ArrayList<Director> directors = new ArrayList<>();
        jdbcTemplate.query(FIND_ALL_SQL,(rs, dr) -> {
            var id = rs.getLong(1);
            var name = rs.getString(2);
            var birthDate = rs.getDate(3).toLocalDate();
            directors.add(new Director(id,name,birthDate));

            return directors;
        });
        return directors;
    }

    @Override
    public <S extends Director> S save(S director) {
        var keyHolder = new GeneratedKeyHolder();
        var parameterSource = new BeanPropertySqlParameterSource(director);

        parameterSource.registerSqlType("birthDate", Types.DATE);
        jdbcTemplate.update(SAVE_SQL,parameterSource,keyHolder,new String[]{"director_id"});

        director.setId(keyHolder.getKey().longValue());

        return director;
    }

    @Override
    public <S extends Director> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Director> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Director> findAllById(Iterable<Long> longs) {
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
    public void delete(Director entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Director> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
