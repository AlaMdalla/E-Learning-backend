package tn.esprit.pidev.Service;

import org.springframework.stereotype.Service;
import tn.esprit.pidev.Entity.Question;
import tn.esprit.pidev.Repository.QuestionRepository;
import tn.esprit.pidev.Repository.TrainingRepository;

import java.util.List;
@Service
public class QuestionServiceImp implements IQuestionService{
    private final QuestionRepository questionRepository;

    public QuestionServiceImp(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(int questionId, Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(int questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public Question getQuestionById(int questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
