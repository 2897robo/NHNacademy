package com.nhnacademy.app;

import java.util.*;

public class Movie {
    private final long movieId;
    private final String title;
    private final Set<String> genres;

    public Movie(long movieId, String title, Set<String> genres) {
        this.movieId = movieId;
        this.title = title;
        this.genres = genres;
    }

    @Override
    public String toString() {
        return ("movieID : " + movieId + "\ntitle : " + title + "\ngenres : " + genres + "\n");
    }
}