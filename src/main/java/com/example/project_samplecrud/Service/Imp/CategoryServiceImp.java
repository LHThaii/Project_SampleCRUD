package com.example.project_samplecrud.Service.Imp;

import com.example.project_samplecrud.Dto.Request.CategoryRequestDTO;
import com.example.project_samplecrud.Dto.Respone.CategoryResponseDTO;
import com.example.project_samplecrud.Dto.Respone.ResponseObjectDTO;
import com.example.project_samplecrud.Entities.Category;
import com.example.project_samplecrud.Exception.NotFoundException;
import com.example.project_samplecrud.Repository.CategoryRepository;
import com.example.project_samplecrud.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    // Constructor injection
    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponseDTO> findAll() {
        List<CategoryResponseDTO> categories = categoryRepository.getAllCategory();
        if (categories.isEmpty()) {
            throw new NotFoundException("Don't have any category existed");
        }
        return categories;
    }

    @Override
    public Category Search(String cname, UUID categoryId) {
        Optional<Category> category = categoryRepository.findByCategoryIdAndName(categoryId, cname);
        if (category.isEmpty()) {
            throw new NotFoundException("Not found category");
        }
        return category.get();
    }

    @Override
    public ResponseEntity<CategoryResponseDTO> insertCategory(CategoryRequestDTO categoryRequestDTO) {
        Category categoryExist = categoryRepository.findByNameIgnoreCase(categoryRequestDTO.getName());
        if (categoryExist != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new CategoryResponseDTO("Category already exists with the same name"));
        }
        Category newCategory = new Category();
        newCategory.setName(categoryRequestDTO.getName());
        Category savedCategory = categoryRepository.save(newCategory);
        CategoryResponseDTO responseDTO = new CategoryResponseDTO(savedCategory.getCategoryId(), savedCategory.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    public ResponseEntity<CategoryResponseDTO> updateCategory(CategoryRequestDTO categoryRequestDTO) {

        Optional<Category> optionalCategory = categoryRepository.findById(categoryRequestDTO.getCategoryId());


        if (optionalCategory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CategoryResponseDTO("Category not found with ID: " + categoryRequestDTO.getCategoryId()));
        }
        Category category = optionalCategory.get();
        category.setName(categoryRequestDTO.getName());
        Category updatedCategory = categoryRepository.save(category);
        CategoryResponseDTO responseDTO = new CategoryResponseDTO(updatedCategory.getCategoryId(), updatedCategory.getName());
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<ResponseObjectDTO> deleteCategory(UUID categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObjectDTO("Category not found with ID: " + categoryId));
        }
        categoryRepository.deleteById(categoryId);
        return ResponseEntity.ok(new ResponseObjectDTO("Category successfully deleted"));
    }
}
