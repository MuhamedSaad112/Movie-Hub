package com.moviehub.service;


import com.moviehub.dto.MovieDTO;
import com.moviehub.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {


    private final com.moviehub.repository.MovieRepository movieRepository;


    private final FileService fileService;

    @Value("${project.poster}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;


    @Override
    public MovieDTO addMovie(MovieDTO movieDTO, MultipartFile file) throws IOException {


        String uploadedFileName = fileService.uploadFile(path, file);

        movieDTO.setPoster(uploadedFileName);

        Movie movie = new Movie();

        movie.setTitle(movieDTO.getTitle());
        movie.setDirector(movieDTO.getDirector());
        movie.setStudio(movieDTO.getStudio());
        movie.setMovieCast(movieDTO.getMovieCast());
        movie.setReleaseYear(movieDTO.getReleaseYear());
        movie.setPoster(movieDTO.getPoster());
        Movie savedMovie = movieRepository.save(movie);

        String postrUrl = baseUrl + "/file/" + uploadedFileName;

        MovieDTO response = new MovieDTO();

        response.setMovieId(savedMovie.getMovieId());
        response.setTitle(savedMovie.getTitle());
        response.setDirector(savedMovie.getDirector());
        response.setStudio(savedMovie.getStudio());
        response.setMovieCast(savedMovie.getMovieCast());
        response.setReleaseYear(savedMovie.getReleaseYear());
        response.setPoster(savedMovie.getPoster());
        response.setPosterUrl(postrUrl);


        return response;
    }

    @Override
    public MovieDTO getMovie(Long movieId) {
        return null;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        return List.of();
    }
}
