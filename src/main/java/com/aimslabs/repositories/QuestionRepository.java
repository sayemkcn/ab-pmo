package com.aimslabs.repositories;

import com.aimslabs.domains.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sayemkcn on 11/9/16.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    Question findByQuestionId(int questionId);
    List<Question> findAllByOrderByQuestionIdAsc();
}
