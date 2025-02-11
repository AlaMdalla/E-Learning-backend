package tn.esprit.pidev.Service;

import org.springframework.stereotype.Service;
import tn.esprit.pidev.Entity.Training;
import tn.esprit.pidev.Repository.TrainingRepository;

import java.util.List;

@Service
public class TrainingServiceImp implements ITrainingService
{
    private final TrainingRepository trainingRepository;

    public TrainingServiceImp(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }


    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public Training getTrainingById(Long id) {
        return null;
    }

    public Training getTrainingById(int idTraining) {
        return trainingRepository.findById(idTraining).orElse(null);
    }

    public Training saveTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public void deleteTraining(Long id) {

    }

    public void deleteTraining(int idTraining) {
        trainingRepository.deleteById(idTraining);
    }
}
