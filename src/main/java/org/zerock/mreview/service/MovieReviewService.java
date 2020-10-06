package org.zerock.mreview.service;

import org.zerock.mreview.dto.MovieReviewDTO;
import org.zerock.mreview.dto.PageRequestDTO;
import org.zerock.mreview.dto.PageResultDTO;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieReview;
import org.zerock.mreview.entity.Reviewer;

import java.util.List;

public interface MovieReviewService {

    //영화의 모든 영화리뷰를 가져온다.
    List<MovieReviewDTO> getListOfMovie(Long mno);

    //영화 리뷰를 추가
    Long register(MovieReviewDTO movieReviewDTO);

    //특정한 영화리뷰 수정
    void modify(MovieReviewDTO movieReviewDTO);

    //영화 리뷰 삭제
    void remove(Long reviewnum);

    default MovieReview dtoToEntity(MovieReviewDTO movieReviewDTO){

        MovieReview movieReview = MovieReview.builder()
                .reviewnum(movieReviewDTO.getReviewnum())
                .movie(Movie.builder().mno(movieReviewDTO.getMno()).build())
                .reviewer(Reviewer.builder().rid(movieReviewDTO.getRid()).build())
                .grade(movieReviewDTO.getGrade())
                .text(movieReviewDTO.getText())
                .build();

        return movieReview;
    }

    default MovieReviewDTO entityToDto(MovieReview movieReview){

        MovieReviewDTO movieReviewDTO = MovieReviewDTO.builder()
                .reviewnum(movieReview.getReviewnum())
                .mno(movieReview.getMovie().getMno())
                .rid(movieReview.getReviewer().getRid())
                .nickname(movieReview.getReviewer().getNickname())
                .grade(movieReview.getGrade())
                .text(movieReview.getText())
                .regDate(movieReview.getRegDate())
                .modDate(movieReview.getModDate())
                .build();

        return movieReviewDTO;
    }
}
