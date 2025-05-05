package una.force_gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import una.force_gym.domain.ExerciseCategory;

public interface ExerciseCategoryRepository extends JpaRepository<ExerciseCategory, Long> {

    @Procedure(procedureName = "prGetAllExerciseCategory")
    List<ExerciseCategory> getExercisesCategories();

    @Procedure(procedureName = "prInsertExerciseCategory", outputParameterName = "result")
    int addExerciseCategory(
        @Param("pName") String name,
        @Param("pLoggedIdUser") Long loggedIdUser
    );

    @Procedure(procedureName = "prUpdateExerciseCategory", outputParameterName = "result")
    int updateExerciseCategory(
        @Param("pIdExerciseCategory") Long idExerciseCategory,
        @Param("pName") String name,
        @Param("pIsDeleted") Long pIsDeleted,  
        @Param("pLoggedIdUser") Long loggedIdUser
    );

    @Procedure(procedureName = "prDeleteExerciseCategory", outputParameterName = "result")
    int deleteExerciseCategory(
        @Param("pIdExerciseCategory") Long idExerciseCategory,
        @Param("pLoggedIdUser") Long loggedIdUser
    );

}
