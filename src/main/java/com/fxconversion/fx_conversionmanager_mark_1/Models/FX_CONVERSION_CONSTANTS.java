package com.fxconversion.fx_conversionmanager_mark_1.Models;

import com.fxconversion.fx_conversionmanager_mark_1.Enums.EnumCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table
@EnableJpaAuditing
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE fx_conversion_constants fcc SET fcc.deleted = 1 WHERE fcc.constant_Id=?")
@Where(clause = "deleted=false")

public class FX_CONVERSION_CONSTANTS {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer constantId;

    @Column
    private double exchangeRate;

    @Column
    private int chargeRate;

    @Column
    private EnumCurrency currencyTo;

    @Column
    private EnumCurrency currencyFrom;

    @Column
    private LocalDateTime effectiveDate;

    @Column
    private boolean deleted;

    public FX_CONVERSION_CONSTANTS(double exchangeRate,
                                   EnumCurrency currencyTo,
                                   EnumCurrency currencyFrom,
                                   LocalDateTime effectiveDate) {
        this.exchangeRate = exchangeRate;
        this.currencyTo = currencyTo;
        this.currencyFrom = currencyFrom;
        this.effectiveDate = effectiveDate;
    }

    @Override
    public String toString() {
        return   currencyTo + " to " +
                currencyFrom ;
    }
}
