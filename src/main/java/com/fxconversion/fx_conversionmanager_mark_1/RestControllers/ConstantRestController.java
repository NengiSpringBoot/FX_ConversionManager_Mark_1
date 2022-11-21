package com.fxconversion.fx_conversionmanager_mark_1.RestControllers;

import com.fxconversion.fx_conversionmanager_mark_1.Enums.EnumCurrency;
import com.fxconversion.fx_conversionmanager_mark_1.Enums.EnumWorkflow;
import com.fxconversion.fx_conversionmanager_mark_1.Exceptions.AppException;
import com.fxconversion.fx_conversionmanager_mark_1.Exceptions.ValueNotFoundException;
import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_CONSTANTS;
import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_REQUEST;
import com.fxconversion.fx_conversionmanager_mark_1.Services.ConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("constants")
public class ConstantRestController {

    @Autowired
    ConstantService constantService;

    @GetMapping("allconstants")
    public List<FX_CONVERSION_CONSTANTS> getALlConstants(){
        EnumCurrency rest=EnumCurrency.valueOf(2);
        System.out.println(rest);

        return constantService.getAllConstants();
    }


    @PostMapping("createconstant")
    public FX_CONVERSION_CONSTANTS createConstants(
            @RequestBody FX_CONVERSION_CONSTANTS conversionConstants) throws AppException {

        EnumCurrency CurrencyTo=EnumCurrency.valueOf(conversionConstants.getCurrencyTo().ordinal());
        EnumCurrency CurrencyFrom=EnumCurrency.valueOf(conversionConstants.getCurrencyFrom().ordinal());
        System.out.println("Successfully created exchange rate for " + conversionConstants.getCurrencyFrom()
                + " to " + conversionConstants.getCurrencyTo());
        return constantService.createConstant(conversionConstants);
    }




    @GetMapping("conversionoperation")
    public double conversionOperation(
            @RequestBody FX_CONVERSION_CONSTANTS conversionConstants,
            @RequestParam int transactionAmount) throws ValueNotFoundException {

        Integer operationConstantId = conversionConstants.getConstantId();
        FX_CONVERSION_CONSTANTS existingConstant = constantService.get(operationConstantId);
        double transactionResult = transactionAmount * existingConstant.getExchangeRate();
        return transactionResult;
    }


    @GetMapping("getbycurrency")
    public List<FX_CONVERSION_CONSTANTS> getByCurrency(@RequestParam int currencyTo,
                                                       @RequestParam int currencyFrom){
        return constantService.getByCurrency(currencyTo, currencyFrom);
    }

    @PutMapping("updateconstant")
    public String updateConstant(@RequestParam int constantId,
                                 @RequestParam int newExchangeRate)
            throws ValueNotFoundException{
        FX_CONVERSION_CONSTANTS existingConstant = constantService.get(constantId);
        existingConstant.setExchangeRate(newExchangeRate);
        constantService.updateConstant(existingConstant);
        return String.format("%s was UPDATED SUCCESSFULLY", existingConstant);
    }

    @GetMapping("findconstant")
    public FX_CONVERSION_CONSTANTS findConstant(@RequestParam int constantId) throws ValueNotFoundException {
        return constantService.get(constantId);
    }



    @DeleteMapping("deleteconstant")
    public void deleteConstantById  (@RequestParam int constantId)
            throws ValueNotFoundException {
        constantService.deleteConstantById(constantId);
    }

    @DeleteMapping("deleteforwardandreverseconstants")
    public void deleteForwardAndReverseConstants(@RequestParam int constantId)
            throws ValueNotFoundException {
        constantService.deleteForwardAndReverseConstant(constantId);
    }

    @DeleteMapping("deletemultiple")
    public String deleteMultipleConstants(@RequestParam("ids") List<Integer> ids) {
        System.out.println("deleting");
        constantService.deleteAllBYIds(ids);
        return String.join(",", ids.stream().map(value ->  Integer.toString(value)).collect(Collectors.toList()));
    }

    @GetMapping("getdeletedconstants")
    public List<FX_CONVERSION_CONSTANTS> deletedConstants(){
        return constantService.deletedConstants();
    }

}
