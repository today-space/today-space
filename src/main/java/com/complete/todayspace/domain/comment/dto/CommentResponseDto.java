package com.complete.todayspace.domain.comment.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final String content;
    private final Long postId;
    private final Long userId;
    private final LocalDateTime createdAt;

    public CommentResponseDto(Long id, String content, Long postId, Long userId, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.userId = userId;
        this.createdAt = createdAt;
    }
}