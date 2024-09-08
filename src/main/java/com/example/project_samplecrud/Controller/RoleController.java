package com.example.project_samplecrud.Controller;

import com.example.project_samplecrud.Dto.Request.RoleRequestDTO;
import com.example.project_samplecrud.Dto.Respone.ResponseObjectDTO;
import com.example.project_samplecrud.Dto.Respone.RoleResponseDTO;
import com.example.project_samplecrud.Dto.Respone.UserResponseDTO;
import com.example.project_samplecrud.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/role")
public class RoleController {
    private final RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
        List<RoleResponseDTO> roles = roleService.getAllRole();
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RoleResponseDTO>> getRolesByUserId(@PathVariable UUID userId) {
        List<RoleResponseDTO> roles = roleService.getRoleByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

    @GetMapping("/user")
    public ResponseEntity<ResponseObjectDTO> getRolesByUser(@RequestHeader("Authorization") String token) {
        return roleService.getRoleByUser(token);
    }

    @PostMapping("/insert")
    public ResponseEntity<RoleResponseDTO> insertRole(@RequestBody RoleRequestDTO roleRequest) {
        return roleService.insertRole(roleRequest);
    }

    @PostMapping("/user/assign")
    public ResponseEntity<UserResponseDTO> assignRoleToUser(@RequestBody RoleRequestDTO roleRequest) {
        return roleService.insertRoleToUser(roleRequest);
    }

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<ResponseObjectDTO> deleteRole(@PathVariable UUID roleId) {
        return roleService.deleteRole(roleId);
    }

    @DeleteMapping("/user/remove")
    public ResponseEntity<UserResponseDTO> removeRoleFromUser(@RequestBody RoleRequestDTO roleRequest) {
        return roleService.deleteRoleOfUser(roleRequest);
    }
}
