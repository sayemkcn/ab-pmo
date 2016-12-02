package com.aimslabs.controllers;

import com.aimslabs.domains.*;
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


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String childList(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/login";
        Parent parent = this.parentService.getParentByUser(user);
        model.addAttribute("childList", parent.getChildList());
        return "child/all";
    }

    // -------SCREENING ------ //
    @RequestMapping(value = "/screening/start", method = RequestMethod.GET)
    public String startScreeningPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        // Check if user is logged in
        if (user == null)
            return "redirect:/login";
        else if (this.parentService.getParentByUser(user) == null)
            return "redirect:/profile/create?message=You must create a profile first!";
        return "parents/screening/start";
    }

    @RequestMapping(value = "/screening/start", method = RequestMethod.POST)
    public String startScreening(@ModelAttribute Child child, BindingResult bindingResult,
                                 HttpSession session) {
        if (bindingResult.hasErrors())
            System.out.println(bindingResult.toString());
        session.setAttribute("child", child);

//        System.out.println(session.getAttribute("child"));
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
            boolean isDetected = this.childService.isAutismDetected(child);
            child.setAppResult(isDetected);
            User user = (User) session.getAttribute("user");
            if (user == null)
                return "redirect:/login";
            child.setParent(this.parentService.getParentByUser(user));
            this.childService.saveChild(child);
            // clear session data
            session.removeAttribute("child");
            session.removeAttribute("responseList");
//            System.out.println(this.childService.isAutismDetected(child));
            if (isDetected)
                return "redirect:/child?message=" + child.getName() + " is in a risk of Autism. Please contact nearest Autism center immediately!";
            else
                return "redirect:/child?message=Nothing to be worried about yet! " + child.getName() + " is functioning properly.";
        }
        System.out.println(session.getAttribute("responseList"));
        return "redirect:/child/screening/" + (++questionId);
    }
}
