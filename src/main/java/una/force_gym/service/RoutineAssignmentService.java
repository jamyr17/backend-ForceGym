package una.force_gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import una.force_gym.domain.RoutineAssignment;
import una.force_gym.repository.RoutineAssignmentRepository;

@Service
public class RoutineAssignmentService {

    @Autowired
    private RoutineAssignmentRepository routineAssignmentRepo;

    @Transactional
    public List<RoutineAssignment> getByClientId(Long idClient) {
        return routineAssignmentRepo.findByClient_IdClient(idClient);
    }

    @Transactional
    public void deleteByRoutineId(Long idRoutine) {
        routineAssignmentRepo.deleteByRoutineId(idRoutine);
    }

    @Transactional
    public RoutineAssignment save(RoutineAssignment assignment) {
        return routineAssignmentRepo.save(assignment);
    }
}
