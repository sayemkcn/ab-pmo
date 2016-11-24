package com.aimslabs.services;

import com.aimslabs.domains.Child;
import com.aimslabs.domains.Question;
import com.aimslabs.domains.QuestionResponse;
import com.aimslabs.repositories.QuestionRepository;
import com.aimslabs.repositories.QuestionResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public List<QuestionResponse> removeResponseIfExists(List<QuestionResponse> responseList, QuestionResponse qResponse) {
        return responseList.stream()
                .filter(e -> e.getQuestionId() != qResponse.getQuestionId())
                .collect(Collectors.toList());
    }


}
