package org.zerock.mreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mreview.entity.Reviewer;

public interface ReviewerRepository extends JpaRepository<Reviewer, String> {


}
