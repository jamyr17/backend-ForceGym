package una.force_gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import una.force_gym.domain.Gender;

public interface GenderRepository extends JpaRepository<Gender, Long> {
    
    @Procedure(procedureName = "prGetGender")
    List<Gender> getGenders(); 

}
