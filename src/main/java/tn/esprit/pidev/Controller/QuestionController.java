package tn.esprit.pidev.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.Entity.Question;
import tn.esprit.pidev.Service.IQuestionService;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private final IQuestionService questionService;

    public QuestionController(IQuestionService questionService) {
        this.questionService = questionService;
    }

    // Récupérer toutes les questions
    @GetMapping("/retrieve-all-questions")
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    // Récupérer une question par ID
    @GetMapping("/retrieve-question/{question-id}")
    public ResponseEntity<Question> retrieveQuestion(@PathVariable("question-id") int id) {
        Question question = questionService.getQuestionById(id);
        return (question != null) ? new ResponseEntity<>(question, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question createdQuestion = questionService.addQuestion(question);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    // Modifier une question
    @PutMapping("/modify-question/{question-id}")
    public ResponseEntity<Question> modifyQuestion(@PathVariable("question-id") int id, @RequestBody Question question) {
        Question updatedQuestion = questionService.updateQuestion(id, question);
        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    }

    // Supprimer une question
    @DeleteMapping("/remove-question/{question-id}")
    public ResponseEntity<Void> removeQuestion(@PathVariable("question-id") int id) {
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }}
