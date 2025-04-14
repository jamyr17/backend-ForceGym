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
import una.force_gym.domain.ClientType;
import una.force_gym.repository.ClientTypeRepository;

@Service
public class ClientTypeService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClientTypeRepository clientTypeRepo;

    public Map<String, Object> getClientTypes(
        int page,
        int size,
        int searchType,
        String searchTerm,
        String orderBy,
        String directionOrderBy,
        String filterByStatus
    ) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("prGetClientType", ClientType.class);

        // Parámetros de entrada
        query.registerStoredProcedureParameter("p_page", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_limit", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_searchType", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_searchTerm", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_orderBy", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_directionOrderBy", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_filterByStatus", String.class, ParameterMode.IN);

        // Salida
        query.registerStoredProcedureParameter("p_totalRecords", Integer.class, ParameterMode.OUT);

        // Seteo de parámetros
        query.setParameter("p_page", page);
        query.setParameter("p_limit", size);
        query.setParameter("p_searchType", searchType);
        query.setParameter("p_searchTerm", searchTerm);
        query.setParameter("p_orderBy", orderBy);
        query.setParameter("p_directionOrderBy", directionOrderBy);
        query.setParameter("p_filterByStatus", filterByStatus);

        query.execute();

        List<?> rawResults = query.getResultList();
        List<ClientType> clientTypes = rawResults.stream()
            .filter(ClientType.class::isInstance)
            .map(ClientType.class::cast)
            .collect(Collectors.toList());

        Integer totalRecords = (Integer) query.getOutputParameterValue("p_totalRecords");

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("clientTypes", clientTypes);
        responseData.put("totalRecords", totalRecords);

        return responseData;
    }

    @Transactional
    public int addClientType(String pName, Long pLoggedIdUser) {
        return clientTypeRepo.addClientType(pName, pLoggedIdUser);
    }

    @Transactional
    public int updateClientType(Long pIdClientType, String pName, Long pIsDeleted, Long pLoggedIdUser) {
        return clientTypeRepo.updateClientType(pIdClientType, pName, pIsDeleted, pLoggedIdUser);
    }

    @Transactional
    public int deleteClientType(Long pIdClientType, Long pLoggedIdUser){
        return clientTypeRepo.deleteClientType(pIdClientType, pLoggedIdUser);
    }
    
}
