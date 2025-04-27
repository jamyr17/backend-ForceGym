package una.force_gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import una.force_gym.domain.RoutineExercise;

import java.util.List;

public interface RoutineExerciseRepository extends JpaRepository<RoutineExercise, Long> {

    // Obtener todos los ejercicios de una rutina
    List<RoutineExercise> findByRoutine_IdRoutine(Long idRoutine);

    // Eliminar ejercicios de una rutina específica
    @Transactional
    @Modifying
    @Query("DELETE FROM RoutineExercise re WHERE re.routine.idRoutine = :idRoutine")
    void deleteByRoutineId(Long idRoutine);
}
