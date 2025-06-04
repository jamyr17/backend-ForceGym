package una.force_gym.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tbNotification")
public class Notification {

    @Id
    @Column(name = "idNotification")
    private Long idNotification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idNotificationType", referencedColumnName = "idNotificationType")
    private NotificationType notificationType;

    @Column(name = "sendDate")
    private Date sendDate;

    @Column(name = "isDeleted")
    private Long isDeleted;

    public Notification() {}

    public Notification(Long idNotification, Client client, NotificationType notificationType, Date sendDate, Long isDeleted) {
        this.idNotification = idNotification;
        this.client = client;
        this.notificationType = notificationType;
        this.sendDate = sendDate;
        this.isDeleted = isDeleted;
    }

    public Long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }   

}