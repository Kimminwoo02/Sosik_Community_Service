package com.example.sosikcommunityservice.model.entity;

import com.example.sosikcommunityservice.dto.request.RequestCreateComment;
import com.example.sosikcommunityservice.dto.request.RequestUpdateComment;
import com.example.sosikcommunityservice.dto.response.ResponseCreateComment;
import com.example.sosikcommunityservice.dto.response.ResponseGetMember;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class CommentEntity extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private PostEntity community;

    @Column(nullable = false)
    private Long memberId;

    @Column(length = 1000, nullable = false)
    private String content;

    @Builder
    public CommentEntity(
            final PostEntity community,
            final Long memberId,
            final String content
    ) {
        this.community = community;
        this.memberId = memberId;
        this.content = content;
    }

    public static CommentEntity create(RequestCreateComment createComment, PostEntity postEntity, Long memberId){
        return CommentEntity.builder()
                .community(postEntity)
                .memberId(memberId)
                .content(createComment.content())
                .build();
    }

    public void updateComment(RequestUpdateComment updateComment) {
        this.content = updateComment.content();
    }
}
