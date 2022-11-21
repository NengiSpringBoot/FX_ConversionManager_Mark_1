package com.fxconversion.fx_conversionmanager_mark_1.RestControllers;

import com.fxconversion.fx_conversionmanager_mark_1.Enums.EnumWorkflow;
import com.fxconversion.fx_conversionmanager_mark_1.Exceptions.AppException;
import com.fxconversion.fx_conversionmanager_mark_1.Exceptions.ValueNotFoundException;
import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_REQUEST;
import com.fxconversion.fx_conversionmanager_mark_1.Services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class RequestRestController {

    @Autowired
    RequestService requestService;


//////////////////////////////////////////////////REQUESTS LISTS////////////////////////////////////////////////

    @GetMapping("getallrequests")
    public List<FX_CONVERSION_REQUEST> allRequests(){
        return requestService.getAllRequests();
    }

    @GetMapping("getdeletedrequests")
    public List<FX_CONVERSION_REQUEST> getDeletedRequests(){
        return requestService.getDeletedRequests();
    }


    @GetMapping("hopstagerequests")
    public List<FX_CONVERSION_REQUEST> HopStageList(){
        return requestService.workflowStage(1);
    }


    @GetMapping("treasurystagerequests")
    public List<FX_CONVERSION_REQUEST> TreasuryStageList(){
        return requestService.workflowStage(2);
    }


    @GetMapping("tropsstagerequests")
    public List<FX_CONVERSION_REQUEST> TROPSStageList(){
        return requestService.workflowStage(3);
    }
    ////////////////////////////////////////////////////REQUEST LISTS////////////////////////////////////////////////



    //////////////////////////////CREATE REQUEST/////////////////////////////////////////////////////
    @PostMapping("createrequest")
    public FX_CONVERSION_REQUEST createRequest(@RequestBody FX_CONVERSION_REQUEST request){
        System.out.println(request);
       requestService.createRequest(request);
       return request;
    }

    public FX_CONVERSION_REQUEST findRequest(@RequestParam int requestId) throws ValueNotFoundException {
        return requestService.get(requestId);
    }



//    @PutMapping("mark2update")
//    public FX_CONVERSION_REQUEST mark2updateRequest(@RequestBody FX_CONVERSION_REQUEST request) throws ValueNotFoundException {
//
//        Integer searchId = request.getRequestId();
//        FX_CONVERSION_REQUEST existingRequest = requestService.get(searchId);
//        existingRequest.setRequestTransaction(request.getRequestTransaction());
//
//        requestService.updateRequest(existingRequest);
//        return existingRequest;
//    }


    //////////////////////////////////////////APPROVAL AND REJECTIONS/////////////////////////////////////////////////
    @PutMapping("hopapproval")
    public String HOPApproval(@RequestParam int requestId)

            throws ValueNotFoundException {
        FX_CONVERSION_REQUEST existingRequest = requestService.get(requestId);
        existingRequest.setRequestWorkflow(EnumWorkflow.TreasuryStage);
        existingRequest.setHOPApprovalDate(LocalDateTime.now());
        requestService.updateRequest(existingRequest);
        return String.format("%s was APPROVED SUCCESSFULLY", existingRequest);
    }


    @PutMapping("hoprejection")
    public String HOPRejection(@RequestParam int requestId)

            throws ValueNotFoundException{
        FX_CONVERSION_REQUEST existingRequest = requestService.get(requestId);
        existingRequest.setRequestWorkflow(EnumWorkflow.RSMStage);
        requestService.updateRequest(existingRequest);
        return String.format("%s REJECTED SUCCESSFULLY", existingRequest);
    }

    @PutMapping("treasuryapproval")
    public String TreasuryApproval(@RequestParam int requestId)
            throws ValueNotFoundException{
            FX_CONVERSION_REQUEST existingRequest = requestService.get(requestId);
            existingRequest.setRequestWorkflow(EnumWorkflow.TROPSStage);
            existingRequest.setTreasuryApprovalDate(LocalDateTime.now());
            requestService.updateRequest(existingRequest);
            return String.format("%s was APPROVED SUCCESSFULLY", existingRequest);
    }


    @PutMapping("treasuryrejection")
    public String TreasuryRejection(@RequestParam int requestId)
            throws ValueNotFoundException{
            FX_CONVERSION_REQUEST existingRequest = requestService.get(requestId);
            existingRequest.setRequestWorkflow(EnumWorkflow.RSMStage);
            existingRequest.setTreasuryApprovalDate(LocalDateTime.now());
            requestService.updateRequest(existingRequest);
            return String.format("%s was REJECTED", existingRequest);
    }


    @PutMapping("tropsrejection")
    public String TROPSRejection(@RequestParam int requestId)
            throws ValueNotFoundException{
            FX_CONVERSION_REQUEST existingRequest = requestService.get(requestId);
            existingRequest.setRequestWorkflow(EnumWorkflow.TreasuryStage);
            existingRequest.setTROPSApprovalDate(LocalDateTime.now());
            requestService.updateRequest(existingRequest);
            return String.format("%s was REJECTED", existingRequest);
    }

    @PutMapping("tropsapproval")
    public String TROPSApproval(@RequestParam int requestId)
            throws ValueNotFoundException{
            FX_CONVERSION_REQUEST existingRequest = requestService.get(requestId);
            existingRequest.setRequestWorkflow(EnumWorkflow.FINISHED);
            existingRequest.setTROPSApprovalDate(LocalDateTime.now());
            requestService.updateRequest(existingRequest);
            return String.format("%s was APPROVED successfully", existingRequest);
    }

    /////////////////////////////////////////////APPROVALS AND REJECTIONS METHODS//////////////////////////////////////




    //////////////////////////////////////////////DELETION METHODS/////////////////////////////////////////////////////
    @DeleteMapping("deleterequest")
    public void deleteRequestById  (@RequestParam int requestId) throws ValueNotFoundException {
        requestService.deleteRequestById(requestId);
    }

    @DeleteMapping("deletemultiple")
    public String delete(@RequestParam("ids") List<Integer> ids) {
        System.out.println("deleting");
        requestService.deleteAllBYIds(ids);
        return String.join(",", ids.stream().map(value ->  Integer.toString(value)).collect(Collectors.toList()));
    }

    //////////////////////////////////////////////DELETION METHODS/////////////////////////////////////////////////////






}
