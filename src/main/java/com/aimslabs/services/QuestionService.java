package com.aimslabs.services;

import com.aimslabs.domains.Question;
import com.aimslabs.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sayemkcn on 11/9/16.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepo;

    @Transactional(readOnly = true)
    public List<Question> getAll() {
        return this.questionRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Question getOne(Long id) {
        return this.questionRepo.getOne(id);
    }

    @Transactional(readOnly = false)
    public Question save(Question question) {
        return this.questionRepo.save(question);
    }

    @Transactional(readOnly = false)
    public void delete(Question question) {
        this.questionRepo.delete(question);
    }
}
