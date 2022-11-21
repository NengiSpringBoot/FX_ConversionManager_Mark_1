package com.fxconversion.fx_conversionmanager_mark_1.Controllers;

import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_REQUEST;
import com.fxconversion.fx_conversionmanager_mark_1.Services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class RequestController {

    @Autowired
    RequestService requestService;

    @GetMapping("requestcontrollerlist")
    public String requestList(Model model){
        List<FX_CONVERSION_REQUEST> requests = requestService.getAllRequests();
        model.addAttribute("requests", requests);
        return "list-requests";
    }



    @GetMapping("hopstage")
    public String HopStageList(Model model){
        List<FX_CONVERSION_REQUEST> HopStageList = requestService.workflowStage(1);
        model.addAttribute("HopStageList", HopStageList);
        return "hopstagelist";
    }



}
