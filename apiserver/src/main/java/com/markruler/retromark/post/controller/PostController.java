package com.markruler.retromark.post.controller;

import com.markruler.retromark.post.application.PostService;
import com.markruler.retromark.post.domain.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 회고 API 요청을 처리합니다.
 */
@RequestMapping("/posts")
@RestController
@CrossOrigin
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * 회고 목록을 응답합니다.
     * 
     * @return 회고 목록
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Post> listPosts() {
        return postService.listPosts();
    }
}
