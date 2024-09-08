package com.example.project_samplecrud.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordRequestDTO {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
