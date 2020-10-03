package org.zerock.mreview.repository;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.zerock.mreview.entity.Reviewer;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewerRepositoryTests {

    @Autowired
    private ReviewerRepository reviewerRepository;

    @Autowired
    private MovieReviewRepository movieReviewRepository;

    @Test
    public void insertReviewers() {

        IntStream.rangeClosed(1,100).forEach(i -> {
            Reviewer reviewer = Reviewer.builder().nickname("reviewer"+i).build();
            reviewerRepository.save(reviewer);
        });
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteReviewer() {

        String nickname ="reviewer14";

        Reviewer reviewer = Reviewer.builder().nickname(nickname).build();


        movieReviewRepository.deleteByReviewer(reviewer);
        reviewerRepository.deleteById(nickname);

    }
}
