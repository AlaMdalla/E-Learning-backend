package tn.esprit.pidev.Service;

import tn.esprit.pidev.Entity.Training;

import java.util.List;

public interface ITrainingService
{ List<Training> getAllTrainings();
    Training getTrainingById(Long id);
    Training saveTraining(Training training);
    void deleteTraining(Long id);
}
