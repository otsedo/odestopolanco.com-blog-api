package com.odestopolanco.odestopolancoblog.dao.services;

import com.odestopolanco.odestopolancoblog.dao.PostRepository;
import com.odestopolanco.odestopolancoblog.domain.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public Post findPostById(long postId) {
        return postRepository.findById(postId);
    }

    public void deletePostById(long postId) {
        postRepository.deleteById(postId);
    }

    public boolean existsPost(long postId) {
        return postRepository.existsById(postId);
    }


}
