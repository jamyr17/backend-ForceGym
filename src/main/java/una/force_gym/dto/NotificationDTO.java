package una.force_gym.dto;

import java.util.Date;

public class NotificationDTO {
    
    private Long idNotification;
    private Long idClient;
    private Long idNotificationType;
    private Date sendDate;
    private Long isDeleted;
    private Long paramLoggedIdUser;

    public NotificationDTO() {}

    public NotificationDTO(Long idNotification, Long idClient, Long idNotificationType, Date sendDate, Long isDeleted,
            Long paramLoggedIdUser) {
        this.idNotification = idNotification;
        this.idClient = idClient;
        this.idNotificationType = idNotificationType;
        this.sendDate = sendDate;
        this.isDeleted = isDeleted;
        this.paramLoggedIdUser = paramLoggedIdUser;
    }

    public Long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdNotificationType() {
        return idNotificationType;
    }

    public void setIdNotificationType(Long idNotificationType) {
        this.idNotificationType = idNotificationType;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
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
