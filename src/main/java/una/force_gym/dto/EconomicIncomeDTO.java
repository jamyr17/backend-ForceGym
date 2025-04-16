package una.force_gym.dto;

import java.time.LocalDate;

public class EconomicIncomeDTO {

    private Long idEconomicIncome;
    private Long idClient;
    private LocalDate registrationDate;
    private String voucherNumber;
    private String detail;
    private Long idMeanOfPayment;
    private Float amount;
    private Long idActivityType;
    private Long delayDays;
    private Long isDeleted;
    private Long paramLoggedIdUser;

    public EconomicIncomeDTO() {}

    public EconomicIncomeDTO(Long idEconomicIncome, Long idClient, LocalDate registrationDate, String voucherNumber,
            String detail, Long idMeanOfPayment, Float amount, Long idActivityType, Long delayDays, Long isDeleted,
            Long paramLoggedIdUser) {
        this.idEconomicIncome = idEconomicIncome;
        this.idClient = idClient;
        this.registrationDate = registrationDate;
        this.voucherNumber = voucherNumber;
        this.detail = detail;
        this.idMeanOfPayment = idMeanOfPayment;
        this.amount = amount;
        this.idActivityType = idActivityType;
        this.delayDays = delayDays;
        this.isDeleted = isDeleted;
        this.paramLoggedIdUser = paramLoggedIdUser;
    }

    public Long getIdEconomicIncome() {
        return idEconomicIncome;
    }

    public void setIdEconomicIncome(Long idEconomicIncome) {
        this.idEconomicIncome = idEconomicIncome;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
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

    public Long getIdMeanOfPayment() {
        return idMeanOfPayment;
    }

    public void setIdMeanOfPayment(Long idMeanOfPayment) {
        this.idMeanOfPayment = idMeanOfPayment;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Long getIdActivityType() {
        return idActivityType;
    }

    public void setIdActivityType(Long idActivityType) {
        this.idActivityType = idActivityType;
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

    public Long getParamLoggedIdUser() {
        return paramLoggedIdUser;
    }

    public void setParamLoggedIdUser(Long paramLoggedIdUser) {
        this.paramLoggedIdUser = paramLoggedIdUser;
    }
    
}
