package una.force_gym.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import una.force_gym.domain.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long>{

    @Procedure(procedureName = "prInsertAsset", outputParameterName = "result")
    int addAsset(@Param("pIdUser") Long pIdUser, @Param("pBoughtDate") LocalDate pBoughtDate, @Param("pCode") String pCode, @Param("pName") String pName, @Param("pQuantity") int pQuantity, @Param("pInitialCost") Float pInitialCost, @Param("pServiceLifeYears") int pServiceLifeYears,  @Param("pDeprecationPerYear") Float pDeprecationPerYear, @Param("pLoggedIdUser") Long pLoggedIdUser);

    @Procedure(procedureName = "prUpdateAsset", outputParameterName = "result")
    int updateAsset(@Param("pIdAsset") Long pIdAsset, @Param("pIdUser") Long pIdUser, @Param("pBoughtDate") LocalDate pBoughtDate, @Param("pCode") String pCode, @Param("pName") String pName, @Param("pQuantity") int pQuantity, @Param("pInitialCost") Float pInitialCost, @Param("pServiceLifeYears") int pServiceLifeYears,  @Param("pDeprecationPerYear") Float pDeprecationPerYear, @Param("pIsDeleted") Long pIsDeleted,  @Param("pLoggedIdUser") Long pLoggedIdUser);

    @Procedure(procedureName = "prDeleteAsset")
    int deleteAsset(@Param("pIdAsset") Long pIdAsset, @Param("pLoggedIdUser") Long pLoggedIdUser);

}
