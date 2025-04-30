package una.force_gym.dto;

import java.util.List;

public class RoutineExerciseDTO {

    private Long idExercise;
    private Integer series;
    private Integer repetitions;

    public RoutineExerciseDTO() {
    }

    public RoutineExerciseDTO(Long idExercise, Integer repetitions, Integer series) {
        this.idExercise = idExercise;
        this.repetitions = repetitions;
        this.series = series;
    }

    public Long getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(Long idExercise) {
        this.idExercise = idExercise;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

}
