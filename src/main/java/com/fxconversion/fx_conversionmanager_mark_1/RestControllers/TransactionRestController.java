package com.fxconversion.fx_conversionmanager_mark_1.RestControllers;

import com.fxconversion.fx_conversionmanager_mark_1.Exceptions.ValueNotFoundException;
import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_TRANSACTION;
import com.fxconversion.fx_conversionmanager_mark_1.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionRestController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("createtransaction")
    public FX_CONVERSION_TRANSACTION createTransaction(FX_CONVERSION_TRANSACTION transaction){
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("getalltransactions")
    public List<FX_CONVERSION_TRANSACTION> transactionList (){

        return transactionService.getAllTransactions();
    }

    @GetMapping("findbyid")
    public FX_CONVERSION_TRANSACTION findById(@RequestParam int transactionId) throws ValueNotFoundException {
       return transactionService.get(transactionId);
    }



    @DeleteMapping("delete")
    public void deleteById(@RequestParam int transactionId) throws ValueNotFoundException {
        transactionService.deleteTransactionById(transactionId);
    }

}
