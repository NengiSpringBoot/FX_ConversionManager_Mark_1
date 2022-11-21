package com.fxconversion.fx_conversionmanager_mark_1.Services;

import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_ATTACHMENT_FILES;
import com.fxconversion.fx_conversionmanager_mark_1.Repositories.AttachmentRepository;

import com.fxconversion.fx_conversionmanager_mark_1.Utilities.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;
    public String uploadImage(MultipartFile file, MultipartFile attach) throws IOException {

        FX_CONVERSION_ATTACHMENT_FILES imageData = attachmentRepository.save(FX_CONVERSION_ATTACHMENT_FILES.builder()
                .customerAttachmentTitle(file.getOriginalFilename())
                .zonalHeadAttachmentTitle(attach.getOriginalFilename())
                .customerAttachmentFileType(file.getContentType())
                .zonalHeadAttachmentFileType(attach.getContentType())
                .customerInstructionAttachment(ImageUtils.compressImage(file.getBytes()))
                .zonalHeadApprovalAttachment(ImageUtils.compressImage(attach.getBytes()))
                .build());
        if (imageData != null) {
            return String.valueOf(imageData);
        }
        return null;
    }


    public byte[] downloadCustomerAttachment(int fileId){

        Optional<FX_CONVERSION_ATTACHMENT_FILES> dbImageData = attachmentRepository.findByAttachmentFileId(fileId);
        System.out.println(dbImageData);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getCustomerInstructionAttachment());
        return images;
    }
    public byte[] downloadZonalHeadApproval(int fileId){

        Optional<FX_CONVERSION_ATTACHMENT_FILES> dbImageData = attachmentRepository.findByAttachmentFileId(fileId);
        System.out.println(dbImageData);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getZonalHeadApprovalAttachment());
        return images;
    }
}
