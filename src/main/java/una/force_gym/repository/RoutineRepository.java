package una.force_gym.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import una.force_gym.domain.Routine;
import una.force_gym.dto.RoutineAssignmentDTO;
import una.force_gym.dto.RoutineExerciseDTO;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    @Procedure(procedureName = "prInsertRoutine", outputParameterName = "result")
    int addRoutine(
            @Param("pName") String pName,
            @Param("pDate") Date pDate,
            @Param("pIdUser") Long pIdUser,
            @Param("pLoggedIdUser") Long pLoggedIdUser,
            @Param("pExercises") List<RoutineExerciseDTO> pExercises,
            @Param("pAssignments") List<RoutineAssignmentDTO> pAssignments
    );

}
