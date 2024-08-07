package com.complete.todayspace.domain.post.entitiy;

import com.complete.todayspace.global.entity.CreatedTimestamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_image_post")
@Getter
@NoArgsConstructor
public class ImagePost extends CreatedTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long orders;

    @Column
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public ImagePost(String fileUrl, Post savePost) {
        this.filePath = fileUrl;
        this.post = savePost;
    }
}
