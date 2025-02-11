package tn.esprit.pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidev.Entity.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
}
