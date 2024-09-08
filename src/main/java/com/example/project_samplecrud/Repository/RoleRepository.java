package com.example.project_samplecrud.Repository;

import com.example.project_samplecrud.Dto.Respone.RoleResponseDTO;
import com.example.project_samplecrud.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    @Query("""
            select r from Role r where r.roleName = :roleName
            """)
    Role findByRoleName(String roleName);

    @Query("select r from Role r")
    List<RoleResponseDTO> findAllRole();

    @Query("select r from Role r where r.roleName = 'ROLE_USER'")
    Role getRoleDefault();

    @Query("""
            select r from Role r
            inner join r.users u
            WHERE u.userId = :userId
            """)
    List<RoleResponseDTO> getRoleByUserId(UUID userId);
}
