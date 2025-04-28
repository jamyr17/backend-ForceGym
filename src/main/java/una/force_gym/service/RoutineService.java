package una.force_gym.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import una.force_gym.domain.Routine;
import una.force_gym.dto.RoutineAssignmentDTO;
import una.force_gym.dto.RoutineExerciseDTO;
import una.force_gym.repository.RoutineRepository;

@Service
public class RoutineService {

    @Autowired
    private RoutineRepository routineRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public int addRoutine(String name, Date date, Long idUser, Long createdByUser, List<RoutineExerciseDTO> exercises, List<RoutineAssignmentDTO> assignments) {
        return routineRepo.addRoutine(name, date, idUser, createdByUser, exercises, assignments);
    }

    public Map<String, Object> getRoutines(
            int page,
            int size,
            int searchType,
            String searchTerm,
            String orderBy,
            String directionOrderBy,
            String filterByStatus,
            Integer filterByRoutineType
    ) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("prGetRoutine", Routine.class);

        // Registrar parámetros de entrada
        query.registerStoredProcedureParameter("p_page", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_limit", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_searchType", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_searchTerm", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_orderBy", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_directionOrderBy", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_filterByStatus", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_filterByRoutineType", Integer.class, ParameterMode.IN);

        // Registrar parámetro de salida
        query.registerStoredProcedureParameter("p_totalRecords", Integer.class, ParameterMode.OUT);

        // Setear valores
        query.setParameter("p_page", page);
        query.setParameter("p_limit", size);
        query.setParameter("p_searchType", searchType);
        query.setParameter("p_searchTerm", searchTerm);
        query.setParameter("p_orderBy", orderBy);
        query.setParameter("p_directionOrderBy", directionOrderBy);
        query.setParameter("p_filterByStatus", filterByStatus);
        query.setParameter("p_filterByRoutineType", filterByRoutineType);

        // Ejecutar procedimiento
        query.execute();

        // Obtener los resultados
        List<?> rawResults = query.getResultList();
        List<Routine> routines = rawResults.stream()
                .filter(Routine.class::isInstance)
                .map(Routine.class::cast)
                .collect(Collectors.toList());
        Integer totalRecords = (Integer) query.getOutputParameterValue("p_totalRecords");

        // Mapear respuesta
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("routines", routines);
        responseData.put("totalRecords", totalRecords);

        return responseData;
    }

}
