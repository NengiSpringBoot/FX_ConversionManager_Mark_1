package com.fxconversion.fx_conversionmanager_mark_1.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class FX_CONVERSION_ATTACHMENT_FILES {

    @Id
    @Column(length = 30)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer attachmentFileId;

    @Column(length = 50)
    private String customerAttachmentTitle;

    @Column(name = "ZONAL_HEAD_ATTACHMENT",length = 50)
    private String zonalHeadAttachmentTitle;

    @Column(name = "CUSTOMER_ATTACHMENT_TYPE",length = 25)
    private String customerAttachmentFileType;

    @Column(name = "ZONAL_ATTACHMENT_TYPE",length = 50)
    private String zonalHeadAttachmentFileType;

    @Lob
    @Column(length= 1000)
    private byte[] zonalHeadApprovalAttachment;

    @Lob
    @Column(name = "CUSTOMER_INSTRUCTION", length= 1000)
    private byte[] customerInstructionAttachment;
    //Todo MAKE customerInstructionAttachment to be required


    @Override
    public String toString() {
        return  customerAttachmentTitle+  " and "  +  zonalHeadAttachmentTitle;
    }
}
