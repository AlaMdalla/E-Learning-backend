package tn.esprit.pidev.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.Entity.Evaluation;
import tn.esprit.pidev.Service.IEvaluationService;

import java.util.List;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    private final IEvaluationService evaluationService;

    public EvaluationController(IEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    // GET ALL Evaluations
    @GetMapping("/retrieve-all")
    public ResponseEntity<List<Evaluation>> getAllEvaluations() {
        return new ResponseEntity<>(evaluationService.getAllEvaluations(), HttpStatus.OK);
    }

    // GET Evaluation by ID
    @GetMapping("/retrieve/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable("id") int idEvaluation) {
        Evaluation evaluation = evaluationService.getEvaluationById(idEvaluation);
        if (evaluation != null) {
            return new ResponseEntity<>(evaluation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ADD Evaluation
    @PostMapping("/add")
    public ResponseEntity<Evaluation> addEvaluation(@RequestBody Evaluation evaluation) {
        return new ResponseEntity<>(evaluationService.addEvaluation(evaluation), HttpStatus.CREATED);
    }

    // UPDATE Evaluation
    @PutMapping("/update/{idEvaluation}")
    public ResponseEntity<Evaluation> updateEvaluation(@PathVariable("idEvaluation") int idEvaluation, @RequestBody Evaluation evaluation) {
        Evaluation updatedEvaluation = evaluationService.updateEvaluation(idEvaluation, evaluation);
        if (updatedEvaluation != null) {
            return new ResponseEntity<>(updatedEvaluation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE Evaluation
    @DeleteMapping("/delete/{idEvaluation}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable("idEvaluation") int idEvaluation) {
        evaluationService.deleteEvaluation(idEvaluation);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



