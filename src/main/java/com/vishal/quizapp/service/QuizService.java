package com.vishal.quizapp.service;


import com.vishal.quizapp.dao.QuestionDao;
import com.vishal.quizapp.dao.QuizDao;
import com.vishal.quizapp.model.Question;
import com.vishal.quizapp.model.QuestionWrapper;
import com.vishal.quizapp.model.Quiz;
import com.vishal.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Quiz quiz = quizDao.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Quiz not found with id " + id
                ));
        List<Question> questionsFromDB = quiz.getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        return ResponseEntity.ok(questionsForUser);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found with id " + id));

        List<Question> questions = quiz.getQuestions();
        int right = 0;

        // responses aur questions ko id ke basis pe match karenge
        for (Response response : responses) {
            if (response.getResponse() != null) { // null check
                for (Question q : questions) {
                    if (q.getId() == response.getId()) { // id match
                        if (response.getResponse().equals(q.getRightAnswer())) { // right answer match
                            right++;
                        }
                        break; // ek question ke liye sirf ek response check
                    }
                }
            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}

