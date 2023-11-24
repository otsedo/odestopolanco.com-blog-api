package com.odestopolanco.odestopolancoblog.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostsResponse {
    private int postCount;
    private List<Post> postList;
}
