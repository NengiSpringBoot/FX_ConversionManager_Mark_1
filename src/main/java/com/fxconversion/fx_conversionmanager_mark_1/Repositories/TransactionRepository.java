package com.fxconversion.fx_conversionmanager_mark_1.Repositories;

import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_REQUEST;
import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_TRANSACTION;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<FX_CONVERSION_TRANSACTION, Integer> {

    public Integer countByTransactionId(Integer id);

    @Query(nativeQuery = true, value = "SELECT * from fx_conversion_transaction fcc where fcr.deleted=1")
    public List<FX_CONVERSION_TRANSACTION> getDeletedTransactions();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update fx_conversion_transaction fcc set fcc.deleted = 1 where fcc.transaction_Id in(:integers)")
    void deleteByIdIn(List<Integer> integers);


}
