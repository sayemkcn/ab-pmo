package com.aimslabs.services;

import com.aimslabs.domains.QuestionResponse;
import com.aimslabs.repositories.QuestionResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sayemkcn on 11/24/16.
 */
@Service
public class QuestionResponseService {
    @Autowired
    private QuestionResponseRepository questionResponseRepo;

    public QuestionResponse getOne(Long id) {
        return this.questionResponseRepo.getOne(id);
    }
}
