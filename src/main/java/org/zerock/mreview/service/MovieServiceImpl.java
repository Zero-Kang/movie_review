package org.zerock.mreview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.dto.MovieDTO;
import org.zerock.mreview.dto.PageRequestDTO;
import org.zerock.mreview.dto.PageResultDTO;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.repository.MovieRepository;
import org.zerock.mreview.repository.MovieReviewRepository;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;

    private final MovieReviewRepository movieReviewRepository;

    @Override
    public Long register(MovieDTO movieDTO) {

        Movie movie = dtoToEntity(movieDTO);

        movieRepository.save(movie);

        return movie.getMno();
    }

    @Override
    public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListWithReviewAvg(pageable);

        Function<Object[], MovieDTO> fn = (arr -> entityToDto((Movie)arr[0] , (Double) arr[1], ((Long)arr[2]).intValue()));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public MovieDTO getMovie(Long mno) {

        Object[]result = movieRepository.getByMno(mno);

        Object[] row = (Object[])result[0];

        return entityToDto((Movie)row[0], (Double) row[1], ((Long)row[2]).intValue());
    }

    @Override
    public void modify(MovieDTO movieDTO) {

        Optional<Movie> result = movieRepository.findById(movieDTO.getMno());

        if(result.isPresent()){

            Movie movie = result.get();

            movie.changeTitle(movieDTO.getTitle());
            movie.changeFile1(movieDTO.getFile1());
            movie.changeFile2(movieDTO.getFile2());
            movie.changeFile3(movieDTO.getFile3());

            movieRepository.save(movie);
        }
    }

    @Transactional
    @Override
    public void remove(Long mno) {

        Movie movie = Movie.builder().mno(mno).build();

        movieReviewRepository.deleteByMovie(movie);

        movieRepository.deleteById(mno);


    }
}
