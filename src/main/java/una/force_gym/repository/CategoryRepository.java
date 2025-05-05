package una.force_gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import una.force_gym.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Procedure(procedureName = "prGetAllCategory")
    List<Category> getCategory();

    @Procedure(procedureName = "prInsertCategory", outputParameterName = "result")
    int addCategory(
            @Param("pName") String name,
            @Param("pLoggedIdUser") Long loggedIdUser
    );

    @Procedure(procedureName = "prUpdateCategory", outputParameterName = "result")
    int updateCategory(
            @Param("pIdCategory") Long idCategory,
            @Param("pName") String name,
            @Param("pIsDeleted") Long isDeleted,
            @Param("pLoggedIdUser") Long loggedIdUser
    );

    @Procedure(procedureName = "prDeleteCategory", outputParameterName = "result")
    int deleteCategory(
            @Param("pIdCategory") Long idCategory,
            @Param("pLoggedIdUser") Long loggedIdUser
    );
}
