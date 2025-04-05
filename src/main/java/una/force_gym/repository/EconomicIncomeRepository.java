package una.force_gym.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import una.force_gym.domain.EconomicIncome;

public interface EconomicIncomeRepository extends JpaRepository<EconomicIncome, Long>{
    
    @Procedure(procedureName = "prGetAllEconomicIncomes",outputParameterName = "result")
    List<EconomicIncome> getAllEconomicIncomes(
        @Param("p_filterByStatus") String filterByStatus,
        @Param("p_filterByAmountRangeMin") BigDecimal filterByAmountRangeMin,
        @Param("p_filterByAmountRangeMax") BigDecimal filterByAmountRangeMax,
        @Param("p_filterByDateRangeStart") Date filterByDateRangeStart,
        @Param("p_filterByDateRangeEnd") Date filterByDateRangeEnd,
        @Param("p_filterByMeanOfPayment") Integer filterByMeanOfPayment,
        @Param("p_filterByTypeClient") Long filterByTypeClient
    );

    @Procedure(procedureName = "prGetAllEconomicIncomes",outputParameterName = "result")
    List<EconomicIncome> getAllEconomicIncomes(
        @Param("p_filterByStatus") String filterByStatus,
        @Param("p_filterByAmountRangeMin") BigDecimal filterByAmountRangeMin,
        @Param("p_filterByAmountRangeMax") BigDecimal filterByAmountRangeMax,
        @Param("p_filterByDateRangeStart") Date filterByDateRangeStart,
        @Param("p_filterByDateRangeEnd") Date filterByDateRangeEnd,
        @Param("p_filterByMeanOfPayment") Integer filterByMeanOfPayment,
        @Param("p_filterByTypeClient") Long filterByTypeClient
    );
    
    @Procedure(procedureName = "prInsertEconomicIncome", outputParameterName = "result")
    int addEconomicIncome(@Param("pIdClient") Long pIdClient, @Param("pRegistrationDate") LocalDate pRegistrationDate, @Param("pVoucherNumber") String pVoucherNumber, @Param("pDetail") String pDetail, @Param("pIdMeanOfPayment") Long pIdMeanOfPayment, @Param("pAmount") Float pAmount, @Param("pIdActivityType") Long pIdActivityType, @Param("pLoggedIdUser") Long pLoggedIdUser);

    @Procedure(procedureName = "prUpdateEconomicIncome", outputParameterName = "result")
    int updateEconomicIncome(@Param("pIdEconomicIncome") Long pIdEconomicIncome, @Param("pIdClient") Long pIdClient, @Param("pRegistrationDate") LocalDate pRegistrationDate, @Param("pVoucherNumber") String pVoucherNumber, @Param("pDetail") String pDetail, @Param("pIdMeanOfPayment") Long pIdMeanOfPayment, @Param("pAmount") Float pAmount, @Param("pIdActivityType") Long pIdActivityType, @Param("pIsDeleted") Long pIsDeleted,  @Param("pLoggedIdUser") Long pLoggedIdUser);

    @Procedure(procedureName = "prDeleteEconomicIncome")
    int deleteEconomicIncome(@Param("pIdEconomicIncome") Long pIdEconomicIncome, @Param("pLoggedIdUser") Long pLoggedIdUser);
    
}
