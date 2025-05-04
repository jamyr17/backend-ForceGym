package una.force_gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import una.force_gym.domain.ExerciseDifficulty;
import una.force_gym.repository.ExerciseDifficultyRepository;

@Service
public class ExerciseDifficultyService {

    @Autowired
    private ExerciseDifficultyRepository exerciseDifficultyRepo;

    @Transactional
    public List<ExerciseDifficulty> getExercisesDifficulties(){
        return exerciseDifficultyRepo.getExercisesDifficulties();
    }
}
