package com.fxconversion.fx_conversionmanager_mark_1.Repositories;

import com.fxconversion.fx_conversionmanager_mark_1.Models.NOSTRO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NostroRepository extends JpaRepository<NOSTRO, Integer> {

    public List<NOSTRO> findByCurrency(String AccountNo);
}
