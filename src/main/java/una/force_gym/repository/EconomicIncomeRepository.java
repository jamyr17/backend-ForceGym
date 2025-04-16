package una.force_gym.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import una.force_gym.domain.EconomicIncome;

public interface EconomicIncomeRepository extends JpaRepository<EconomicIncome, Long>{
    
    @Procedure(procedureName = "prInsertEconomicIncome", outputParameterName = "result")
    int addEconomicIncome(@Param("pIdClient") Long pIdClient, @Param("pRegistrationDate") LocalDate pRegistrationDate, @Param("pVoucherNumber") String pVoucherNumber, @Param("pDetail") String pDetail, @Param("pIdMeanOfPayment") Long pIdMeanOfPayment, @Param("pAmount") Float pAmount, @Param("pIdActivityType") Long pIdActivityType, @Param("pDelayDays") Long pDelayDays, @Param("pLoggedIdUser") Long pLoggedIdUser);

    @Procedure(procedureName = "prUpdateEconomicIncome", outputParameterName = "result")
    int updateEconomicIncome(@Param("pIdEconomicIncome") Long pIdEconomicIncome, @Param("pIdClient") Long pIdClient, @Param("pRegistrationDate") LocalDate pRegistrationDate, @Param("pVoucherNumber") String pVoucherNumber, @Param("pDetail") String pDetail, @Param("pIdMeanOfPayment") Long pIdMeanOfPayment, @Param("pAmount") Float pAmount, @Param("pIdActivityType") Long pIdActivityType, @Param("pDelayDays") Long pDelayDays, @Param("pIsDeleted") Long pIsDeleted,  @Param("pLoggedIdUser") Long pLoggedIdUser);

    @Procedure(procedureName = "prDeleteEconomicIncome")
    int deleteEconomicIncome(@Param("pIdEconomicIncome") Long pIdEconomicIncome, @Param("pLoggedIdUser") Long pLoggedIdUser);
    
}
