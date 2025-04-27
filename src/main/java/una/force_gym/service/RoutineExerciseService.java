package una.force_gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import una.force_gym.domain.RoutineExercise;
import una.force_gym.repository.RoutineExerciseRepository;

@Service
public class RoutineExerciseService {

    @Autowired
    private RoutineExerciseRepository routineExerciseRepo;

    @Transactional
    public List<RoutineExercise> getByRoutineId(Long idRoutine) {
        return routineExerciseRepo.findByRoutine_IdRoutine(idRoutine);
    }

    @Transactional
    public void deleteByRoutineId(Long idRoutine) {
        routineExerciseRepo.deleteByRoutineId(idRoutine);
    }

    @Transactional
    public RoutineExercise save(RoutineExercise exercise) {
        return routineExerciseRepo.save(exercise);
    }
}
