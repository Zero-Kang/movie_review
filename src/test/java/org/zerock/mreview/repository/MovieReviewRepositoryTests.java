package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieReview;
import org.zerock.mreview.entity.Reviewer;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieReviewRepositoryTests {

    @Autowired
    private MovieReviewRepository movieReviewRepository;

    @Test
    public void insertMoviewReviews() {

        //200개의 리뷰를 등록
        IntStream.rangeClosed(1,200).forEach(i -> {

            //영화 번호
            Long mno = (long)(Math.random()*100) + 1;
            Movie movie = Movie.builder().mno(mno).build();

            //리뷰어번호
            String nickname = "reviewer" + ( (int)(Math.random()*100) + 1 );
            Reviewer reviewer = Reviewer.builder().nickname(nickname).build();

            MovieReview movieReview = MovieReview.builder()
                    .movie(movie)
                    .reviewer(reviewer)
                    .grade((int)(Math.random()* 5) + 1)
                    .text("이 영화에 대한 느낌..."+i)
                    .build();

            movieReviewRepository.save(movieReview);
        });
    }

    @Test
    public void getMovieReviews() {

        Sort sort = Sort.by(Sort.Direction.ASC,"reviewnum");
        PageRequest pageRequest = PageRequest.of(0,10,sort);

        Movie movie = Movie.builder().mno(100L).build();

        Page<MovieReview> result = movieReviewRepository.findByMovie(movie, pageRequest);

        result.forEach(movieReview -> {

            System.out.print(movieReview.getReviewnum());
            System.out.print("\t"+movieReview.getGrade());
            System.out.print("\t"+movieReview.getText());
            System.out.print("\t"+movieReview.getReviewer().getNickname());
            System.out.println("---------------------------");
        });
    }
}
