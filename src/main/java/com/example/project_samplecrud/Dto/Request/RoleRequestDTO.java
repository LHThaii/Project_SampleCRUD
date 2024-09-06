package com.example.project_samplecrud.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDTO {
    private UUID userId;
    private String roleName;
}
