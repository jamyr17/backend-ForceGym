package una.force_gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import una.force_gym.domain.ExerciseDifficulty;

public interface ExerciseDifficultyRepository extends JpaRepository<ExerciseDifficulty, Long> {

    @Procedure(procedureName = "prGetExerciseDifficulty")
    List<ExerciseDifficulty> getExercisesDifficulties();

}
