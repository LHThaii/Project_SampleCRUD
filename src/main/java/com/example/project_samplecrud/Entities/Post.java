package com.example.project_samplecrud.Entities;

import com.example.project_samplecrud.Dto.Request.PostRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter
@Setter
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")

public class Post extends AuditedEntityBase {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID postId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @JsonBackReference
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "post_tag",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    @JsonBackReference
    private Set<Tag> tags;

    public Post(PostRequestDTO request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }
}
