package com.example.project_samplecrud.Repository;

import com.example.project_samplecrud.Dto.Respone.UserResponseDTO;
import com.example.project_samplecrud.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("select u from User u where u.username = :username")
    User findByUsername(String username);

    User deleteUsersByUserId(UUID userId);

    @Query("select u from User u")
    List<UserResponseDTO> findAllUser();

    @Query("select u from User u where u.userId=?1")
    User findByUserId(UUID userId);

    @Query("select u from User u where u.enable = true")
    List<UserResponseDTO> getUserEnable();
}
