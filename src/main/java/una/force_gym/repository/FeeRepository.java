package una.force_gym.repository;

import una.force_gym.domain.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface FeeRepository extends JpaRepository<Fee, Long> {
    
    // Método para buscar por idActivityType
    List<Fee> findByActivityType_IdActivityType(Long idActivityType);
    
    // Método para eliminar por idActivityType
    @Transactional
    @Modifying
    @Query("DELETE FROM Fee f WHERE f.activityType.idActivityType = :idActivityType")
    void deleteByActivityTypeId(Long idActivityType);
    
}