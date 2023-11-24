package com.odestopolanco.odestopolancoblog.controller.v1;


import com.odestopolanco.odestopolancoblog.dao.services.PostService;
import com.odestopolanco.odestopolancoblog.domain.Post;
import com.odestopolanco.odestopolancoblog.domain.PostsResponse;
import com.odestopolanco.odestopolancoblog.exceptions.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping(value = "v1/")
public class PostController {
    private final PostService postService;


    @GetMapping("/post/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable long postId) {
        Post post;
        try {
            post = postService.findPostById(postId);
            if (Objects.isNull(post)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
        return ResponseEntity.ok(post);
    }

    @PostMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> getAllPosts(@RequestBody Post newPost) {
        Post post;
        try {
            post = postService.savePost(newPost);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
        return ResponseEntity.ok(post);
    }

    @PutMapping(value = "/post/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> updatePost(@RequestBody Post oldPost, @PathVariable long postId) {

        Post updatedPost;
        try {
            var postToUpdate = postService.findPostById(postId);
            postToUpdate.setUser(oldPost.getUser());
            postToUpdate.setUpdatedAt(LocalDateTime.now());
            postToUpdate.setContent(oldPost.getContent());
            postToUpdate.setSlug(oldPost.getSlug());
            postToUpdate.setTitle(oldPost.getTitle());
            postToUpdate.setPublished(oldPost.isPublished());
            postToUpdate.setUser(oldPost.getUser());
            postToUpdate.setImageUrl(oldPost.getImageUrl());
            postToUpdate.setSummary(oldPost.getSummary());
            postToUpdate.setSubTitle(oldPost.getSubTitle());
            postToUpdate.setMetaTitle(oldPost.getMetaTitle());
            //TODO agregar modifiedBy
            if (postId == 0) {
                throw new ApiRequestException("You must specify the post ID");
            }
            postToUpdate.setId(postId);
            updatedPost = postService.savePost(postToUpdate);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<Void> deletePostById(@PathVariable long postId) {
        try {
            if (postService.existsPost(postId)) {
                postService.deletePostById(postId);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostsResponse> getAllPosts() {
        List<Post> posts;
        try {
            posts = postService.findAllPost();
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(PostsResponse.builder().postCount(posts.size()).postList(posts).build());
    }
}
