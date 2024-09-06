package com.example.project_samplecrud.Dto.Respone;

import com.example.project_samplecrud.Entities.Category;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Getter
@Setter
public class CategoryResponeDTO {
    private String status = "SUCCESS";
    private UUID categoryId;
    private String name;
    private String message;

    public CategoryResponeDTO(Category category) {
        this.categoryId = category.getCategoryId();
        this.name = category.getName();
    }
    public CategoryResponeDTO(String message) {
        this.message = message;
    }
    public CategoryResponeDTO(UUID categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }
}
