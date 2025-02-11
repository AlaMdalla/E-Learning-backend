package tn.esprit.pidev.Service;

import tn.esprit.pidev.Entity.Evaluation;

import java.util.List;

public interface IEvaluationService {
    Evaluation addEvaluation(Evaluation evaluation);
    Evaluation updateEvaluation(int id, Evaluation evaluation);
    void deleteEvaluation(int id);
    Evaluation getEvaluationById(int id);
    List<Evaluation> getAllEvaluations();
}
