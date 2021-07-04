package com.markruler.retromark.post.domain;

import java.util.List;

public interface PostRepository {
    List<Post> findAll();
}
