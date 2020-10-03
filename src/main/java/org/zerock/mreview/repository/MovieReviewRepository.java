package org.zerock.mreview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieReview;
import org.zerock.mreview.entity.Reviewer;

public interface MovieReviewRepository extends JpaRepository<MovieReview, Long> {

    Page<MovieReview> findByMovie(Movie movie, Pageable pageable);

    @Modifying
    @Query("delete from MovieReview mr where mr.reviewer = :reviewer")
    void deleteByReviewer(Reviewer reviewer);
}


