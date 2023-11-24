package com.odestopolanco.odestopolancoblog.dao;

import com.odestopolanco.odestopolancoblog.domain.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    List<Post> findAll();

    Post save(Post post);

    Post findById(long id);

    void deleteById(long postId);

    boolean existsById(long postId);
}
