package una.force_gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import una.force_gym.domain.RoutineAssignment;

import java.util.List;

public interface RoutineAssignmentRepository extends JpaRepository<RoutineAssignment, Long> {

    // Obtener asignaciones por cliente
    List<RoutineAssignment> findByClient_IdClient(Long idClient);

    // Eliminar todas las asignaciones de una rutina
    @Transactional
    @Modifying
    @Query("DELETE FROM RoutineAssignment ra WHERE ra.routine.idRoutine = :idRoutine")
    void deleteByRoutineId(Long idRoutine);
}
