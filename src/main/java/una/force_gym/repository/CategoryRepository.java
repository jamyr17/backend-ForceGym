package una.force_gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import una.force_gym.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    @Procedure(procedureName = "prGetCategory")
    List<Category> getCategory(); 

}