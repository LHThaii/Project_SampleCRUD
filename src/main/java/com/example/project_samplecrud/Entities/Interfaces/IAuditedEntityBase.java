package com.example.project_samplecrud.Entities.Interfaces;

import java.time.LocalDateTime;

public interface IAuditedEntityBase {
    String getCreatedBy();
    void setCreatedBy(String createdBy);

    LocalDateTime getCreatedDate();
    void setCreatedDate(LocalDateTime createdDate);

    String getLastModifiedBy();
    void setLastModifiedBy(String lastModifiedBy);

    LocalDateTime getLastModifiedDate();
    void setLastModifiedDate(LocalDateTime lastModifiedDate);
}
