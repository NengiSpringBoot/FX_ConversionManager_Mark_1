package com.fxconversion.fx_conversionmanager_mark_1.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class NOSTRO {

    public NOSTRO() {
    }

    public NOSTRO(Integer SN, String correspondentBank, String typeOfAccountMaintained, String accountNo, String generalLedgerNo, String currency) {
        this.SN = SN;
        this.correspondentBank = correspondentBank;
        this.typeOfAccountMaintained = typeOfAccountMaintained;
        this.accountNo = accountNo;
        this.generalLedgerNo = generalLedgerNo;
        this.currency = currency;
    }

    @Id
    @Column(name = "SN")
    private Integer SN;

    @Column (name = "CORRESPONDENT_BANK")
    private String correspondentBank;

    @Column (name = "TYPE_OF_ACCOUNT_MAINTAINED")
    private String typeOfAccountMaintained;

    @Column (name = "ACCOUNT_NO")
    private String accountNo;

    @Column (name = "GENERAL_LEDGER_NO")
    private String generalLedgerNo;

    @Column (name = "CURRENCY")
    private String currency;
}
