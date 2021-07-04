package com.markruler.retromark.post.application;

import com.markruler.retromark.post.PostFixtures;
import com.markruler.retromark.post.domain.Post;
import com.markruler.retromark.post.domain.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ActiveProfiles("test")
@DisplayName("PostService 클래스")
class PostServiceTest {

    private final PostRepository postRepository = mock(PostRepository.class);
    private PostService postService;

    @BeforeEach
    void setUp() {
        postService = new PostService(postRepository);
    }

    @Nested
    @DisplayName("listPosts 메서드는")
    class Describe_listPosts {

        @BeforeEach
        void prepare_posts() {
            final Post dailyRetrospective = PostFixtures.dailyRetrospective();

            given(postRepository.findAll())
                    .willReturn(List.of(dailyRetrospective));
        }

        @Test
        @DisplayName("회고 목록을 리턴합니다")
        void It_returns_list_of_posts() {
            List<Post> posts = postService.listPosts();

            assertThat(posts).hasSize(1);
        }
    }

}
