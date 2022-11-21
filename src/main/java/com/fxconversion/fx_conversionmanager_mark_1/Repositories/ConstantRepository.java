package com.fxconversion.fx_conversionmanager_mark_1.Repositories;

import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_CONSTANTS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ConstantRepository extends JpaRepository<FX_CONVERSION_CONSTANTS, Integer> {

    public Integer countByConstantId(int id);


    @Query(nativeQuery = true, value = "select * from fx_conversion_constants fcc where fcc.currency_to=?1 and fcc.currency_from=?2")
    public List<FX_CONVERSION_CONSTANTS> getConstantByCurrency(int currencyToCode, int currencyFromCode);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update fx_conversion_constants fcc set fcc.deleted = 1 where fcc.constant_Id in(:integers)")
    void deleteByIdIn(List<Integer> integers);

    @Query(nativeQuery = true, value = "select * from fx_conversion_constants fcc where fcc.deleted=1")
    public List<FX_CONVERSION_CONSTANTS> deletedConstants();



}
