package org.zerock.mreview.service;

import org.zerock.mreview.dto.MovieDTO;
import org.zerock.mreview.dto.PageRequestDTO;
import org.zerock.mreview.dto.PageResultDTO;
import org.zerock.mreview.entity.Movie;

public interface MovieService {

    Long register(MovieDTO movieDTO);

    PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO);

    MovieDTO getMovie(Long mno);

    void modify(MovieDTO movieDTO);

    void remove(Long mno);

    default Movie dtoToEntity(MovieDTO movieDTO){
        Movie movie = Movie.builder()
                .mno(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .file1(movieDTO.getFile1())
                .file2(movieDTO.getFile2())
                .file3(movieDTO.getFile3())
                .build();
        return movie;
    }

    default MovieDTO entityToDto(Movie movie, double avg, int reviewCnt){

        MovieDTO movieDTO = MovieDTO.builder()
                .mno(movie.getMno())
                .title(movie.getTitle())
                .file1(movie.getFile1())
                .file2(movie.getFile2())
                .file3(movie.getFile3())
                .regDate(movie.getRegDate())
                .modDate(movie.getModDate())
                .build();

        movieDTO.setAvg(avg);
        movieDTO.setReviewCnt(reviewCnt);

        return movieDTO;
    }
}
