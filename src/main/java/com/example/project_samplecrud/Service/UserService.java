package com.example.project_samplecrud.Service;

import com.example.project_samplecrud.Dto.Request.ChangePasswordRequestDTO;
import com.example.project_samplecrud.Dto.Request.UserRequestDTO;
import com.example.project_samplecrud.Dto.Respone.ResponseObjectDTO;
import com.example.project_samplecrud.Dto.Respone.TokenResponseDTO;
import com.example.project_samplecrud.Dto.Respone.UserResponseDTO;
import com.example.project_samplecrud.Entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface UserService {
    ResponseEntity<TokenResponseDTO> login(UserRequestDTO userRequestDTO);

    ResponseEntity<UserResponseDTO> findByUserName(UserRequestDTO userRequestDTO);

    User findById(UUID id);

    User findByUsername(String username);

    List<UserResponseDTO> findAll();

    List<UserResponseDTO> getUserEnable();

    ResponseEntity<UserResponseDTO> changePassword(ChangePasswordRequestDTO changePasswordRequestDTO, String token);

    ResponseEntity<ResponseObjectDTO> disableUser(UserRequestDTO userRequestDTO);

    ResponseEntity<ResponseObjectDTO> enableUser(UserRequestDTO userRequestDTO);

    ResponseEntity<UserResponseDTO> insertUser(UserRequestDTO userRequestDTO);

    ResponseEntity<UserResponseDTO> updateUser(UserRequestDTO userRequestDTO);
}
