package com.fxconversion.fx_conversionmanager_mark_1.RestControllers;

import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_ATTACHMENT_FILES;
import com.fxconversion.fx_conversionmanager_mark_1.Services.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentRestController {

    @Autowired
    public AttachmentService attachmentService;


    @PostMapping
    public ResponseEntity<?> uploadAttachment (
            @RequestParam("file")MultipartFile file,
            @RequestParam("attach")MultipartFile attach
    ) throws IOException {
        String uploadImage = attachmentService.uploadImage(file, attach);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(uploadImage);
    }


    @GetMapping("/customer")
    public ResponseEntity<?> downloadCustomerAttachment(@RequestParam int fileId){
        byte[] imageData= attachmentService.downloadCustomerAttachment(fileId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @GetMapping("/zonal")
    public ResponseEntity<?> downloadZonalAttachment(@RequestParam int fileId){
        byte[] imageData= attachmentService.downloadZonalHeadApproval(fileId);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
