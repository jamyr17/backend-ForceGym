package una.force_gym.dto;

import java.util.Date;
import java.util.List;

import una.force_gym.domain.DifficultyRoutine;

public class RoutineWithExercisesDTO {

    private Long idRoutine;
    private String name;
    private Date date;
    private Long idUser;
    private DifficultyRoutine difficultyRoutine;
    private List<RoutineExerciseDTO> exercises;
    private List<RoutineAssignmentDTO> assignments;
    private Integer categoryOrder;
    private Long isDeleted;
    private Long paramLoggedIdUser;

    public RoutineWithExercisesDTO() {
    }

    public RoutineWithExercisesDTO(List<RoutineAssignmentDTO> assignments, Date date, DifficultyRoutine difficultyRoutine, List<RoutineExerciseDTO> exercises, Long idRoutine, Long idUser, Long isDeleted, String name, Long paramLoggedIdUser) {
        this.assignments = assignments;
        this.date = date;
        this.difficultyRoutine = difficultyRoutine;
        this.exercises = exercises;
        this.idRoutine = idRoutine;
        this.idUser = idUser;
        this.isDeleted = isDeleted;
        this.name = name;
        this.paramLoggedIdUser = paramLoggedIdUser;
    }

    public RoutineWithExercisesDTO(List<RoutineAssignmentDTO> assignments, Integer categoryOrder, Date date, DifficultyRoutine difficultyRoutine, List<RoutineExerciseDTO> exercises, Long idRoutine, Long idUser, Long isDeleted, String name, Long paramLoggedIdUser) {
        this.assignments = assignments;
        this.categoryOrder = categoryOrder;
        this.date = date;
        this.difficultyRoutine = difficultyRoutine;
        this.exercises = exercises;
        this.idRoutine = idRoutine;
        this.idUser = idUser;
        this.isDeleted = isDeleted;
        this.name = name;
        this.paramLoggedIdUser = paramLoggedIdUser;
    }

    public Long getIdRoutine() {
        return idRoutine;
    }

    public void setIdRoutine(Long idRoutine) {
        this.idRoutine = idRoutine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public DifficultyRoutine getDifficultyRoutine() {
        return difficultyRoutine;
    }

    public void setDifficultyRoutine(DifficultyRoutine difficultyRoutine) {
        this.difficultyRoutine = difficultyRoutine;
    }

    public List<RoutineExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<RoutineExerciseDTO> exercises) {
        this.exercises = exercises;
    }

    public List<RoutineAssignmentDTO> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<RoutineAssignmentDTO> assignments) {
        this.assignments = assignments;
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

    public Integer getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(Integer categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

}
