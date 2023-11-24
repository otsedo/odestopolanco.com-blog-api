package com.odestopolanco.odestopolancoblog.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * Pojo to represented all the information about a blog POST
 */
@Data
@Entity
@EqualsAndHashCode
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String subTitle;
    private String metaTitle;
    @ManyToOne()
    private Users users;
    private String isPublished;
    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime publishedAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private String slug;
    private String summary;
    private String imageUrl;
    private String content;
}
