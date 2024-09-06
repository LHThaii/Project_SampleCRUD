package com.example.project_samplecrud.Entities;

import com.example.project_samplecrud.Dto.Request.CategoryRequestDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "category")
public class Category extends AuditedEntityBase{
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID categoryId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @JsonManagedReference
    private List<Post> posts;

    // Constructor to map CategoryRequestDTO to Category
    public Category(CategoryRequestDTO categoryRequest) {
        this.name = categoryRequest.getName(); // Assuming getname() should map to name
    }
}