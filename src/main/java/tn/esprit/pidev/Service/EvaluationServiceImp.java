package tn.esprit.pidev.Service;

import org.springframework.stereotype.Service;
import tn.esprit.pidev.Entity.Evaluation;
import tn.esprit.pidev.Repository.EvaluationRepository;

import java.util.List;
@Service
public class EvaluationServiceImp implements IEvaluationService{

    private final EvaluationRepository evaluationRepository;

    public EvaluationServiceImp(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }


    @Override
    public Evaluation addEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public Evaluation updateEvaluation(int idEvaluation, Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public void deleteEvaluation(int idEvaluation) {
        evaluationRepository.deleteById(idEvaluation);

    }

    @Override
    public Evaluation getEvaluationById(int idEvaluation) {
        return evaluationRepository.findById(idEvaluation).orElse(null);
    }

    @Override
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }
}
