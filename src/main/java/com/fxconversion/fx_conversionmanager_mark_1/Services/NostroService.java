package com.fxconversion.fx_conversionmanager_mark_1.Services;

import com.fxconversion.fx_conversionmanager_mark_1.Models.NOSTRO;
import com.fxconversion.fx_conversionmanager_mark_1.Repositories.NostroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NostroService {

    @Autowired
    NostroRepository nostroRepository;

    public List<NOSTRO> getAllNostros(){
        return nostroRepository.findAll();
    }

    public List<NOSTRO> getAllNostrosByCurrency(String currency){
        return nostroRepository.findByCurrency(currency);
    }
}
