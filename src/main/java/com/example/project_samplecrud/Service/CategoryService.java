package com.example.project_samplecrud.Service;

import com.example.project_samplecrud.Dto.Request.CategoryRequestDTO;
import com.example.project_samplecrud.Dto.Respone.CategoryResponseDTO;
import com.example.project_samplecrud.Dto.Respone.ResponseObjectDTO;
import com.example.project_samplecrud.Entities.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryResponseDTO> findAll();

    Category Search(String cname, UUID categoryId);

    ResponseEntity<CategoryResponseDTO> insertCategory(CategoryRequestDTO categoryRequestDTO);

    ResponseEntity<CategoryResponseDTO> updateCategory(CategoryRequestDTO categoryRequestDTO);

    ResponseEntity<ResponseObjectDTO> deleteCategory(UUID categoryId);


}
