package una.force_gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import una.force_gym.domain.Routine;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    @Procedure(procedureName = "prGetRoutine")
    List<Routine> getRoutine();

    boolean existsByNameAndUser_IdUserAndIsDeleted(String name, Long userId, Long isDeleted);

    boolean existsByNameAndUser_IdUserAndIsDeletedAndIdRoutineNot(String name, Long userId, Long isDeleted, Long routineId);

    // Nuevo método para buscar rutinas asignadas a un cliente
    @Query("SELECT r FROM tbRoutine r JOIN r.assignments a WHERE a.client.idClient = :clientId AND r.isDeleted = 0")
    List<Routine> findAssignedToClient(Long clientId);
}
