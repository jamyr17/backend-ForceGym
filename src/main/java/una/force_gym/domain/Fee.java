package una.force_gym.domain;

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
@Table(name = "tbFee")
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFee")
    private Long idFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idActivityType", referencedColumnName = "idActivityType")
    private ActivityType activityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClientType", referencedColumnName = "idClientType")
    private ClientType clientType;

    @Column(name = "amount")
    private Double amount;

    public Fee() {}

    public Fee(ActivityType activityType, ClientType clientType, Double amount) {
        this.activityType = activityType;
        this.clientType = clientType;
        this.amount = amount;
    }

    public Long getIdFee() {
        return idFee;
    }

    public void setIdFee(Long idFee) {
        this.idFee = idFee;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    
}