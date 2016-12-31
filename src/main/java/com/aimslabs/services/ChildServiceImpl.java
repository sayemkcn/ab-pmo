package com.aimslabs.services;

import com.aimslabs.domains.Child;
import com.aimslabs.domains.Question;
import com.aimslabs.domains.QuestionResponse;
import com.aimslabs.repositories.ChildRepository;
import com.aimslabs.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sayemkcn on 11/25/16.
 */
@Service
public class ChildServiceImpl implements ChildService {

    private static final String FIELD_NAME = "id";

    @Autowired
    private QuestionRepository questionRepo;
    @Autowired
    private ChildRepository childRepo;

    @Override
    public List<Child> getAllChildPaginated(int page, int size) {
        Page<Child> childPage = this.childRepo.findAll(new PageRequest(page, size, Sort.Direction.DESC, FIELD_NAME));
        return childPage.getContent();
    }

    @Override
    public Child saveChild(Child child) {
        return childRepo.saveAndFlush(child);
    }

    @Override
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
            if (question.isAutismDetectedForPositiveText() == response.isUserResponse()) {
                matches++;
                // check if question is crutial;
                if (question.isCritical())
                    crutialMatches++;
            }
        }
        System.out.println("MATCH: " + matches + "\nCRUTIAL MATCHES: " + crutialMatches);
        return crutialMatches >= 2 || matches >= 3;
    }

    @Override
    public List<Child> getAllPendingChildList(int page, int size) {
        Page<Child> childPage = this.childRepo.findAllPristine(new PageRequest(page, size, Sort.Direction.DESC, FIELD_NAME));
//        List<Child> childList = childPage.getContent();
        return childPage.getContent();
    }

    @Override
    public Child getById(Long id) {
        return this.childRepo.getOne(id);
    }
}
