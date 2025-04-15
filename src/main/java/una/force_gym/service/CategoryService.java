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
import una.force_gym.domain.Category;
import una.force_gym.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public Map<String, Object> getCategories(
            int page,
            int size,
            int searchType,
            String searchTerm,
            String orderBy,
            String directionOrderBy,
            String filterByStatus
    ) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("prGetCategory", Category.class);

        // Parámetros de entrada
        query.registerStoredProcedureParameter("p_page", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_limit", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_searchType", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_searchTerm", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_orderBy", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_directionOrderBy", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_filterByStatus", String.class, ParameterMode.IN);

        // Parámetro de salida
        query.registerStoredProcedureParameter("p_totalRecords", Integer.class, ParameterMode.OUT);

        // Setear parámetros
        query.setParameter("p_page", page);
        query.setParameter("p_limit", size);
        query.setParameter("p_searchType", searchType);
        query.setParameter("p_searchTerm", searchTerm);
        query.setParameter("p_orderBy", orderBy);
        query.setParameter("p_directionOrderBy", directionOrderBy);
        query.setParameter("p_filterByStatus", filterByStatus);

        // Ejecutar
        query.execute();

        // Procesar resultados
        List<?> rawResults = query.getResultList();
        List<Category> categories = rawResults.stream()
                .filter(Category.class::isInstance)
                .map(Category.class::cast)
                .collect(Collectors.toList());

        Integer totalRecords = (Integer) query.getOutputParameterValue("p_totalRecords");

        // Preparar respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories);
        response.put("totalRecords", totalRecords);

        return response;
    }

    @Transactional
    public List<Category> getCategory() {
        return categoryRepo.getCategory();
    }

    @Transactional
    public int addCategory(String name, Long pLoggedIdUser) {
        return categoryRepo.addCategory(name, pLoggedIdUser);
    }

    @Transactional
    public int updateCategory(Long idCategory, String name, Long isDeleted, Long pLoggedIdUser) {
        return categoryRepo.updateCategory(idCategory, name, isDeleted, pLoggedIdUser);
    }

    @Transactional
    public int deleteCategory(Long idCategory, Long pLoggedIdUser) {
        return categoryRepo.deleteCategory(idCategory, pLoggedIdUser);
    }
}
