package com.odestopolanco.odestopolancoblog.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;

/**
 * POJO to represent the author of an blog post
 */
@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique=true, name = "username_unique_constraint")
    private String userName;
    private String firstName;
    private String lastName;
    @Column(unique=true, name = "email_unique_constraint")
    private String email;
    private LocalDateTime lastLogin;
    private String profilePicture;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    boolean active;
}
