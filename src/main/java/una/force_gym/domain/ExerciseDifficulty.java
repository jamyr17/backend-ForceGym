package una.force_gym.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbExerciseCategory")
public class ExerciseDifficulty {

    @Id
    @Column(name = "idExerciseCategory")
    private Integer idExerciseCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "isDeleted")
    private Long isDeleted;

    public ExerciseCategory() {
    }

    public ExerciseCategory(Integer idExerciseCategory, String name, Long isDeleted) {
        this.idExerciseCategory = idExerciseCategory;
        this.name = name;
        this.isDeleted = isDeleted;

    }

    public Integer getIdExerciseCategory() {
        return idExerciseCategory;
    }

    public void setIdExerciseCategory(Integer idExerciseCategory) {
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
}