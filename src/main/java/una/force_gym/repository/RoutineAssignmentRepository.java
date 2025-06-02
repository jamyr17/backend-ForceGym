package una.force_gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import una.force_gym.domain.RoutineAssignment;

import java.util.List;

public interface RoutineAssignmentRepository extends JpaRepository<RoutineAssignment, Long> {

    List<RoutineAssignment> findByRoutine_IdRoutine(Long routineId);

    @Transactional
    @Modifying
    @Query("DELETE FROM tbRoutineAssignment ra WHERE ra.routine.idRoutine = :routineId")
    void deleteByRoutineId(Long routineId);

    // Para verificar si un cliente ya tiene asignada una rutina activa
    boolean existsByClient_IdClientAndRoutine_IsDeleted(Long clientId, Long isDeleted);

    List<RoutineAssignment> findByClient_IdClient(Long clientId);
}
