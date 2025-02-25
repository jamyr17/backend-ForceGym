package una.force_gym.dto;

public class PermissionDTO {
    
    private Long idPermission;
    private String definition;
    private String description;
    private Long isDeleted;
    private Long paramLoggedIdUser;

    public PermissionDTO() {}

    public PermissionDTO(Long idPermission, String definition, String description, Long isDeleted,
            Long paramLoggedIdUser) {
        this.idPermission = idPermission;
        this.definition = definition;
        this.description = description;
        this.isDeleted = isDeleted;
        this.paramLoggedIdUser = paramLoggedIdUser;
    }

    public Long getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Long idPermission) {
        this.idPermission = idPermission;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
