package com.fxconversion.fx_conversionmanager_mark_1.Services;

import com.fxconversion.fx_conversionmanager_mark_1.Enums.EnumWorkflow;
import com.fxconversion.fx_conversionmanager_mark_1.Exceptions.ValueNotFoundException;
import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_REQUEST;
import com.fxconversion.fx_conversionmanager_mark_1.Repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;


    ///////////BASIC CRUD OPERATIONS//////////////

    //Request Creation/Submission
    public void createRequest(FX_CONVERSION_REQUEST request){
        request.setRequestWorkflow(EnumWorkflow.HOPStage);
        request.setRSMSubmitted(true);
        request.setRequestTransaction(request.getRequestTransaction());

        //TODO
/*        Set RSM Name
          Set Branch
 */
        requestRepository.save(request);
    }

    //Get list of all requests
    public List<FX_CONVERSION_REQUEST> getAllRequests(){
        return requestRepository.findAll();
    }


    //Get a particular request by id
    public FX_CONVERSION_REQUEST get(Integer id) throws ValueNotFoundException {

        try {
            return requestRepository.findById(id).get();
        } catch (NoSuchElementException exception) {
            throw new ValueNotFoundException("Could not find any request with ID " + id);
        }
    }

    public void updateRequest(FX_CONVERSION_REQUEST request){
        requestRepository.save(request);
    }

    //Delete request by id
    public void deleteRequestById(Integer id) throws ValueNotFoundException {


        Integer countById = requestRepository.countByRequestId(id);
        if (countById == null || countById == 0) {
            throw new ValueNotFoundException("Could not find any request with ID " + id);
        }
        requestRepository.deleteById(id);

    }

    //HOP Pending Approvals
    public List<FX_CONVERSION_REQUEST> workflowStage(int workflow){
        return requestRepository.workflowStage(workflow);
    }

    //Get Deleted Requests
    public List<FX_CONVERSION_REQUEST> getDeletedRequests(){
        return requestRepository.getDeletedRequests();
    }

    public void deleteAllBYIds(List<Integer> integers) {
        requestRepository.deleteByIdIn(integers);
    }

}
