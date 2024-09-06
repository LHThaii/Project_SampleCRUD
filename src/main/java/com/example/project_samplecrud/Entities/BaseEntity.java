package com.example.project_samplecrud.Entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
@Getter
@Setter
@MappedSuperclass
public class BaseEntity<TKey> extends AuditedEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private TKey id;

}