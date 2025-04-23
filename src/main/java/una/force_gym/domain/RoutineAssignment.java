package una.force_gym.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbRoutineAssignment")
public class RoutineAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRoutineAssignment")
    private Long idRoutineAssignment;

    @Column(name = "idRoutine")
    private Long idRoutine;

    @Column(name = "idClient")
    private Long idClient;

    @Column(name = "assignmentDate")
    private Date assignmentDate;

    public RoutineAssignment() {
    }

    public RoutineAssignment(Date assignmentDate, Long idClient, Long idRoutine, Long idRoutineAssignment) {
        this.assignmentDate = assignmentDate;
        this.idClient = idClient;
        this.idRoutine = idRoutine;
        this.idRoutineAssignment = idRoutineAssignment;
    }

    public Long getIdRoutineAssignment() {
        return idRoutineAssignment;
    }

    public void setIdRoutineAssignment(Long idRoutineAssignment) {
        this.idRoutineAssignment = idRoutineAssignment;
    }

    public Long getIdRoutine() {
        return idRoutine;
    }

    public void setIdRoutine(Long idRoutine) {
        this.idRoutine = idRoutine;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Date getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }
}
