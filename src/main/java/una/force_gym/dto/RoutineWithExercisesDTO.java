package una.force_gym.dto;

import java.util.Date;
import java.util.List;

import una.force_gym.domain.DifficultyRoutine;

public class RoutineWithExercisesDTO {

    private Long idRoutine;
    private String name;
    private Date date;
    private Long idUser;
    private DifficultyRoutine DifficultyRoutine;
    private List<RoutineExerciseDTO> exercises;
    private Long isDeleted;

    public RoutineWithExercisesDTO() {
    }

    public RoutineWithExercisesDTO(DifficultyRoutine DifficultyRoutine, Date date, List<RoutineExerciseDTO> exercises, Long idRoutine, Long idUser, Long isDeleted, String name) {
        this.DifficultyRoutine = DifficultyRoutine;
        this.date = date;
        this.exercises = exercises;
        this.idRoutine = idRoutine;
        this.idUser = idUser;
        this.isDeleted = isDeleted;
        this.name = name;
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
        return DifficultyRoutine;
    }

    public void setDifficultyRoutine(DifficultyRoutine DifficultyRoutine) {
        this.DifficultyRoutine = DifficultyRoutine;
    }

    public List<RoutineExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<RoutineExerciseDTO> exercises) {
        this.exercises = exercises;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

}
