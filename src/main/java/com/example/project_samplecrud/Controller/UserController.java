package com.example.project_samplecrud.Controller;

import com.example.project_samplecrud.Dto.Request.ChangePasswordRequestDTO;
import com.example.project_samplecrud.Dto.Request.UserRequestDTO;
import com.example.project_samplecrud.Dto.Respone.ResponseObjectDTO;
import com.example.project_samplecrud.Dto.Respone.TokenResponseDTO;
import com.example.project_samplecrud.Dto.Respone.UserResponseDTO;
import com.example.project_samplecrud.Entities.User;
import com.example.project_samplecrud.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.login(userRequestDTO);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<UserResponseDTO> findByUserName(@PathVariable String username) {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername(username);
        return userService.findByUserName(userRequestDTO);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable UUID id) {
        User user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new UserResponseDTO(user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/enabled")
    public ResponseEntity<List<UserResponseDTO>> getUserEnable() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserEnable());
    }

    @PostMapping("/change-password")
    public ResponseEntity<UserResponseDTO> changePassword(@RequestBody ChangePasswordRequestDTO changePasswordRequestDTO, @RequestHeader("Authorization") String token) {
        return userService.changePassword(changePasswordRequestDTO, token);
    }

    @PostMapping("/disable")
    public ResponseEntity<ResponseObjectDTO> disableUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.disableUser(userRequestDTO);
    }

    @PostMapping("/enable")
    public ResponseEntity<ResponseObjectDTO> enableUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.enableUser(userRequestDTO);
    }

    @PostMapping("/insert")
    public ResponseEntity<UserResponseDTO> insertUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.insertUser(userRequestDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.updateUser(userRequestDTO);
    }
}
