package una.force_gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import una.force_gym.domain.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Procedure(procedureName = "prGetExercise")
    List<Exercise> getAllExercises();

    @Procedure(procedureName = "prInsertExercise", outputParameterName = "result")
    int addExercise(
            @Param("pName") String name,
            @Param("pDescription") String description,
            @Param("pSets") int sets,
            @Param("pRepetitions") int repetitions,
            @Param("pLoggedIdUser") Long loggedIdUser
    );

}
