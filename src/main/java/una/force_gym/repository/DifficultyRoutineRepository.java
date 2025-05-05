package una.force_gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import una.force_gym.domain.DifficultyRoutine;

public interface DifficultyRoutineRepository extends JpaRepository<DifficultyRoutine, Long> {

    @Procedure(procedureName = "prGetDifficultyRoutine")
    List<DifficultyRoutine> getDifficultyRoutine();

}
