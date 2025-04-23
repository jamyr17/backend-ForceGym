package una.force_gym.dto;

import java.util.Date;
import java.util.List;

public class RoutineDTO {

    private Long idRoutine;
    private String name;
    private Date date;
    private Long idUser;
    private Long createdByUser;
    private Long isDeleted;
    private List<RoutineExerciseDTO> exercises;
    private List<RoutineAssignmentDTO> assignments;
    private Long paramLoggedIdUser;

    public RoutineDTO() {
    }

    public RoutineDTO(List<RoutineAssignmentDTO> assignments, Long createdByUser, Date date, List<RoutineExerciseDTO> exercises, Long idRoutine, Long idUser, Long isDeleted, String name, Long paramLoggedIdUser) {
        this.assignments = assignments;
        this.createdByUser = createdByUser;
        this.date = date;
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

    public Long getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(Long createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
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

    public Long getParamLoggedIdUser() {
        return paramLoggedIdUser;
    }

    public void setParamLoggedIdUser(Long paramLoggedIdUser) {
        this.paramLoggedIdUser = paramLoggedIdUser;
    }
}
