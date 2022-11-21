package com.fxconversion.fx_conversionmanager_mark_1.RestControllers;

import com.fxconversion.fx_conversionmanager_mark_1.Models.NOSTRO;
import com.fxconversion.fx_conversionmanager_mark_1.Services.NostroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("nostrotest")
public class NostroController {

    @Autowired
    NostroService nostroService;

    @GetMapping("")
    public List<NOSTRO> getAllNostroses(){
       return nostroService.getAllNostros();
    }

    @GetMapping("thelist")
    public List<NOSTRO> getAllByCurrency(@RequestParam String currency){
        return nostroService.getAllNostrosByCurrency(currency);
    }
}
