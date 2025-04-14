package una.force_gym.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbClientType")
public class ClientType {
    
    @Id
    @Column(name = "idClientType")
    private Long idClientType;

    @Column(name = "name")
    private String name;

    public ClientType() {}

    public ClientType(Long idClientType, String name) {
        this.idClientType = idClientType;
        this.name = name;
    }

    public Long getIdClientType() {
        return idClientType;
    }

    public void setIdClientType(Long idClientType) {
        this.idClientType = idClientType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
