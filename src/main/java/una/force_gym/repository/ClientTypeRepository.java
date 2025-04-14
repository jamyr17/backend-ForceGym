package una.force_gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import una.force_gym.domain.ClientType;

public interface ClientTypeRepository extends JpaRepository<ClientType, Long> {

    @Procedure(procedureName = "prInsertClientType", outputParameterName = "result")
    int addClientType(
        @Param("pName") String pName, 
        @Param("pLoggedIdUser") Long pLoggedIdUser
    );

    @Procedure(procedureName = "prUpdateClientType", outputParameterName = "result")
    int updateClientType(
        @Param("pIdClientType") Long pIdClientType, 
        @Param("pName") String pName,
        @Param("pIsDeleted") Long pIsDeleted,  
        @Param("pLoggedIdUser") Long pLoggedIdUser
    );

    @Procedure(procedureName = "prDeleteClientType", outputParameterName = "result")
    int deleteClientType(
        @Param("pIdClientType") Long pIdClientType, 
        @Param("pLoggedIdUser") Long pLoggedIdUser
    );
}
