package una.force_gym.dto;

public class ProductInventoryDTO {

    private Long idProductInventory;
    private Long idUser;
    private String code;
    private String name;
    private int quantity;
    private Float cost;
    private Long isDeleted;
    private Long paramLoggedIdUser;

    public ProductInventoryDTO() {}

    public ProductInventoryDTO(Long idProductInventory, Long idUser, String code, String name, int quantity, Float cost,
            Long isDeleted, Long paramLoggedIdUser) {
        this.idProductInventory = idProductInventory;
        this.idUser = idUser;
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.isDeleted = isDeleted;
        this.paramLoggedIdUser = paramLoggedIdUser;
    }

    public Long getIdProductInventory() {
        return idProductInventory;
    }

    public void setIdProductInventory(Long idProductInventory) {
        this.idProductInventory = idProductInventory;
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

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
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
