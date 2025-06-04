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
@Table(name = "tbAsset")
public class Asset {
    
    @Id
    @Column(name = "idAsset")
    private Long idAsset;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private User user;

    @Column(name = "boughtDate")
    private LocalDate boughtDate;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "initialCost")
    private Float initialCost;

    @Column(name = "serviceLifeYears")
    private int serviceLifeYears;

    @Column(name = "deprecationPerYear")
    private Float deprecationPerYear;

    @Column(name = "isDeleted")
    private Long isDeleted;

    public Asset() {}

    public Asset(Long idAsset, User user, LocalDate boughtDate, String code, String name, int quantity,
            Float initialCost, int serviceLifeYears, Float deprecationPerYear, Long isDeleted) {
        this.idAsset = idAsset;
        this.user = user;
        this.boughtDate = boughtDate;
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.initialCost = initialCost;
        this.serviceLifeYears = serviceLifeYears;
        this.deprecationPerYear = deprecationPerYear;
        this.isDeleted = isDeleted;
    }

    public Long getIdAsset() {
        return idAsset;
    }

    public void setIdAsset(Long idAsset) {
        this.idAsset = idAsset;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
