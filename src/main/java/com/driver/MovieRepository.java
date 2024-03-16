package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public HashMap<String, Movie> getMovieMap() {
        return movieMap;
    }

    public void setMovieMap(HashMap<String, Movie> movieMap) {
        this.movieMap = movieMap;
    }

    public HashMap<String, Director> getDirectorMap() {
        return directorMap;
    }

    public void setDirectorMap(HashMap<String, Director> directorMap) {
        this.directorMap = directorMap;
    }

    public HashMap<String, List<String>> getDirectorMovieMapping() {
        return directorMovieMapping;
    }

    public void setDirectorMovieMapping(HashMap<String, List<String>> directorMovieMapping) {
        this.directorMovieMapping = directorMovieMapping;
    }


    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie){
        movieMap.put(movie.getName(), movie);
    }

    public void saveDirector(Director director){
        directorMap.put(director.getName(), director);
    }

    public void saveMovieDirectorPair(String movie, String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director) && !directorMovieMapping.containsKey(director)){
            directorMovieMapping.put(director,new ArrayList<String>());
            directorMovieMapping.get(director).add(movie);
            Director dir = directorMap.get(director);
            dir.setNumberOfMovies(dir.getNumberOfMovies()+1);
        }
        else{
            directorMovieMapping.get(director).add(movie);
            directorMovieMapping.get(director).add(movie);
            Director dir = directorMap.get(director);
            dir.setNumberOfMovies(dir.getNumberOfMovies()+1);
        }
    }

    public Movie findMovie(String movie){
        Movie m = movieMap.get(movie);
        return m;
    }

    public Director findDirector(String director){
        Director d = directorMap.get(director);
        return d;
    }

    public List<String> findMoviesFromDirector(String director){
        List<String> list = directorMovieMapping.get(director);
        return list;
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director){
        directorMovieMapping.remove(director);
        directorMap.remove(director);
    }

    public void deleteAllDirector(){
        directorMap.clear();
    }
}