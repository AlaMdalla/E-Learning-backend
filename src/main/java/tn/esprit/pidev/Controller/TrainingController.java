package tn.esprit.pidev.Controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.Entity.Training;
import tn.esprit.pidev.Service.TrainingServiceImp;

import java.util.List;
@RestController
@RequestMapping("/trainings")
public class TrainingController {
        private final TrainingServiceImp trainingServiceImp;

        public TrainingController(TrainingServiceImp trainingServiceImp) {
            this.trainingServiceImp = trainingServiceImp;
        }

        @GetMapping
        public List<Training> getAllTrainings() {
            return trainingServiceImp.getAllTrainings();
        }

        @GetMapping("/{id}")
        public Training getTrainingById(@PathVariable int idTraining) {
            return trainingServiceImp.getTrainingById(idTraining);
        }

        @PostMapping
        public Training createTraining(@RequestBody Training training) {
            return trainingServiceImp.saveTraining(training);
        }

        @DeleteMapping("/{id}")
        public void deleteTraining(@PathVariable int idTraining) {
            trainingServiceImp.deleteTraining(idTraining);
        }
}
