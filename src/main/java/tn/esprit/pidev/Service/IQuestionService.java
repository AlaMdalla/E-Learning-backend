package tn.esprit.pidev.Service;

import tn.esprit.pidev.Entity.Question;

import java.util.List;

public interface IQuestionService {

        Question addQuestion(Question question);
        Question updateQuestion(int questionId, Question question);
        void deleteQuestion(int questionId);
        Question getQuestionById(int questionId);
        List<Question> getAllQuestions();
}
