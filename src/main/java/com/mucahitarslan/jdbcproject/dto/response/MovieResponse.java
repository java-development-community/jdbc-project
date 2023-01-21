package com.mucahitarslan.jdbcproject.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovieResponse{
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate sceneTime;
    private long rating;
    private BigDecimal cost;

    public MovieResponse() {
    }

    public MovieResponse(String name, LocalDate sceneTime, long rating, BigDecimal cost) {
        this.name = name;
        this.sceneTime = sceneTime;
        this.rating = rating;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getSceneTime() {
        return sceneTime;
    }

    public void setSceneTime(LocalDate sceneTime) {
        this.sceneTime = sceneTime;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}