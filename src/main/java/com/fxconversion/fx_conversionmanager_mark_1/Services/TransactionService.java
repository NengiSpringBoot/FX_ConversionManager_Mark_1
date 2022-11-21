package com.fxconversion.fx_conversionmanager_mark_1.Services;

import com.fxconversion.fx_conversionmanager_mark_1.Enums.EnumWorkflow;
import com.fxconversion.fx_conversionmanager_mark_1.Exceptions.ValueNotFoundException;

import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_TRANSACTION;
import com.fxconversion.fx_conversionmanager_mark_1.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public FX_CONVERSION_TRANSACTION createTransaction(FX_CONVERSION_TRANSACTION transaction){
        transactionRepository.save(transaction);
        return transaction;
    }


    public List<FX_CONVERSION_TRANSACTION> getAllTransactions(){
        return transactionRepository.findAll();
    }


    //Get a particular transaction by id
    public FX_CONVERSION_TRANSACTION get(Integer id) throws ValueNotFoundException {

        try {
            return transactionRepository.findById(id).get();
        } catch (NoSuchElementException exception) {
            throw new ValueNotFoundException("Could not find any transaction with ID " + id);
        }
    }

    public void updateTransaction(FX_CONVERSION_TRANSACTION transaction){
        transactionRepository.save(transaction);
    }

    //Delete transaction by id
    public void deleteTransactionById(Integer id) throws ValueNotFoundException {


        Integer countById = transactionRepository.countByTransactionId(id);
        if (countById == null || countById == 0) {
            throw new ValueNotFoundException("Could not find any transaction with ID " + id);
        }
        transactionRepository.deleteById(id);

    }

    public List<FX_CONVERSION_TRANSACTION> getDeletedTransactions(){
        return transactionRepository.getDeletedTransactions();
    }

    public void deleteAllBYIds(List<Integer> integers) {
        transactionRepository.deleteByIdIn(integers);
    }



}
