package com.example.project_samplecrud.Service.Imp;

import com.example.project_samplecrud.Dto.Request.ChangePasswordRequestDTO;
import com.example.project_samplecrud.Dto.Request.UserRequestDTO;
import com.example.project_samplecrud.Dto.Respone.ResponseObjectDTO;
import com.example.project_samplecrud.Dto.Respone.TokenResponseDTO;
import com.example.project_samplecrud.Dto.Respone.UserResponseDTO;
import com.example.project_samplecrud.Entities.Role;
import com.example.project_samplecrud.Entities.User;
import com.example.project_samplecrud.Exception.NotFoundException;
import com.example.project_samplecrud.Repository.RoleRepository;
import com.example.project_samplecrud.Repository.UserRepository;
import com.example.project_samplecrud.Security.Convert;
import com.example.project_samplecrud.Security.CustomUserDetailsService;
import com.example.project_samplecrud.Security.JwtUtils;
import com.example.project_samplecrud.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseEntity<TokenResponseDTO> login(UserRequestDTO userRequestDTO) {

        User user = userRepository.findByUsername(userRequestDTO.getUsername());

        if (user != null && user.getPassword().equals(userRequestDTO.getPassword())) {

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userRequestDTO.getUsername());
            String token = jwtUtils.generateToken(userDetails);


            TokenResponseDTO tokenResponse = new TokenResponseDTO(token, "Bearer");


            return ResponseEntity.ok(tokenResponse);
        } else {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @Override
    public ResponseEntity<UserResponseDTO> findByUserName(UserRequestDTO userRequestDTO) {
        User user = userRepository.findByUsername(userRequestDTO.getUsername());

        if (user != null) {
            UserResponseDTO userResponseDTO = new UserResponseDTO(user);
            return ResponseEntity.ok(userResponseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAllUser();
    }

    @Override
    public List<UserResponseDTO> getUserEnable() {
        return userRepository.getUserEnable();
    }
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public ResponseEntity<UserResponseDTO> changePassword(ChangePasswordRequestDTO changePasswordRequestDTO, String token) {
        token = Convert.bearerTokenToToken(token);
        User userToken = findByUsername(jwtUtils.extractUsername(token));
        if (userToken == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (changePasswordRequestDTO.getOldPassword() == null
                || changePasswordRequestDTO.getNewPassword() == null
                || changePasswordRequestDTO.getConfirmPassword() == null
                || changePasswordRequestDTO.getOldPassword().isEmpty()
                || changePasswordRequestDTO.getNewPassword().isEmpty()
                || changePasswordRequestDTO.getConfirmPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        userToken.setPassword(changePasswordRequestDTO.getNewPassword());
        userRepository.save(userToken);
        UserResponseDTO userDto = new UserResponseDTO(userToken);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    public class UserAlreadyEnabledException extends RuntimeException {
        public UserAlreadyEnabledException(String message) {
            super(message);
        }
    }
    @Override
    public ResponseEntity<ResponseObjectDTO> disableUser(UserRequestDTO userRequestDTO) {
        User user = userRepository.findByUserId(userRequestDTO.getUserId());

        if (user == null) {
            throw new NotFoundException("User not found!");
        }

        if (!user.getEnable()) {
            throw new IllegalStateException("User is already disabled!");
        }

        user.setEnable(false);
        User updatedUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObjectDTO("OK", "User disabled successfully", updatedUser));
    }

    @Override
    public ResponseEntity<ResponseObjectDTO> enableUser(UserRequestDTO userRequestDTO) {
        User user = userRepository.findByUserId(userRequestDTO.getUserId());

        if (user == null) {
            throw new NotFoundException("User not found!");
        }

        if (user.getEnable()) {
            throw new UserAlreadyEnabledException("User is already enabled!");
        }

        user.setEnable(true);
        User updatedUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObjectDTO("OK", "User enabled successfully", updatedUser));
    }

    @Override
    public ResponseEntity<UserResponseDTO> insertUser(UserRequestDTO userRequestDTO) {
        String username = userRequestDTO.getUsername().trim();
        User foundUser = userRepository.findByUsername(username);
        if (foundUser != null) {
            throw new NotFoundException("Username already exists!");
        }
        Role foundRole = roleRepository.getRoleDefault();
        if (foundRole == null) {
            throw new NotFoundException("Default role not found");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(userRequestDTO.getPassword());
        user.setEnable(userRequestDTO.getEnable());

        // Assign the default role to the user
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(foundRole);
        user.setRole(roleSet); // Assuming the User class uses "roles" instead of "role"

        // Save the user and return the result
        User result = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(new UserResponseDTO(result));
    }

    @Override
    public ResponseEntity<UserResponseDTO> updateUser(UserRequestDTO userRequestDTO) {
        return null;
    }
}
