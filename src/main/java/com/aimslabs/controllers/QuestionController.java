package com.aimslabs.controllers;

import com.aimslabs.commons.ImageValidator;
import com.aimslabs.domains.Question;
import com.aimslabs.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by sayemkcn on 11/9/16.
 */
@Controller
@RequestMapping(value = "/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    ImageValidator imageValidator;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allQuestions(Model model) {
        model.addAttribute("questionList", this.questionService.getAll());
        return "questions/all";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPage() {
        return "questions/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createQuestion(@ModelAttribute Question question, BindingResult bindingResult,
                                 @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (bindingResult.hasErrors())
            System.out.println(bindingResult.toString());
        if (imageValidator.isImageValid(multipartFile)) {
            question.setFile(multipartFile.getBytes());
        }
        this.questionService.save(question);
        return "redirect:/questions?message=Successful!";
    }


    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getImage(@PathVariable("id") Long id) {
        Question question = this.questionService.getOne(id);
        return question.getFile();
    }
}
