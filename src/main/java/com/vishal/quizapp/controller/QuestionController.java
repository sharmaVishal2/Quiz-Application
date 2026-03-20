package com.vishal.quizapp.controller;

import com.vishal.quizapp.model.Question;
import com.vishal.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>>getAllQuestions() {
        return questionService.getAllQuestions();
    }

     @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category).getBody();
     }

     @PostMapping("add")
     public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
     }
     @DeleteMapping("delete")
     public ResponseEntity<String> deleteQuestion(@RequestBody Question question) {
         return questionService.deleteQuestionById();
     }



}
