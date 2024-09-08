package com.example.project_samplecrud.Dto.Respone;

import com.example.project_samplecrud.Entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostResponseDTO {
    private String status = "SUCCESS";
    private UUID userId;
    private UUID categoryId;
    private UUID postId;
    private String content;
    private String title;
    public PostResponseDTO(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.categoryId = post.getCategory().getCategoryId();
    }
}
