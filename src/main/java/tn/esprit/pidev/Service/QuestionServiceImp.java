package tn.esprit.pidev.Service;

import org.springframework.stereotype.Service;
import tn.esprit.pidev.Entity.Question;

import java.util.List;
@Service
public class QuestionServiceImp implements IQuestionService{
    @Override
    public Question addQuestion(Question question) {
        return null;
    }

    @Override
    public Question updateQuestion(int questionId, Question question) {
        return null;
    }

    @Override
    public void deleteQuestion(int questionId) {

    }

    @Override
    public Question getQuestionById(int questionId) {
        return null;
    }

    @Override
    public List<Question> getAllQuestions() {
        return List.of();
    }
}
