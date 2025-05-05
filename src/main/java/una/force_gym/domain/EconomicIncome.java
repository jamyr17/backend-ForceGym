package una.force_gym.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbEconomicIncome")
public class EconomicIncome {

    @Id
    @Column(name = "idEconomicIncome")
    private Long idEconomicIncome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    private Client client;

    @Column(name = "registrationDate")
    private LocalDate registrationDate;

    @Column(name = "voucherNumber")
    private String voucherNumber;

    @Column(name = "detail")
    private String detail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idMeanOfPayment", referencedColumnName = "idMeanOfPayment")
    private MeanOfPayment meanOfPayment;

    @Column(name = "amount")
    private Float amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idActivityType", referencedColumnName = "idActivityType")
    private ActivityType activityType;

    @Column(name = "delayDays")
    private Long delayDays;

    @Column(name = "isDeleted")
    private Long isDeleted;

    public EconomicIncome() {}

    public EconomicIncome(Long idEconomicIncome, Client client, LocalDate registrationDate, String voucherNumber, String detail, MeanOfPayment meanOfPayment, Float amount, ActivityType activityType, Long delayDays, Long isDeleted) {
        this.idEconomicIncome = idEconomicIncome;
        this.client = client;
        this.registrationDate = registrationDate;
        this.voucherNumber = voucherNumber;
        this.detail = detail;
        this.meanOfPayment = meanOfPayment;
        this.amount = amount;
        this.activityType = activityType;
        this.delayDays = delayDays;
        this.isDeleted = isDeleted;
    }

    public Long getIdEconomicIncome() {
        return idEconomicIncome;
    }

    public void setIdEconomicIncome(Long idEconomicIncome) {
        this.idEconomicIncome = idEconomicIncome;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public MeanOfPayment getMeanOfPayment() {
        return meanOfPayment;
    }

    public void setMeanOfPayment(MeanOfPayment meanOfPayment) {
        this.meanOfPayment = meanOfPayment;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public Long getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(Long delayDays) {
        this.delayDays = delayDays;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

}
