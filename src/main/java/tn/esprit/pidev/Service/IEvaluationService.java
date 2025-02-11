package tn.esprit.pidev.Service;

import tn.esprit.pidev.Entity.Evaluation;

import java.util.List;

public interface IEvaluationService {
    Evaluation addEvaluation(Evaluation evaluation);
    Evaluation updateEvaluation(int idEvaluation, Evaluation evaluation);
    void deleteEvaluation(int idEvaluation);
    Evaluation getEvaluationById(int idEvaluation);
    List<Evaluation> getAllEvaluations();
}
