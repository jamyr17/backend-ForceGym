package una.force_gym.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbGender")
public class Gender {
    
    @Id
    @Column(name = "idGender")
    private Long idGender;
    
    @Column(name = "name")
    private String name;

    public Gender(){}

    public Gender(Long idGender, String name){
        this.idGender = idGender;
        this.name = name;
    }

    public Long getIdGender() {
        return idGender;
    }

    public void setIdGender(Long idGender) {
        this.idGender = idGender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
