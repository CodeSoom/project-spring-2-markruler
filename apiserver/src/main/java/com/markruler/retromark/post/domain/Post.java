package com.markruler.retromark.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * 회고
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "writer", columnDefinition = "VARCHAR(128)")
    private String writer;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
