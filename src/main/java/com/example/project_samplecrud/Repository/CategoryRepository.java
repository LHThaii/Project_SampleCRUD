package com.example.project_samplecrud.Repository;

import com.example.project_samplecrud.Dto.Respone.CategoryResponseDTO;
import com.example.project_samplecrud.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("select c from Category c ")
    public List<CategoryResponseDTO> getAllCategory();

    Optional<Category> findByCategoryIdAndName(UUID categoryId, String name);

    @Query("SELECT c FROM Category c WHERE LOWER(c.name) = LOWER(:name)")
    Category findByNameIgnoreCase(@Param("name") String name);

}
