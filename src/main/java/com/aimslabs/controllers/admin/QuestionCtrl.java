package com.aimslabs.controllers.admin;

import com.aimslabs.commons.ImageValidator;
import com.aimslabs.domains.Question;
import com.aimslabs.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by sayemkcn on 11/9/16.
 */
@Controller
@RequestMapping(value = "/admin/questions")
public class QuestionCtrl {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("file");
    }


    @Autowired
    private QuestionService questionService;
    @Autowired
    ImageValidator imageValidator;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allQuestions(Model model) {
        model.addAttribute("questionList", this.questionService.getAll());
        return "questions/all";
    }

    // ----- CREATE   -----//
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPage() {
        return "questions/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createQuestion(@ModelAttribute Question question, BindingResult bindingResult,
                                 @RequestParam("file") MultipartFile multipartFile) throws IOException {
        if (bindingResult.hasErrors())
            System.out.println(bindingResult.toString());
        if (imageValidator.isImageValid(multipartFile)) {
            question.setFile(multipartFile.getBytes());
        }
        this.questionService.save(question);
        return "redirect:/admin/questions?message=Successful!";
    }

    // -------- UPDATE -------//
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    private String updateQuestionPage(@PathVariable("id") Long id, Model model) {
        Question question = this.questionService.getOne(id);
        model.addAttribute("question", question);
        return "questions/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    private String updateQuestion(@ModelAttribute Question question, BindingResult bindingResult,
                                  @RequestParam("file") MultipartFile multipartFile,
                                  @PathVariable("id") Long id) throws IOException {
        if (bindingResult.hasErrors())
            System.out.println(bindingResult.toString());

        Question existingQuestion = this.questionService.getOne(id);
        if (!multipartFile.isEmpty()) {
            if (imageValidator.isImageValid(multipartFile))
                existingQuestion.setFile(multipartFile.getBytes());
            else
                return "redirect:/admin/questions/update/" + id + "?message=file is not valid!";
        }
        // The reason for copying new entity to existing entity is, when updating entity created date will be saved null because @PrePersist will not be executed this time.
        existingQuestion.setTitle(question.getTitle());
        existingQuestion.setQuestionId(question.getQuestionId());
        existingQuestion.setPositiveText(question.getPositiveText());
        existingQuestion.setNegativeText(question.getNegativeText());
        existingQuestion.setCritical(question.isCritical());
        existingQuestion.setAutismDetectedForPositiveText(question.isAutismDetectedForPositiveText());
        this.questionService.save(existingQuestion);
        return "redirect:/admin/questions?message=Successfully updated question " + existingQuestion.getQuestionId();
    }

    // ------ DELETE -----//
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    private String deleteQuestion(@PathVariable("id") Long id) {
        this.questionService.delete(id);
        return "redirect:/admin/questions?message=Successfully deleted question.";
    }

    // ------ IMAGE -----//
    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getImage(@PathVariable("id") Long id) {
        Question question = this.questionService.getOne(id);
        return question.getFile();
    }
}
