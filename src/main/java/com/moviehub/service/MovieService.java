package com.moviehub.service;


import com.moviehub.dto.MovieDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {

    MovieDTO addMovie(MovieDTO movieDTO, MultipartFile file) throws IOException;

    MovieDTO getMovie(Long movieId);

    public List<MovieDTO> getAllMovies();


}
