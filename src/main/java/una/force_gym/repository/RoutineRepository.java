package una.force_gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import una.force_gym.domain.Routine;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {

    @Procedure(procedureName = "prGetRoutine")
    List<Routine> getRoutine();
}
