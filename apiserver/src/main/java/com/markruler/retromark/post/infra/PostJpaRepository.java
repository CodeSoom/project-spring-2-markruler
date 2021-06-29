package com.markruler.retromark.post.infra;

import com.markruler.retromark.post.domain.Post;
import com.markruler.retromark.post.domain.PostRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJpaRepository extends PostRepository, JpaRepository<Post, Long> {
}
