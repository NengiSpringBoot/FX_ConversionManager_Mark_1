package com.fxconversion.fx_conversionmanager_mark_1.Repositories;

import com.fxconversion.fx_conversionmanager_mark_1.Models.FX_CONVERSION_ATTACHMENT_FILES;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
//@Transactional
public interface AttachmentRepository extends JpaRepository<FX_CONVERSION_ATTACHMENT_FILES, Integer> {

    public Optional<FX_CONVERSION_ATTACHMENT_FILES> findByCustomerAttachmentTitle(String fileName);

    public Optional<FX_CONVERSION_ATTACHMENT_FILES> findByZonalHeadApprovalAttachment(String fileName);
    public Optional<FX_CONVERSION_ATTACHMENT_FILES> findByAttachmentFileId(int fileId);
}
