package una.force_gym.dto;

public class ClientTypeDTO {

    private Long idClientType;
    private String name;
    private Long isDeleted;
    private Long paramLoggedIdUser;

    public ClientTypeDTO() {}

    public ClientTypeDTO(Long idClientType, String name, Long isDeleted, Long paramLoggedIdUser) {
        this.idClientType = idClientType;
        this.name = name;
        this.isDeleted = isDeleted;
        this.paramLoggedIdUser = paramLoggedIdUser;
    }

    public Long getIdClientType() {
        return idClientType;
    }

    public void setIdClientType(Long idClientType) {
        this.idClientType = idClientType;
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
