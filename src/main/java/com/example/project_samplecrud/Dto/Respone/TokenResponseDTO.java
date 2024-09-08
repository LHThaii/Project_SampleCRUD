package com.example.project_samplecrud.Dto.Respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDTO {
    private String token;
    private String type;
}
