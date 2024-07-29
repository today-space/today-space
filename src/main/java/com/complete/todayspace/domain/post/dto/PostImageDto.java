package com.complete.todayspace.domain.post.dto;

import lombok.Getter;

@Getter
public class PostImageDto {

    private final Long id;
    private final String imagePath;
    private final Long imageOrder;

    public PostImageDto(Long id, Long imageOrder, String imagePath) {
        this.id = id;
        this.imageOrder = imageOrder;
        this.imagePath = imagePath;
    }
}
