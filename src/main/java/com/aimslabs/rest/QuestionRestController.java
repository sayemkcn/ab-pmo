package com.aimslabs.rest;

import com.aimslabs.domains.Question;
import com.aimslabs.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: rezaul || Date: 11/28/16.
 */
@RestController
public class QuestionRestController {

    @Autowired
    QuestionService questionServices;


    @CrossOrigin
    @RequestMapping(value = "/api/question/all", method = RequestMethod.POST)
    public void submitQuestionnairesReport(@RequestBody List<Question> questions) {
        questionServices.createQuestionList(questions);
    }

}
