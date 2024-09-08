package com.example.project_samplecrud.Service;

import com.example.project_samplecrud.Dto.Request.RoleRequestDTO;
import com.example.project_samplecrud.Dto.Respone.ResponseObjectDTO;
import com.example.project_samplecrud.Dto.Respone.RoleResponseDTO;
import com.example.project_samplecrud.Dto.Respone.UserResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    List<RoleResponseDTO> getAllRole();

    List<RoleResponseDTO> getRoleByUserId(UUID userId);

    ResponseEntity<ResponseObjectDTO> getRoleByUser(String token);

    ResponseEntity<RoleResponseDTO> insertRole(RoleRequestDTO roleRequest);

    ResponseEntity<UserResponseDTO> insertRoleToUser(RoleRequestDTO roleRequest);

    ResponseEntity<ResponseObjectDTO> deleteRole(UUID roleId);

    ResponseEntity<UserResponseDTO> deleteRoleOfUser(RoleRequestDTO roleRequest);
}
