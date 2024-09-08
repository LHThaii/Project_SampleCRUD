package com.example.project_samplecrud.Controller;

import com.example.project_samplecrud.Dto.Request.CategoryRequestDTO;
import com.example.project_samplecrud.Dto.Respone.CategoryResponseDTO;
import com.example.project_samplecrud.Dto.Respone.ResponseObjectDTO;
import com.example.project_samplecrud.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/category")

public class CategoryController {
    private final CategoryService categoryService;

    // Constructor injection
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/view")
    ResponseEntity<ResponseObjectDTO> getAllCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObjectDTO("OK", "Category query success", categoryService.findAll()));
    }

    @PostMapping("/insert")

    ResponseEntity<CategoryResponseDTO> insertCategory(@RequestBody CategoryRequestDTO categoryRequest) {
        return categoryService.insertCategory(categoryRequest);
    }

    @PutMapping("/update")

    ResponseEntity<CategoryResponseDTO> updateCategory(@RequestBody CategoryRequestDTO categoryRequest){
        return categoryService.updateCategory(categoryRequest);
    }

    @DeleteMapping("/delete/{categoryId}")

    ResponseEntity<ResponseObjectDTO> deleteCategory(@PathVariable(name = "categoryId") UUID categoryId){
        return categoryService.deleteCategory(categoryId);
    }
}
