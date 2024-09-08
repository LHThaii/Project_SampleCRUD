package com.example.project_samplecrud.Service.Imp;

import com.example.project_samplecrud.Dto.Request.RoleRequestDTO;
import com.example.project_samplecrud.Dto.Respone.CategoryResponseDTO;
import com.example.project_samplecrud.Dto.Respone.ResponseObjectDTO;
import com.example.project_samplecrud.Dto.Respone.RoleResponseDTO;
import com.example.project_samplecrud.Dto.Respone.UserResponseDTO;
import com.example.project_samplecrud.Entities.Role;
import com.example.project_samplecrud.Entities.User;
import com.example.project_samplecrud.Exception.NotFoundException;
import com.example.project_samplecrud.Repository.RoleRepository;
import com.example.project_samplecrud.Repository.UserRepository;
import com.example.project_samplecrud.Security.Convert;
import com.example.project_samplecrud.Security.JwtUtils;
import com.example.project_samplecrud.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service

public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;
    @Override

    public List<RoleResponseDTO> getAllRole() {
        List<RoleResponseDTO> roles = roleRepository.findAllRole();
        if (roles.isEmpty()) {
            throw new NotFoundException("Don't have any role existed");
        }
        return roles;
    }

    @Override
    public List<RoleResponseDTO> getRoleByUserId(UUID userId) {
        return roleRepository.getRoleByUserId(userId);
    }

    @Override
    public ResponseEntity<ResponseObjectDTO> getRoleByUser(String token) {
            token = Convert.bearerTokenToToken(token);
            User user = userRepository.findByUsername(jwtUtils.extractUsername(token));
            UUID userId = user.getUserId();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObjectDTO("OK", "Role query success", getRoleByUserId(userId)));
    }

    @Override
    public ResponseEntity<RoleResponseDTO> insertRole(RoleRequestDTO roleRequest) {
        Role role = new Role(roleRequest);
        Role roleExisted = roleRepository.findByRoleName(roleRequest.getRoleName());
        if (roleExisted != null && !roleExisted.getRoleId().equals(role.getRoleId())) {
            throw new NotFoundException("RoleName have existed");
        }
        role.setRoleName(roleRequest.getRoleName());
        Role result = roleRepository.save(role);
        return ResponseEntity.status(HttpStatus.OK).body(new RoleResponseDTO(result));
    }

    @Override
    public ResponseEntity<UserResponseDTO> insertRoleToUser(RoleRequestDTO roleRequest) {
        User user = userRepository.findByUserId(roleRequest.getUserId());
        Role role = roleRepository.getById(roleRequest.getRoleId());
        user.getRole().add(role);
        User result = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(new UserResponseDTO((result)));
    }

    @Override
    public ResponseEntity<ResponseObjectDTO> deleteRole(UUID roleId) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (roleOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObjectDTO("Error", "Role not found", null));
        }
        roleRepository.deleteById(roleId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObjectDTO("OK", "Role deleted successfully", null));
    }


    @Override
    public ResponseEntity<UserResponseDTO> deleteRoleOfUser(RoleRequestDTO roleRequest) {
        // Find user by userId
        User user = userRepository.findByUserId(roleRequest.getUserId());
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        Set<Role> roles = user.getRole();
        // Check if roles are assigned
        if (roles == null || roles.isEmpty()) {
            throw new NotFoundException("User has no roles assigned");
        }
        Role roleToRemove = roles.stream()
                .filter(role -> role.getRoleId().equals(roleRequest.getRoleId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Role not found for the user"));
        roles.remove(roleToRemove);
        user.setRole(roles);
        User updatedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(new UserResponseDTO(updatedUser));
    }
}
