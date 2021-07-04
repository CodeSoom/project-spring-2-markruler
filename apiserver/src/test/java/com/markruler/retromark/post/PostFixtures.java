package com.markruler.retromark.post;

import com.markruler.retromark.post.domain.Post;

public class PostFixtures {
    public static Post dailyRetrospective() {
        return Post.builder()
                   .id(1L)
                   .writer("markruler")
                   .title("회고 테스트")
                   .content("나는 개발 고수다")
                   .build();
    }
}
