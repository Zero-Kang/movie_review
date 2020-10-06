package org.zerock.mreview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.mreview.dto.MovieReviewDTO;
import org.zerock.mreview.dto.PageRequestDTO;
import org.zerock.mreview.dto.PageResultDTO;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieReview;
import org.zerock.mreview.repository.MovieReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieReviewServiceImpl implements MovieReviewService {

    private final MovieReviewRepository movieReviewRepository;


    @Override
    public List<MovieReviewDTO> getListOfMovie(Long mno){

        Movie movie = Movie.builder().mno(mno).build();

        List<MovieReview> result = movieReviewRepository.findByMovie(movie);

        return result.stream().map(movieReview -> entityToDto(movieReview)).collect(Collectors.toList());
    }

    @Override
    public Long register(MovieReviewDTO movieReviewDTO) {

        MovieReview movieReview = dtoToEntity(movieReviewDTO);

        movieReviewRepository.save(movieReview);

        return movieReview.getReviewnum();
    }

    @Override
    public void modify(MovieReviewDTO movieReviewDTO) {

        Optional<MovieReview> result =
                movieReviewRepository.findById(movieReviewDTO.getReviewnum());

        if(result.isPresent()){

            MovieReview movieReview = result.get();
            movieReview.changeGrade(movieReviewDTO.getGrade());
            movieReview.changeText(movieReviewDTO.getText());

            movieReviewRepository.save(movieReview);
        }

    }


    @Override
    public void remove(Long reviewnum) {

        movieReviewRepository.deleteById(reviewnum);

    }
}














