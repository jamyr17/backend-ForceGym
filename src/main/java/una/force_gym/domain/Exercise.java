package una.force_gym.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tbExercise")
public class Exercise {

    @Id
    @Column(name = "idExercise")
    private Long idExercise;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idExerciseCategory", referencedColumnName = "idExerciseCategory")
    private ExerciseCategory exerciseCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "isDeleted")
    private Long isDeleted;

    public Exercise() {
    }

    public Exercise(Long idExercise, String name, String description, String difficulty, Long isDeleted, User user, ExerciseCategory exerciseCategory) {
        this.idExercise = idExercise;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.isDeleted = isDeleted;
        this.user = user;
        this.exerciseCategory = exerciseCategory;
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

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ExerciseCategory getExerciseCategory() {
        return exerciseCategory;
    }

    public void setExerciseCategory(ExerciseCategory exerciseCategory) {
        this.exerciseCategory = exerciseCategory;
    }
}
