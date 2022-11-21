package com.fxconversion.fx_conversionmanager_mark_1.Repositories;

import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_REQUEST;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<FX_CONVERSION_REQUEST, Integer> {

    public Integer countByRequestId(Integer id);

    public FX_CONVERSION_REQUEST findByRequestId(int id);

    @Query(nativeQuery = true, value = "SELECT * from fx_conversion_request fcr where fcr.workflow_track=:enumWorkflow and RSMSubmitted=1")
    public List<FX_CONVERSION_REQUEST> workflowStage(int enumWorkflow);

//    @Modifying
//    @Transactional
//    @Query(nativeQuery = true, value= "delete from fx_conversion_request fcr where fcr.request_Id in(:integers)")
//    void deleteByIdIn(List<Integer> integers);

    @Query(nativeQuery = true, value = "SELECT * from fx_conversion_request fcr where fcr.deleted=1")
    public List<FX_CONVERSION_REQUEST> getDeletedRequests();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update fx_conversion_request fcr set fcr.deleted = 1 where fcr.request_Id in(:integers)")
    void deleteByIdIn(List<Integer> integers);


}
