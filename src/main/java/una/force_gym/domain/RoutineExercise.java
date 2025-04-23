package una.force_gym.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbRoutineExercise")
public class RoutineExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRoutineExercise")
    private Long idRoutineExercise;

    @Column(name = "idRoutine")
    private Long idRoutine;

    @Column(name = "idExercise")
    private Long idExercise;

    @Column(name = "series")
    private Integer series;

    @Column(name = "repetitions")
    private Integer repetitions;

    public RoutineExercise() {
    }

    public RoutineExercise(Long idExercise, Long idRoutine, Long idRoutineExercise, Integer repetitions, Integer series) {
        this.idExercise = idExercise;
        this.idRoutine = idRoutine;
        this.idRoutineExercise = idRoutineExercise;
        this.repetitions = repetitions;
        this.series = series;
    }

    public Long getIdRoutineExercise() {
        return idRoutineExercise;
    }

    public void setIdRoutineExercise(Long idRoutineExercise) {
        this.idRoutineExercise = idRoutineExercise;
    }

    public Long getIdRoutine() {
        return idRoutine;
    }

    public void setIdRoutine(Long idRoutine) {
        this.idRoutine = idRoutine;
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
