package com.fxconversion.fx_conversionmanager_mark_1.Controllers;

import com.fxconversion.fx_conversionmanager_mark_1.Exceptions.ValueNotFoundException;
import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_CONSTANTS;
import com.fxconversion.fx_conversionmanager_mark_1.Services.ConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("constantapi")
public class ConstantController {

    @Autowired
    ConstantService constantService;

    @GetMapping("exchangerate")
    public String exchangeRate(Model model){
        List<FX_CONVERSION_CONSTANTS> exchangeRates = constantService.getAllConstants();
        model.addAttribute("constants", exchangeRates);
        return "exchangeratetable";
    }

    @GetMapping("/conversionoperation")
    public String conversionForm(Model model){
        List<FX_CONVERSION_CONSTANTS> exchangeRates = constantService.getAllConstants();
        FX_CONVERSION_CONSTANTS aConstants = new FX_CONVERSION_CONSTANTS();
        model.addAttribute("constants", aConstants);
        model.addAttribute("exchangeRates", exchangeRates);
        return "convertoperation";
    }

    @GetMapping("conversionselect/{id}")
    public String operation(@PathVariable Integer id, Model model)  {
        return "redirect:/constantapi/conversionoperation";
    }




}
