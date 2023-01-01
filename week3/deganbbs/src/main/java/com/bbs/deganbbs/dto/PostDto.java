package com.bbs.deganbbs.dto;

import com.bbs.deganbbs.domain.Account;
import com.bbs.deganbbs.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private Account user;
    private String createdDate, modifiedDate;


    public Post toEntity() {
        Post post = Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .user(user)
                .build();

        return post;
    }
    /* Entity -> Dto*/
    public PostDto toDto(Post post) {
        PostDto dto = PostDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .createdDate(post.getCreatedDate())
                        .modifiedDate(post.getModifiedDate())
                        .writer(post.getUser().getEmail())
                        .build();

        return dto;
    }
}
