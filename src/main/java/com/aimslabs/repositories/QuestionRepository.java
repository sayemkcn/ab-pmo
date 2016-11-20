package com.aimslabs.repositories;

import com.aimslabs.domains.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sayemkcn on 11/9/16.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
}
