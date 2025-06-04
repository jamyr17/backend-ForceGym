package una.force_gym.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import una.force_gym.domain.Person;
import una.force_gym.domain.Role;

@Entity
@Table(name = "tbUser")
public class UserDTO {

    @Id
    @Column(name = "idUser")
    private Long idUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPerson", referencedColumnName = "idPerson")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRole", referencedColumnName = "idRole")
    private Role role;

    @Column(name = "username")
    private String username;

    @Column(name = "isDeleted")
    private Long isDeleted;

    public UserDTO(){}

    public UserDTO(Long idUser, Person person, Role role, String username, Long isDeleted) {
        this.idUser = idUser;
        this.person = person;
        this.role = role;
        this.username = username;
        this.isDeleted = isDeleted;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

}
