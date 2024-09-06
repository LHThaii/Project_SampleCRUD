package com.example.project_samplecrud.Service.Imp;

import com.example.project_samplecrud.Dto.Request.CategoryRequestDTO;
import com.example.project_samplecrud.Dto.Respone.CategoryResponeDTO;
import com.example.project_samplecrud.Dto.Respone.ResponeObjectDTO;
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

    public List<CategoryResponeDTO> findAll() {
        List<CategoryResponeDTO> categories = categoryRepository.getAllCategory();
        if (categories.isEmpty()) {
            throw new NotFoundException("Don't have any category existed");
        }
        return categories;
    }

    @Override
    public Category Search(String cname, UUID categoryId) {
        // Tìm kiếm category dựa trên categoryId và cname (nếu có)
        Optional<Category> category = categoryRepository.findByCategoryIdAndName(categoryId, cname);

        // Kiểm tra nếu category có tồn tại
        if (category.isEmpty()) {
            throw new NotFoundException("Not found category");
        }
        return category.get();
    }

    @Override
    public ResponseEntity<CategoryResponeDTO> insertCategory(CategoryRequestDTO categoryRequestDTO) {
        // Tìm kiếm Category không phân biệt chữ hoa chữ thường
        Category categoryExist = categoryRepository.findByNameIgnoreCase(categoryRequestDTO.getName());

        // Nếu danh mục đã tồn tại, trả về phản hồi HTTP 409 Conflict
        if (categoryExist != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new CategoryResponeDTO("Category already exists with the same name"));
        }

        // Nếu danh mục chưa tồn tại, tạo mới Category từ DTO
        Category newCategory = new Category();
        newCategory.setName(categoryRequestDTO.getName());
        // Thêm các thuộc tính khác nếu cần

        // Lưu Category vào cơ sở dữ liệu
        Category savedCategory = categoryRepository.save(newCategory);

        // Tạo đối tượng phản hồi DTO để trả về
        CategoryResponeDTO responseDTO = new CategoryResponeDTO(savedCategory.getCategoryId(), savedCategory.getName());

        // Trả về phản hồi HTTP 201 Created với thông tin danh mục vừa thêm
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


    public ResponseEntity<CategoryResponeDTO> updateCategory(CategoryRequestDTO categoryRequestDTO) {
        // Tìm kiếm danh mục theo ID
        Optional<Category> optionalCategory = categoryRepository.findById(categoryRequestDTO.getCategoryId());

        // Nếu danh mục không tồn tại, trả về phản hồi HTTP 404 Not Found
        if (optionalCategory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CategoryResponeDTO("Category not found with ID: " + categoryRequestDTO.getCategoryId()));
        }

        // Lấy danh mục từ Optional
        Category category = optionalCategory.get();

        // Cập nhật thông tin danh mục từ DTO
        category.setName(categoryRequestDTO.getName());
        // Cập nhật các thuộc tính khác nếu cần

        // Lưu danh mục đã cập nhật vào cơ sở dữ liệu
        Category updatedCategory = categoryRepository.save(category);

        // Tạo đối tượng phản hồi DTO để trả về
        CategoryResponeDTO responseDTO = new CategoryResponeDTO(updatedCategory.getCategoryId(), updatedCategory.getName());

        // Trả về phản hồi HTTP 200 OK với thông tin danh mục đã cập nhật
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<ResponeObjectDTO> deleteCategory(UUID categoryId) {
        // Tìm kiếm danh mục theo ID
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        // Nếu danh mục không tồn tại, trả về phản hồi HTTP 404 Not Found
        if (optionalCategory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponeObjectDTO("Category not found with ID: " + categoryId));
        }

        // Xóa danh mục khỏi cơ sở dữ liệu
        categoryRepository.deleteById(categoryId);

        // Trả về phản hồi HTTP 200 OK với thông báo thành công
        return ResponseEntity.ok(new ResponeObjectDTO("Category successfully deleted"));
    }
}
