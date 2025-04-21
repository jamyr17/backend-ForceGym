package una.force_gym.dto;

public class ExerciseDTO {

    private Long idExercise;
    private String name;
    private String description;
    private String difficulty;
    private Long idExerciseCategory;
    private Long idUser;
    private Long isDeleted;
    private Long paramLoggedIdUser;

    public ExerciseDTO() {}

    public ExerciseDTO(Long idExercise, String name, String description, String difficulty, Long idExerciseCategory,
            Long idUser, Long isDeleted, Long paramLoggedIdUser) {
        this.idExercise = idExercise;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.idExerciseCategory = idExerciseCategory;
        this.idUser = idUser;
        this.isDeleted = isDeleted;
        this.paramLoggedIdUser = paramLoggedIdUser;
    }

    public Long getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(Long idExercise) {
        this.idExercise = idExercise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Long getIdExerciseCategory() {
        return idExerciseCategory;
    }

    public void setIdExerciseCategory(Long idExerciseCategory) {
        this.idExerciseCategory = idExerciseCategory;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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