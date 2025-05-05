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
import una.force_gym.domain.NotificationTemplate;
import una.force_gym.repository.NotificationTemplateRepository;

@Service
public class NotificationTemplateService {

    @Autowired
    private NotificationTemplateRepository notificationTemplateRepo;


    @PersistenceContext
    private EntityManager entityManager;

    public Map<String, Object> getNotificationTemplates(
        int page, 
        int size, int searchType, 
        String searchTerm, 
        String orderBy, 
        String directionOrderBy, 
        String filterByStatus,
        int filterByNotificationType
    ) {
            
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("prGetNotificationTemplate", NotificationTemplate.class);
        
        // Parámetros de entrada
        query.registerStoredProcedureParameter("p_page", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_limit", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_searchType", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_searchTerm", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_orderBy", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_directionOrderBy", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_filterByStatus", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_filterByNotificationType", Integer.class, ParameterMode.IN);

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
        query.setParameter("p_filterByNotificationType", filterByNotificationType);

        // Ejecutar procedimiento
        query.execute();

        // Obtener los resultados
        List<?> rawResults = query.getResultList();
        List<NotificationTemplate> notificationTemplates = rawResults.stream()
            .filter(NotificationTemplate.class::isInstance) 
            .map(NotificationTemplate.class::cast)         
            .collect(Collectors.toList());
        Integer totalRecords = (Integer) query.getOutputParameterValue("p_totalRecords");

        // Mapear respuesta
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("notificationTemplates", notificationTemplates);
        responseData.put("totalRecords", totalRecords);
        
        return responseData;
    }
    @Transactional
    public int addNotificationTemplate( Long pIdNotificationType, 
                                        Long pIdUser, 
                                        String pMessage, 
                                        Long pLoggedIdUser) {
        return notificationTemplateRepo.addNotificationTemplate(pIdNotificationType, 
                                                                pLoggedIdUser, 
                                                                pMessage,
                                                                pLoggedIdUser);
    }

    @Transactional
    public int updateNotificationTemplate(  Long pIdNotificationTemplate, 
                                            Long pIdNotificationType, 
                                            Long pIdUser, 
                                            String pMessage,
                                            Long pIsDeleted,
                                            Long pLoggedIdUser) {
        return notificationTemplateRepo.updateNotificationTemplate( pIdNotificationTemplate, 
                                                                    pIdNotificationType, 
                                                                    pIdUser, 
                                                                    pMessage,
                                                                    pIsDeleted,
                                                                    pLoggedIdUser);
    }

    @Transactional
    public int deleteNotificationTemplate(  Long pIdNotificationTemplate, 
                                            Long pLoggedIdUser){
        return notificationTemplateRepo.deleteNotificationTemplate( pIdNotificationTemplate, 
                                                                    pLoggedIdUser);
    }
}
