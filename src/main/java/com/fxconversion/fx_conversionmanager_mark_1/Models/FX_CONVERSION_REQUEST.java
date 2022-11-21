package com.fxconversion.fx_conversionmanager_mark_1.Models;

import com.fxconversion.fx_conversionmanager_mark_1.Enums.EnumWorkflow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

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
public class FX_CONVERSION_REQUEST {


    @Id
    @Column(name = "requestId", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer requestId;

    ///BASIC FIELDS///
    @Column( length = 70)
    private String requestTitle;


    @Column(name = "customerName", length = 50)
    private String requestCustomerName;

    @Column (name = "customerPhoneNumber")
    private String customerPhoneNumber;

    @Column (name = "workflowTrack")
    private EnumWorkflow requestWorkflow;


    ///RSM///
    @Column (name = "requestCreationDate", nullable = false)
    @CreationTimestamp
    @CreatedDate
    private final LocalDateTime createdDateRSM = LocalDateTime.now();

    @Column
    private boolean RSMSubmitted;

    @Column (name = "RSMName")
    private String createdByRSM;

    @Column (name = "Branch")
    private String residentBranch;


    ///HOP(Head Of Operations///
    @Column (name = "HOPName")
    private String approvalHOPName;


    @Column (name = "HOPApprovalDate")
    private LocalDateTime HOPApprovalDate;


    ///Treasury////
    @Column (name = "TreasuryStaffName")
    private String approvalTreasuryOfficerName;

    @Column (name = "TreasuryApprovalDate")
    private LocalDateTime treasuryApprovalDate;


    ///TROPS///
    @Column (name = "TROPSOfficerName")
    private String approvalTROPSOfficerName;

    @Column
    private boolean deleted = Boolean.FALSE;

    @Column (name = "TROPSApprovalDate")
    private LocalDateTime TROPSApprovalDate;


    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "requestTransactionId")
    private List<FX_CONVERSION_TRANSACTION> requestTransaction;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "attachmentFileId")
    private FX_CONVERSION_ATTACHMENT_FILES attachmentFile;

    @Override
    public String toString() {
        return requestTitle;
    }



}
