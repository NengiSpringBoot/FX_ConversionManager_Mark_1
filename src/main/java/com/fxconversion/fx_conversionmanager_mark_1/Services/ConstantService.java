package com.fxconversion.fx_conversionmanager_mark_1.Services;

import com.fxconversion.fx_conversionmanager_mark_1.Enums.EnumCurrency;
import com.fxconversion.fx_conversionmanager_mark_1.Exceptions.AppException;
import com.fxconversion.fx_conversionmanager_mark_1.Exceptions.ValueNotFoundException;
import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_CONSTANTS;
import com.fxconversion.fx_conversionmanager_mark_1.Repositories.ConstantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ConstantService {

    @Autowired
    ConstantRepository constantRepository;


    public List<FX_CONVERSION_CONSTANTS> getAllConstants(){
        return constantRepository.findAll();
    }

    public FX_CONVERSION_CONSTANTS createConstant(FX_CONVERSION_CONSTANTS constants) throws AppException {
        int currencyTo= constants.getCurrencyTo().ordinal();
        int currencyFrom=constants.getCurrencyFrom().ordinal();


        //TODO Create an exception in order to prevent duplicate entries

        //CHECKING IF THE INVERSE CONVERSION EXISTS
        if(constantRepository.getConstantByCurrency(currencyTo, currencyFrom).isEmpty()){
            createInverseConstants(constants.getExchangeRate(),
                    constants.getCurrencyTo(), constants.getCurrencyFrom(), LocalDateTime.now());
        }

        //CHECKING IF THE CURRENT ENTRY EXISTS OR IF BOTH THE CURRENT AND THE INVERSE ENTRY EXISTS
        if(!constantRepository.getConstantByCurrency(currencyTo, currencyFrom).isEmpty() &&
                !constantRepository.getConstantByCurrency(currencyFrom, currencyTo).isEmpty()){

            throw new AppException("Exchange Rate entry already exists");
        }
        constants.setEffectiveDate(LocalDateTime.now());
        return constantRepository.save(constants);
    }


    public void createInverseConstants(double inverseExchangeRate,
                                       EnumCurrency inverseCurrencyTo,
                                       EnumCurrency inverseCurrencyFrom,
                                       LocalDateTime effectiveDate){

        System.out.println("Just created reverse conversion");
        double actualReverseExchangeRate = 1.0000/inverseExchangeRate;
        System.out.println(actualReverseExchangeRate);
        constantRepository.save(new FX_CONVERSION_CONSTANTS(actualReverseExchangeRate,
                inverseCurrencyFrom, inverseCurrencyTo, effectiveDate));

    }


    public List<FX_CONVERSION_CONSTANTS> getByCurrency(int currencyToCode, int currencyFromCode){
        return constantRepository.getConstantByCurrency(currencyToCode, currencyFromCode);
    }


    //Get a particular constant by id
    public FX_CONVERSION_CONSTANTS get(Integer id) throws ValueNotFoundException {

        try {
            return constantRepository.findById(id).get();
        } catch (NoSuchElementException exception) {
            throw new ValueNotFoundException("Could not find any constant with ID " + id);
        }
    }

    public void updateConstant(FX_CONVERSION_CONSTANTS constants){
        FX_CONVERSION_CONSTANTS reverseConstantUpdate = findReverseConstant(constants);
        reverseConstantUpdate.setExchangeRate(1/ constants.getExchangeRate());
        constantRepository.save(constants);
    }


    //Delete constant by id
    public void deleteConstantById(int id) throws ValueNotFoundException {
        Integer countById = constantRepository.countByConstantId(id);
        if (countById == null || countById == 0) {
            throw new ValueNotFoundException("Could not find any constant with ID " + id);
        }
        constantRepository.deleteById(id);

    }

    public void deleteForwardAndReverseConstant(Integer id) throws ValueNotFoundException {
        Integer countById = constantRepository.countByConstantId(id);
        if (countById == null || countById == 0) {
            throw new ValueNotFoundException("Could not find any constant with ID " + id);
        }

        FX_CONVERSION_CONSTANTS conversionConstants = get(id);
        FX_CONVERSION_CONSTANTS reverseConstantToBeDeleted = findReverseConstant(conversionConstants);

        Integer reverseId= reverseConstantToBeDeleted.getConstantId();
        System.out.println("The Reverse for of the Constant to be deleted is " + reverseConstantToBeDeleted);
        if(reverseConstantToBeDeleted.isDeleted()){

        }else{
        constantRepository.deleteById(reverseId);
        }
        constantRepository.deleteById(id);
    }

    public String deleteAllBYIds(List<Integer> integers) {
        constantRepository.deleteByIdIn(integers);
        return "Deleted Successfully";
    }


    public FX_CONVERSION_CONSTANTS findReverseConstant(FX_CONVERSION_CONSTANTS forwardConstant){
        int currencyTo= forwardConstant.getCurrencyTo().ordinal();
        int currencyFrom=forwardConstant.getCurrencyFrom().ordinal();
        List<FX_CONVERSION_CONSTANTS> searchConstant=
                constantRepository.getConstantByCurrency(currencyFrom, currencyTo);
        FX_CONVERSION_CONSTANTS reverseConstant = searchConstant.get(0);
        return reverseConstant;
    }


    public List<FX_CONVERSION_CONSTANTS> deletedConstants(){
        return constantRepository.deletedConstants();
    }
}
