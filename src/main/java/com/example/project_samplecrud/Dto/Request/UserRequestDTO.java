package com.example.project_samplecrud.Dto.Request;

import com.example.project_samplecrud.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private UUID userId;
    private String username;
    private String password;
    private Boolean enable;
    public UserRequestDTO(User users) {
        this.userId = users.getUserId();
        this.username = users.getUsername();
        this.password = users.getPassword();
        this.enable = users.getEnable();
    }
}

