package una.force_gym.dto;

public class NotificationTemplateDTO {

    private Long idNotificationTemplate;
    private Long idNotificationType;
    private Long idUser;
    private String message;
    private Long isDeleted;
    private Long paramLoggedIdUser;

    public NotificationTemplateDTO() {}

    public NotificationTemplateDTO(Long idNotificationTemplate, Long idNotificationType, Long idUser, String message,
            Long isDeleted, Long paramLoggedIdUser) {
        this.idNotificationTemplate = idNotificationTemplate;
        this.idNotificationType = idNotificationType;
        this.idUser = idUser;
        this.message = message;
        this.isDeleted = isDeleted;
        this.paramLoggedIdUser = paramLoggedIdUser;
    }

    public Long getIdNotificationTemplate() {
        return idNotificationTemplate;
    }

    public void setIdNotificationTemplate(Long idNotificationTemplate) {
        this.idNotificationTemplate = idNotificationTemplate;
    }

    public Long getIdNotificationType() {
        return idNotificationType;
    }

    public void setIdNotificationType(Long idNotificationType) {
        this.idNotificationType = idNotificationType;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getParamLoggedIdUser() {
        return paramLoggedIdUser;
    }

    public void setParamLoggedIdUser(Long paramLoggedIdUser) {
        this.paramLoggedIdUser = paramLoggedIdUser;
    }
    
}