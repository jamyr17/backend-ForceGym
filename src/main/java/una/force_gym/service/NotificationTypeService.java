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
import una.force_gym.domain.NotificationType;
import una.force_gym.repository.NotificationTypeRepository;

@Service
public class NotificationTypeService {
    
    @Autowired
    private NotificationTypeRepository notificationTypeRepo;


    @PersistenceContext
    private EntityManager entityManager;

    public Map<String, Object> getNotificationTypes(
        int page, 
        int size, int searchType, 
        String searchTerm, 
        String orderBy, 
        String directionOrderBy, 
        String filterByStatus
    ) {
            
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("prGetNotificationType", NotificationType.class);
        
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

        // Setear valores
        query.setParameter("p_page", page);
        query.setParameter("p_limit", size);
        query.setParameter("p_searchType", searchType);
        query.setParameter("p_searchTerm", searchTerm);
        query.setParameter("p_orderBy", orderBy);
        query.setParameter("p_directionOrderBy", directionOrderBy);
        query.setParameter("p_filterByStatus", filterByStatus);

        // Ejecutar procedimiento
        query.execute();

        // Obtener los resultados
        List<?> rawResults = query.getResultList();
        List<NotificationType> notificationTypes = rawResults.stream()
            .filter(NotificationType.class::isInstance) 
            .map(NotificationType.class::cast)         
            .collect(Collectors.toList());
        Integer totalRecords = (Integer) query.getOutputParameterValue("p_totalRecords");

        // Mapear respuesta
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("notificationTypes", notificationTypes);
        responseData.put("totalRecords", totalRecords);
        
        return responseData;
    }
    
    @Transactional
    public int addNotificationType( String pName, 
                                    Long pLoggedIdUser){
        return notificationTypeRepo.addNotificationType(pName,  
                                                        pLoggedIdUser);
    }

    @Transactional
    public int updateNotificationType(  Long pIdNotificationType, 
                                        String pName,
                                        Long pIsDeleted,
                                        Long pLoggedIdUser){
        return notificationTypeRepo.updateNotificationType( pIdNotificationType, 
                                                            pName, 
                                                            pIsDeleted,
                                                            pLoggedIdUser);
    }

    @Transactional
    public int deleteNotificationType(Long pIdNotificationType, Long pLoggedIdUser){
        return notificationTypeRepo.deleteNotificationType(pIdNotificationType, pLoggedIdUser);
    }
}
