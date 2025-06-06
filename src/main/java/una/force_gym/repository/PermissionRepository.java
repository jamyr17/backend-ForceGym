package una.force_gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import una.force_gym.domain.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    
    @Procedure(procedureName = "prInsertPermission", outputParameterName = "result")
    int addPermission(  @Param("pDefinition") String pDefinition, 
                        @Param("pDescription") String pDescription, 
                        @Param("pLoggedIdUser") Long pLoggedIdUser);

    @Procedure(procedureName = "prUpdatePermission", outputParameterName = "result")
    int updatePermission(   @Param("pIdPermission") Long pIdPermission, 
                            @Param("pDefinition") String pDefinition, 
                            @Param("pDescription") String pDescription,
                            @Param("pIsDeleted") Long pIsDeleted,   
                            @Param("pLoggedIdUser") Long pLoggedIdUser);


    @Procedure(procedureName = "prDeletePermission")
    int deletePermission(   @Param("pIdPermission") Long pIdPermission, 
                            @Param("pLoggedIdUser") Long pLoggedIdUser);

}
