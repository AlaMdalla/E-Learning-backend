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


    @Override
    public List<Training> getAllTrainings() {
        return List.of();
    }

    @Override
    public Training getTrainingById(int idTraining) {
        return null;
    }

    @Override
    public Training saveTraining(Training training) {
        return null;
    }

    @Override
    public void deleteTraining(int idTraining) {

    }
}