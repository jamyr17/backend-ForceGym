package una.force_gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.query.Procedure;

import una.force_gym.domain.ActivityType;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {
    
    @Procedure(procedureName = "prGetActivityType")
    List<ActivityType> getActivityTypes();
    
    @Query("SELECT a FROM ActivityType a WHERE a.isDeleted = 0")
    List<ActivityType> findActiveActivityTypes();
    
    @Query("SELECT a FROM ActivityType a")
    List<ActivityType> findAllIncludingDeleted();
    
    @Modifying
    @Query("UPDATE ActivityType a SET a.isDeleted = 1 WHERE a.idActivityType = :id")
    void softDelete(@Param("id") Long id);
}