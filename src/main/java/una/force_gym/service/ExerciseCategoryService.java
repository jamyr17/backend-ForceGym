package una.force_gym.service;

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
import una.force_gym.domain.ExerciseCategory;
import una.force_gym.repository.ExerciseCategoryRepository;

@Service
public class ExerciseCategoryService {

    @Autowired
    private ExerciseCategoryRepository exerciseExerciseCategoryRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<ExerciseCategory> getExercisesCategories(){
        return exerciseExerciseCategoryRepo.getExercisesCategories();
    }

    public Map<String, Object> getExercisesCategoriesList(
        int page, 
        int size,
        String filterByStatus
    ) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("prGetExerciseCategory", ExerciseCategory.class);
        
        // Parámetros de entrada
        query.registerStoredProcedureParameter("p_page", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_limit", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_filterByStatus", String.class, ParameterMode.IN);
        
        // Parámetro de salida
        query.registerStoredProcedureParameter("p_totalRecords", Integer.class, ParameterMode.OUT);

        // Setear valores
        query.setParameter("p_page", page);
        query.setParameter("p_limit", size);
        query.setParameter("p_filterByStatus", filterByStatus);

        // Ejecutar procedimiento
        query.execute();

        // Obtener los resultados
        List<?> rawResults = query.getResultList();
        List<ExerciseCategory> exerciseCategories = rawResults.stream()
            .filter(ExerciseCategory.class::isInstance) 
            .map(ExerciseCategory.class::cast)         
            .collect(Collectors.toList());

        Integer totalRecords = (Integer) query.getOutputParameterValue("p_totalRecords");

        // Mapear respuesta
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("exerciseCategories", exerciseCategories);
        responseData.put("totalRecords", totalRecords);
        
        return responseData;
    }

    @Transactional
    public int addExerciseCategory(String name, Long pLoggedIdUser) {
        return exerciseExerciseCategoryRepo.addExerciseCategory(name, pLoggedIdUser);
    }

    @Transactional
    public int updateExerciseCategory(Long idExerciseCategory, String name, Long isDeleted, Long pLoggedIdUser) {
        return exerciseExerciseCategoryRepo.updateExerciseCategory(idExerciseCategory, name, isDeleted, pLoggedIdUser);
    }

    @Transactional
    public int deleteExerciseCategory(Long idExerciseCategory, Long pLoggedIdUser) {
        return exerciseExerciseCategoryRepo.deleteExerciseCategory(idExerciseCategory, pLoggedIdUser);
    }

}
