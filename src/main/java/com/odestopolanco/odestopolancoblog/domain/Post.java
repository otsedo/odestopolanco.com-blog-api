package com.odestopolanco.odestopolancoblog.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity

@EqualsAndHashCode
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String subTitle;
    private String metaTitle;
    @ManyToOne
    @JoinColumn(name = "user_id") // This is the foreign key column in the Post table
    private User user;
    private boolean isPublished;
    @CreatedDate
    private LocalDate createdAt;
    private LocalDate publishedAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String modifiedBy;
    private String slug;
    private String summary;
    @Column(length = 2000)
    private String imageUrl;
    private String content;
}
