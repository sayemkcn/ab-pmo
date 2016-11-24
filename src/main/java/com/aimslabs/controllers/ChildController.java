package com.aimslabs.controllers;

import com.aimslabs.domains.Child;
import com.aimslabs.domains.Question;
import com.aimslabs.domains.QuestionResponse;
import com.aimslabs.services.QuestionResponseService;
import com.aimslabs.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by sayemkcn on 11/24/16.
 */
@Controller
@RequestMapping("/child")
public class ChildController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionResponseService questionResponseService;

    // -------SCREENING ------ //
    @RequestMapping(value = "/screening/start", method = RequestMethod.GET)
    public String startScreeningPage() {
        return "parents/screening/start";
    }

    @RequestMapping(value = "/screening/start", method = RequestMethod.POST)
    public String startScreening(@ModelAttribute Child child, BindingResult bindingResult,
                                 HttpSession session) {
        if (bindingResult.hasErrors())
            System.out.println(bindingResult.toString());
        session.setAttribute("child", child);

        System.out.println(session.getAttribute("child"));
        return "redirect:/child/screening/1";
    }

    @RequestMapping(value = "/screening/{questionId}", method = RequestMethod.GET)
    public String screeningQuestionariesPage(@PathVariable("questionId") Integer questionId,
                                             Model model) {
        Question question = this.questionService.findByQuestionId(questionId);
        model.addAttribute("question", question);
        return "parents/screening/questionaries";
    }

    @RequestMapping(value = "/screening/{questionId}", method = RequestMethod.POST)
    public String screeningQuestionaries(@PathVariable("questionId") Integer questionId,
                                         @RequestParam("userResponse") Boolean userResponse,
                                         HttpSession session) {
        QuestionResponse qResponse = new QuestionResponse();
        // check if this response already exists
//        QuestionResponse existionResponse = this.questionResponseService.getOne(questionId.longValue());
//        if (existionResponse != null) // if exists then update bye setting id
//            qResponse.setId(existionResponse.getId()); // Bye the way, questionId is being used as response primary id
        qResponse.setQuestionId(questionId);
        qResponse.setUserResponse(userResponse);

        // pull responselist from session and update it with new list
        Set<QuestionResponse> responseSet = (Set<QuestionResponse>) session.getAttribute("responseList");
        if (responseSet == null || responseSet.isEmpty())
            responseSet = new HashSet<>();
        // check if repsonse already exists, if yes then remove previous response
        responseSet = this.questionResponseService.removeResponseIfExists(responseSet, qResponse);
        responseSet.add(qResponse);
        session.setAttribute("responseList", responseSet);

        System.out.println(session.getAttribute("responseList"));
        return "redirect:/child/screening/" + (++questionId);
    }
}
