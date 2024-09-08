package com.example.project_samplecrud.Dto.Respone;

import com.example.project_samplecrud.Entities.Role;
import com.example.project_samplecrud.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponseDTO {
    private String status="SUCCESS";
    private UUID userId;
    private String username;
    private Boolean enable;
    private Set<Role> role;

    public UserResponseDTO(User users) {
        this.userId = users.getUserId();
        this.username = users.getUsername();
        this.enable = users.getEnable();
        this.role = users.getRole();
    }
}
