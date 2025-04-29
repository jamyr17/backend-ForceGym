package una.force_gym.dto;

import java.time.LocalDate;

public class Asset {

    private Long idAsset;
    private Long idUser;
    private LocalDate boughtDate;
    private String code;
    private String name;
    private int quantity;
    private Float initialCost;
    private int serviceLifeYears;
    private Float deprecationPerYear;
    private Long isDeleted;
    private Long paramLoggedIdUser;

    public Asset() {}

    public Asset(Long idAsset, Long idUser, LocalDate boughtDate, String code, String name, int quantity,
            Float initialCost, int serviceLifeYears, Float deprecationPerYear, Long isDeleted, Long paramLoggedIdUser) {
        this.idAsset = idAsset;
        this.idUser = idUser;
        this.boughtDate = boughtDate;
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.initialCost = initialCost;
        this.serviceLifeYears = serviceLifeYears;
        this.deprecationPerYear = deprecationPerYear;
        this.isDeleted = isDeleted;
        this.paramLoggedIdUser = paramLoggedIdUser;
    }

    public Long getIdAsset() {
        return idAsset;
    }

    public void setIdAsset(Long idAsset) {
        this.idAsset = idAsset;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getInitialCost() {
        return initialCost;
    }

    public void setInitialCost(Float initialCost) {
        this.initialCost = initialCost;
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

    public LocalDate getBoughtDate() {
        return boughtDate;
    }

    public void setBoughtDate(LocalDate boughtDate) {
        this.boughtDate = boughtDate;
    }

    public int getServiceLifeYears() {
        return serviceLifeYears;
    }

    public void setServiceLifeYears(int serviceLifeYears) {
        this.serviceLifeYears = serviceLifeYears;
    }

    public Float getDeprecationPerYear() {
        return deprecationPerYear;
    }

    public void setDeprecationPerYear(Float deprecationPerYear) {
        this.deprecationPerYear = deprecationPerYear;
    }
    
}
