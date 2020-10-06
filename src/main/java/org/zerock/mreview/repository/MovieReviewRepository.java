package org.zerock.mreview.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieReview;
import org.zerock.mreview.entity.Reviewer;

import java.util.List;

public interface MovieReviewRepository extends JpaRepository<MovieReview, Long> {

    @EntityGraph(attributePaths = {"reviewer"})
    List<MovieReview> findByMovie(Movie movie);

    //void deleteByReviewer (Reviewer reviewer);

    @Modifying
    @Query("delete from MovieReview mr where mr.reviewer = :reviewer")
    void deleteByReviewer(Reviewer reviewer);


    @Modifying
    @Query("delete from MovieReview mr where mr.movie = :movie")
    void deleteByMovie(Movie movie);
}


