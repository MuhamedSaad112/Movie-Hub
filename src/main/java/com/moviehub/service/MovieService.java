package com.moviehub.service;


import com.moviehub.dto.MovieDTO;
import com.moviehub.dto.MoviePageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {

    MovieDTO addMovie(MovieDTO movieDTO, MultipartFile file) throws IOException;

    MovieDTO getMovie(Long movieId);

    List<MovieDTO> getAllMovies();

    MovieDTO updateMovie(Long movieId, MovieDTO movieDTO, MultipartFile file) throws IOException;

    String deleteMovie(Long movieId) throws IOException;

    MoviePageResponse getAllMoviesWithPagination(Integer pageNumber, Integer pageSize);

    MoviePageResponse getAllMoviesWithPaginationAndSorting(Integer pageNumber, Integer pageSize, String sortBy, String direction);


}
