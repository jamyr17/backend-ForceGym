package una.force_gym.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbExerciseDifficulty")
public class ExerciseDifficulty {

    @Id
    @Column(name = "idExerciseDifficulty")
    private Integer idExerciseDifficulty;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "isDeleted")
    private Long isDeleted;

    public ExerciseDifficulty() {
    }

    public ExerciseDifficulty(Integer idExerciseDifficulty, String difficulty, Long isDeleted) {
        this.idExerciseDifficulty = idExerciseDifficulty;
        this.difficulty = difficulty;
        this.isDeleted = isDeleted;
    }

    public Integer getIdExerciseDifficulty() {
        return idExerciseDifficulty;
    }

    public void setIdExerciseDifficulty(Integer idExerciseDifficulty) {
        this.idExerciseDifficulty = idExerciseDifficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }
}