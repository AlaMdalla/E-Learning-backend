package tn.esprit.pidev.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        @GetMapping("/retrieve-all-trainings")
        public List<Training> getAllTrainings() {
            return trainingServiceImp.getAllTrainings();
        }

        @GetMapping("/{idTraining}")
        public Training getTrainingById(@PathVariable int idTraining) {
            return trainingServiceImp.getTrainingById(idTraining);
        }

    @PostMapping("/add-training")
    public ResponseEntity<Training> addTraining(@RequestBody Training training) {
        Training createdTraining = trainingServiceImp.saveTraining(training);
        return new ResponseEntity<>(createdTraining, HttpStatus.CREATED);
    }

    @PutMapping("/modify-training")
    public Training modifyTraining(@RequestBody Training training) {
        return trainingServiceImp.saveTraining(training);
    }

    // DELETE training
    @DeleteMapping("/remove-training/{training-id}")
    public void removeTraining(@PathVariable("training-id") int id) {
        trainingServiceImp.deleteTraining(id);
    }}
