package com.example.project_samplecrud.Dto.Respone;

import com.example.project_samplecrud.Entities.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class RoleResponseDTO {
    private UUID roleId;
    private String roleName;

    public RoleResponseDTO(Role role){
        this.roleId = role.getRoleId();
        this.roleName = role.getRoleName();

    }
}
