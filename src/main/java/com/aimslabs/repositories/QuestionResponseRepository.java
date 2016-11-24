package com.aimslabs.repositories;

import com.aimslabs.domains.QuestionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sayemkcn on 11/24/16.
 */
@Repository
public interface QuestionResponseRepository extends JpaRepository<QuestionResponse,Long> {
}
