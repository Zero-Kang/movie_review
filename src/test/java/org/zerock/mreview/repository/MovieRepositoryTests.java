package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.zerock.mreview.entity.Movie;

import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void insertMovies() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            Movie movie = Movie.builder().title("Movie...." +i).build();

            movieRepository.save(movie);

        });
    }

    @Test
    public void testGetListWithAvg() {

        PageRequest pageRequest = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC, "mno"));

        Page<Object[]> result = movieRepository.getListWithReviewAvg(pageRequest);

        result.forEach(arr -> {
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testGetByMno(){

        //리뷰가 여러 개인 영화를 지정
        Object[] arr = movieRepository.getByMno(72L);

        Object[] inner = (Object[])arr[0];

        System.out.println(Arrays.toString(inner));

    }
}









