package com.bbs.deganbbs.dto;

import com.bbs.deganbbs.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostDto {
        /** 게시글의 등록과 수정을 처리할 요청(Request) 클래스 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long id;
        private String title;
        private String writer;
        private String content;
        private String createdDate, modifiedDate;

        /* Dto -> Entity */
        public Post toEntity() {
            Post post = Post.builder()
                    .id(id)
                    .title(title)
                    .content(content)
                    .build();

            return post;
        }
    }

    /**
     * 게시글 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */
    @Getter
    public static class Response {
        private final Long id;
        private final String title;
        private final String writer;
        private final String content;
        private final String createdDate, modifiedDate;
        private final Long userId;

        /* Entity -> Dto*/
        public Response(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.writer = post.getWriter();
            this.content = post.getContent();
            this.createdDate = post.getCreatedDate();
            this.modifiedDate = post.getModifiedDate();
            this.userId = post.getUser().getId();
        }
    }
}
