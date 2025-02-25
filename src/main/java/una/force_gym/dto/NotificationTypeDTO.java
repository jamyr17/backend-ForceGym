package una.force_gym.dto;

public class NotificationTypeDTO {
    
    private Long idNotificationType;
    private String name;
    private Long isDeleted;
    private Long paramLoggedIdUser;

    public NotificationTypeDTO() {}

    public NotificationTypeDTO(Long idNotificationType, String name, Long isDeleted, Long paramLoggedIdUser) {
        this.idNotificationType = idNotificationType;
        this.name = name;
        this.isDeleted = isDeleted;
        this.paramLoggedIdUser = paramLoggedIdUser;
    }

    public Long getIdNotificationType() {
        return idNotificationType;
    }

    public void setIdNotificationType(Long idNotificationType) {
        this.idNotificationType = idNotificationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
