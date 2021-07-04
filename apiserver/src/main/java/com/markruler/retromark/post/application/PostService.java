package com.markruler.retromark.post.application;

import com.markruler.retromark.post.domain.Post;
import com.markruler.retromark.post.domain.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 회고 API의 비즈니스 로직을 담당합니다.
 */
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * 회고 목록을 리턴합니다.
     * 
     * @return 회고 목록
     */
    public List<Post> listPosts() {
        return postRepository.findAll();
    }
}
