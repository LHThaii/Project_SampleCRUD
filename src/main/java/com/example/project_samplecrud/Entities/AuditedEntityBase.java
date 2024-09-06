package com.example.project_samplecrud.Entities;
import com.example.project_samplecrud.Entities.Interfaces.IAuditedEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@MappedSuperclass
public abstract class AuditedEntityBase implements IAuditedEntityBase {
    @Column(length = 100)
    private String createdBy;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(length = 100)
    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;
}

