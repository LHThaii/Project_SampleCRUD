package com.example.project_samplecrud.Service;

import com.example.project_samplecrud.Dto.Request.CategoryRequestDTO;
import com.example.project_samplecrud.Dto.Respone.CategoryResponeDTO;
import com.example.project_samplecrud.Dto.Respone.ResponeObjectDTO;
import com.example.project_samplecrud.Entities.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryResponeDTO> findAll();

    Category Search(String cname, UUID categoryId);

    ResponseEntity<CategoryResponeDTO> insertCategory(CategoryRequestDTO categoryRequestDTO);

    ResponseEntity<CategoryResponeDTO> updateCategory(CategoryRequestDTO categoryRequestDTO);

    ResponseEntity<ResponeObjectDTO> deleteCategory(UUID categoryId);


}
