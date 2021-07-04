package com.markruler.retromark.post.controller;

import com.markruler.retromark.post.PostFixtures;
import com.markruler.retromark.post.application.PostService;
import com.markruler.retromark.post.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.markruler.retromark.RestDocUtils.getDocumentRequest;
import static com.markruler.retromark.RestDocUtils.getDocumentResponse;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@AutoConfigureRestDocs
@ActiveProfiles("test")
@DisplayName("PostController 클래스")
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Nested
    @DisplayName("GET /posts")
    class Describe_list_posts {

        @Nested
        @DisplayName("만약 회고록이 1개 있다면")
        class Context_with_one_post {
            final Post dailyRetrospective = PostFixtures.dailyRetrospective();

            @BeforeEach
            void mocking() {
                given(postService.listPosts())
                        .willReturn(List.of(dailyRetrospective));
            }

            @Test
            @DisplayName("1개의 회고록이 담긴 목록을 응답한다")
            void It_returns_list_with_a_post() throws Exception {
                var result = mockMvc.perform(
                        get("/posts"));

                result.andExpect(status().isOk())
                      .andExpect(jsonPath("$", hasSize(1)))
                      .andExpect(jsonPath("$[0].writer",
                                          containsString(dailyRetrospective.getWriter())))
                      .andExpect(jsonPath("$[0].content",
                                          containsString(dailyRetrospective.getContent())))
                      .andDo(document("posts-list",
                                      getDocumentRequest(),
                                      getDocumentResponse(),
                                      responseFields(
                                              fieldWithPath("[]").type(JsonFieldType.ARRAY)
                                                                 .description("회고 목록"),
                                              fieldWithPath("[].id").type(JsonFieldType.NUMBER)
                                                                    .description("식별자"),
                                              fieldWithPath("[].writer").type(JsonFieldType.STRING)
                                                                        .description("작성자"),
                                              fieldWithPath("[].title").type(JsonFieldType.STRING)
                                                                       .description("제목"),
                                              fieldWithPath("[].content").type(JsonFieldType.STRING)
                                                                         .description("내용"),
                                              fieldWithPath("[].createdAt").type(JsonFieldType.STRING)
                                                                           .ignored(),
                                              fieldWithPath("[].updatedAt").type(JsonFieldType.STRING)
                                                                           .ignored()
                                                    )));

                verify(postService).listPosts();
            }
        }

        @Nested
        @DisplayName("만약 회고록이 없다면")
        class Context_with_empty {

            @BeforeEach
            void mocking() {
                given(postService.listPosts())
                        .willReturn(new ArrayList<>());
            }

            @Test
            @DisplayName("빈 목록을 응답한다")
            void It_returns_empty_list() throws Exception {
                var result = mockMvc.perform(
                        get("/posts"));

                result.andExpect(status().isOk())
                      .andExpect(jsonPath("$", hasSize(0)));

                verify(postService).listPosts();
            }
        }
    }
}
