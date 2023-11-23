package com.odestopolanco.odestopolancoblog.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Main entry point for the blog controller
 */
@RestController
@RequestMapping("/v1")
public class BlogController {

    /**
     * path to get all post in the blog
     * @return List of blogs posts
     */
    @GetMapping("/posts")
    public String getAllPosts() {
        return "First Blog";
    }
}
