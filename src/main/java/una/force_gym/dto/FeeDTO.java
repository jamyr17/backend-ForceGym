package una.force_gym.dto;

import java.util.List;

public class FeeDTO {
    private List<Long> idClientType; 
    private Double amount;

    public FeeDTO() {}

    public FeeDTO(List<Long> idClientType, Double amount) {
        this.idClientType = idClientType;
        this.amount = amount;
    }

    public List<Long> getIdClientType() {
        return idClientType;
    }
    
    public void setIdClientType(List<Long> idClientType) {
        this.idClientType = idClientType;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
}