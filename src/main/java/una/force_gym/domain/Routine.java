package una.force_gym.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbRoutine")
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRoutine")
    private Long idRoutine;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idDifficultyRoutine", referencedColumnName = "idDifficultyRoutine")
    private DifficultyRoutine difficultyRoutine;

    @Column(name = "isDeleted")
    private Long isDeleted;

    public Routine() {
    }

    public Routine(Date date, DifficultyRoutine difficultyRoutine, Long idRoutine, Long isDeleted, String name, User user) {
        this.date = date;
        this.difficultyRoutine = difficultyRoutine;
        this.idRoutine = idRoutine;
        this.isDeleted = isDeleted;
        this.name = name;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DifficultyRoutine getDifficultyRoutine() {
        return difficultyRoutine;
    }

    public void setDifficultyRoutine(DifficultyRoutine difficultyRoutine) {
        this.difficultyRoutine = difficultyRoutine;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

}
