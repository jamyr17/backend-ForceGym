package una.force_gym.dto;

public class ExerciseCategoryDTO {

    private Long idExerciseCategory;
    private String name;
    private Long isDeleted;
    private Long paramLoggedIdUser;

    public ExerciseCategoryDTO() {
    }

    public ExerciseCategoryDTO(Long idExerciseCategory, String name, Long isDeleted, Long paramLoggedIdUser) {
        this.idExerciseCategory = idExerciseCategory;
        this.name = name;
        this.isDeleted = isDeleted;
        this.paramLoggedIdUser = paramLoggedIdUser;
    }

    public Long getIdExerciseCategory() {
        return idExerciseCategory;
    }

    public void setIdExerciseCategory(Long idExerciseCategory) {
        this.idExerciseCategory = idExerciseCategory;
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
