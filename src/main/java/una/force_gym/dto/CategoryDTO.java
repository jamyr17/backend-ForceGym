package una.force_gym.dto;

public class CategoryDTO {

    private Long idCategory;
    private String name;
    private Long isDeleted;
    private Long paramLoggedIdUser;

    public CategoryDTO() {
    }

    public CategoryDTO(Long idCategory, String name, Long isDeleted, Long paramLoggedIdUser) {
        this.idCategory = idCategory;
        this.name = name;
        this.isDeleted = isDeleted;
        this.paramLoggedIdUser = paramLoggedIdUser;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
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
