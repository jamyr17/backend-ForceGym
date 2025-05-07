package una.force_gym.dto;

import java.util.List;

public class RoutineExerciseDTO {

    private Long idExercise;
    private Integer series;
    private Integer repetitions;
    private String note;

    public RoutineExerciseDTO() {
    }

    public RoutineExerciseDTO(Long idExercise, Integer repetitions, Integer series, String note) {
        this.idExercise = idExercise;
        this.repetitions = repetitions;
        this.series = series;
        this.note = note;
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

    public String getNote() {
        return note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }

}
