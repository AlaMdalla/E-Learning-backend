package tn.esprit.pidev.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/all")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // Récupérer une question par ID
    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int questionId) {
        Question question = questionService.getQuestionById(questionId);
        return question != null ? ResponseEntity.ok(question) : ResponseEntity.notFound().build();
    }

    // Ajouter une nouvelle question
    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question newQuestion = questionService.addQuestion(question);
        return ResponseEntity.ok(newQuestion);
    }

    // Modifier une question
    @PutMapping("/update/{questionId}")
    public ResponseEntity<Question> updateQuestion(@PathVariable int questionId, @RequestBody Question question) {
        Question updatedQuestion = questionService.updateQuestion(questionId, question);
        return updatedQuestion != null ? ResponseEntity.ok(updatedQuestion) : ResponseEntity.notFound().build();
    }

    // Supprimer une question
    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable int questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    } }
