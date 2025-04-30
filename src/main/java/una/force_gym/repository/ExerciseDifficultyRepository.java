package una.force_gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import una.force_gym.domain.ExerciseCategory;

public interface ExerciseDifficultyRepository extends JpaRepository<ExerciseCategory, Long> {

    @Procedure(procedureName = "prGetExerciseCategory")
    List<ExerciseCategory> getExercisesCategories();

}
