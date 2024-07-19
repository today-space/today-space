package com.complete.todayspace.domain.hashtag.entity;

import com.complete.todayspace.domain.post.entitiy.Post;
import com.complete.todayspace.global.entity.CreatedTimestamp;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "table_hashtag")
@Getter
public class Hashtag extends CreatedTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_list_id", nullable = false)
    private HashtagList hashtagList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

}
