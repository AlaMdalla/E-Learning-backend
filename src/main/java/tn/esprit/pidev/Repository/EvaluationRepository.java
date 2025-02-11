package tn.esprit.pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidev.Entity.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
}
