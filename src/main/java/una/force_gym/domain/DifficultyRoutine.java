package una.force_gym.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbDifficultyRoutine")
public class DifficultyRoutine {

    @Id
    @Column(name = "idDifficultyRoutine")
    private Long idDifficultyRoutine;

    @Column(name = "name")
    private String name;

    public DifficultyRoutine() {
    }

    public DifficultyRoutine(Long idDifficultyRoutine, String name) {
        this.idDifficultyRoutine = idDifficultyRoutine;
        this.name = name;
    }

    public Long getIdDifficultyRoutine() {
        return idDifficultyRoutine;
    }

    public void setIdDifficultyRoutine(Long idDifficultyRoutine) {
        this.idDifficultyRoutine = idDifficultyRoutine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
