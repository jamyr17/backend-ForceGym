package una.force_gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import una.force_gym.domain.DifficultyRoutine;
import una.force_gym.repository.DifficultyRoutineRepository;

@Service
public class DifficultyRoutineService {

    @Autowired
    private DifficultyRoutineRepository difficultyRoutineRepo;

    @Transactional
    public List<DifficultyRoutine> getDifficultyRoutines() {
        return difficultyRoutineRepo.getDifficultyRoutine();
    }

}
