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
    @Autowired
    private QuestionRepository questionRepo;

    public QuestionResponse getOne(Long id) {
        return this.questionResponseRepo.getOne(id);
    }

    public List<QuestionResponse> removeResponseIfExists(List<QuestionResponse> responseList, QuestionResponse qResponse) {
        return responseList.stream()
                .filter(e -> e.getQuestionId() != qResponse.getQuestionId())
                .collect(Collectors.toList());
    }


    // AUTISM DETECTION ALGORITHM
    public boolean isAutismDetected(Child child) {
        List<QuestionResponse> responseList = child.getResponseList();
        int matches = 0;
        int crutialMatches = 0;
        for (int i = 0; i < responseList.size(); i++) {
            QuestionResponse response = responseList.get(i);
            // find question for that response by question id (not primary id)
            Question question = this.questionRepo.findByQuestionId(response.getQuestionId());
            // check if user response and button value for a question matches
            if (question.isAutismDetectedForPositiveText()==response.isUserResponse()){
                matches++;
                // check if question is crutial;
                if (question.isCritical())
                    crutialMatches++;
            }
            
        }
        System.out.println("MATCH: " + matches + "\nCRUTIAL MATCHES: " + crutialMatches);
        return crutialMatches >= 2 || matches >= 3;
    }

}
