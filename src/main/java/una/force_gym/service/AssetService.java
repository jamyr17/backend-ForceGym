package una.force_gym.service;

import java.time.LocalDate;
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
import una.force_gym.domain.Asset;
import una.force_gym.repository.AssetRepository;
import una.force_gym.util.StraightLineDeprecation;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepo;
    
    @PersistenceContext
    private EntityManager entityManager;

    public Map<String, Object> getAssets(
        int page, 
        int size, int searchType, 
        String searchTerm, 
        String orderBy, 
        String directionOrderBy, 
        String filterByStatus,
        Long filterByCostRangeMin,
        Long filterByCostRangeMax,
        Long filterByQuantityRangeMin,
        Long filterByQuantityRangeMax
    ) {
            
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("prGetAsset", Asset.class);
        
        // Parámetros de entrada
        query.registerStoredProcedureParameter("p_page", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_limit", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_searchType", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_searchTerm", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_orderBy", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_directionOrderBy", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_filterByStatus", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_filterByCostRangeMin", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_filterByCostRangeMax", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_filterByQuantityRangeMin", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_filterByQuantityRangeMax", Long.class, ParameterMode.IN);

        // Parámetro de salida
        query.registerStoredProcedureParameter("p_totalRecords", Integer.class, ParameterMode.OUT);

        // Setear valores
        query.setParameter("p_page", page);
        query.setParameter("p_limit", size);
        query.setParameter("p_searchType", searchType);
        query.setParameter("p_searchTerm", searchTerm);
        query.setParameter("p_orderBy", orderBy);
        query.setParameter("p_directionOrderBy", directionOrderBy);
        query.setParameter("p_filterByStatus", filterByStatus);
        query.setParameter("p_filterByCostRangeMin", filterByCostRangeMin);
        query.setParameter("p_filterByCostRangeMax", filterByCostRangeMax);
        query.setParameter("p_filterByQuantityRangeMax", filterByQuantityRangeMax);
        query.setParameter("p_filterByQuantityRangeMin", filterByQuantityRangeMin);

        // Ejecutar procedimiento
        query.execute();

        // Obtener los resultados
        List<?> rawResults = query.getResultList();
        List<Asset> assets = rawResults.stream()
            .filter(Asset.class::isInstance) 
            .map(Asset.class::cast)         
            .collect(Collectors.toList());
        Integer totalRecords = (Integer) query.getOutputParameterValue("p_totalRecords");

        // Mapear respuesta
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("assets", assets);
        responseData.put("totalRecords", totalRecords);
        
        return responseData;
    }

    @Transactional
    public int addAsset(Long pIdUser, LocalDate boughtDate, String code, String name, int quantity, Float initialCost, int serviceLifeYears, Long pLoggedIdUser){
        Float deprecationPerYear = StraightLineDeprecation.calculate(initialCost, serviceLifeYears);


        return assetRepo.addAsset(pIdUser, boughtDate, code, name, quantity, initialCost, serviceLifeYears, deprecationPerYear, pLoggedIdUser);
    }

    @Transactional
    public int updateAsset(Long pIdAsset, Long pIdUser, LocalDate boughtDate, String code, String name, int quantity, Float initialCost, int serviceLifeYears, Long pIsDeleted, Long pLoggedIdUser){
        Float deprecationPerYear = StraightLineDeprecation.calculate(initialCost, serviceLifeYears);

        return assetRepo.updateAsset(pIdAsset, pIdUser, boughtDate, code, name, quantity, initialCost, serviceLifeYears, deprecationPerYear, pIsDeleted, pLoggedIdUser);
    }

    @Transactional
    public int deleteAsset(Long pIdAsset, Long pLoggedIdUser){
        return assetRepo.deleteAsset(pIdAsset, pLoggedIdUser);
    }
    
}
