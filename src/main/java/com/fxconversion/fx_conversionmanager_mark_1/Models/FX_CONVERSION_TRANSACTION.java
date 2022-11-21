package com.fxconversion.fx_conversionmanager_mark_1.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table
@EnableJpaAuditing
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE fx_conversion_request fcr SET fcr.deleted = 1 WHERE fcr.request_Id=?")
@Where(clause = "deleted=false")


//SOFT DELETE//
//I am using the @SQLDelete annotation to override the delete command. Every time I execute the delete command,
//I am actually have turned it into a SQL update command that changes the deleted field value to true instead
//of deleting the data permanently.

//The @Where annotation, on the other hand, will add a filter when we read the fx_conversion_request data.
//So, according to the code example above, fx_conversion_request data with the value deleted = true won't be included within the results.
public class FX_CONVERSION_TRANSACTION {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transactionId;

    @Column
    private String fx_account_number;

    @Column
    private String naira_account_number;

    @Column
    private int transaction_amount;


    @Column
    @CreationTimestamp
    private final LocalDateTime timeAndDateStamp = LocalDateTime.now();

    @Column
    private Date transactionDate;

    @Column
    private Date ValueDate;

    @Column
    private String transaction_purpose;

    @Column
    private boolean deleted = Boolean.FALSE;




}
