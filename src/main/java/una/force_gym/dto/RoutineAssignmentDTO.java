package una.force_gym.dto;

import java.util.Date;

public class RoutineAssignmentDTO {

    private Long idClient;
    private Date assignmentDate;

    public RoutineAssignmentDTO() {
    }

    public RoutineAssignmentDTO(Date assignmentDate, Long idClient) {
        this.assignmentDate = assignmentDate;
        this.idClient = idClient;
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
