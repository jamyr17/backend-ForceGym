package una.force_gym.dto;

public class ExerciseDTO {

    private Long idExercise;
    private String name;
    private String description;
    private Integer sets;
    private Integer repetitions;
    private Long isDeleted;
    private Long paramLoggedIdUser;

    public ExerciseDTO() {
    }

    public ExerciseDTO(Long idExercise, String name, String description, Integer sets, Integer repetitions, Long isDeleted, Long paramLoggedIdUser) {
        this.idExercise = idExercise;
        this.name = name;
        this.description = description;
        this.sets = sets;
        this.repetitions = repetitions;
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

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
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
