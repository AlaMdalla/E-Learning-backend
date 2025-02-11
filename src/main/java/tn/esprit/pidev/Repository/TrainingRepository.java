package tn.esprit.pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidev.Entity.Training;

public interface TrainingRepository extends JpaRepository <Training, Integer> {

}
