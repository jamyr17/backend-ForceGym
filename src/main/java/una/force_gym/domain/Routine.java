package una.force_gym.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "idUser")
    private Long idUser;

    @Column(name = "createdByUser")
    private Long createdByUser;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedByUser")
    private Long updatedByUser;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "isDeleted")
    private Long isDeleted;

    public Routine() {
    }

    public Routine(Date createdAt, Long createdByUser, Date date, Long idRoutine, Long idUser, Long isDeleted, String name, Date updatedAt, Long updatedByUser) {
        this.createdAt = createdAt;
        this.createdByUser = createdByUser;
        this.date = date;
        this.idRoutine = idRoutine;
        this.idUser = idUser;
        this.isDeleted = isDeleted;
        this.name = name;
        this.updatedAt = updatedAt;
        this.updatedByUser = updatedByUser;
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(Long createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(Long updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }
}
