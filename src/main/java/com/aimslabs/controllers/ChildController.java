package com.aimslabs.controllers;

import com.aimslabs.domains.Child;
import com.aimslabs.domains.Question;
import com.aimslabs.domains.QuestionResponse;
import com.aimslabs.domains.User;
import com.aimslabs.services.ChildService;
import com.aimslabs.services.ParentService;
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
    @Autowired
    private ChildService childService;
    @Autowired
    private ParentService parentService;

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
        qResponse.setQuestionId(questionId);
        qResponse.setUserResponse(userResponse);

        // pull responselist from session and update it with new list
        List<QuestionResponse> responseList = (List<QuestionResponse>) session.getAttribute("responseList");
        if (responseList == null || responseList.isEmpty())
            responseList = new ArrayList<>();
        // check if repsonse already exists, if yes then remove previous response
        responseList = this.questionResponseService.removeResponseIfExists(responseList, qResponse);
        responseList.add(qResponse);
        session.setAttribute("responseList", responseList);

        // If screening test over
        if (questionId >= this.questionService.getAll().size()) {
            Child child = (Child) session.getAttribute("child");
            child.setResponseList((List<QuestionResponse>) session.getAttribute("responseList"));
            child.setAppResult(this.childService.isAutismDetected(child));
            User user = (User) session.getAttribute("user");
            if (user == null)
                return "redirect:/login";
            child.setParent(this.parentService.getParentByUser(user));
            this.childService.saveChild(child);
            System.out.println(this.childService.isAutismDetected(child));
            return "redirect:/dashboard";
        }
        System.out.println(session.getAttribute("responseList"));
        return "redirect:/child/screening/" + (++questionId);
    }
}
