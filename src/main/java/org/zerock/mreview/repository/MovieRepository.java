package org.zerock.mreview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {


    @Query("select m, avg(COALESCE(r.grade,0)), count(r) " +
            "from Movie m left join MovieReview r on r.movie = m group by m")
    Page<Object[]> getListWithReviewAvg(Pageable pageRequest);

    @Query("select m, avg(COALESCE(r.grade,0)) , count(r) " +
            "from Movie m left join MovieReview r on r.movie = m where m.mno =:mno")
    Object[] getByMno(Long mno);

}
