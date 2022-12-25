package com.degan.jpatest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.degan.jpatest.domain.Comment;
import com.degan.jpatest.repository.CommentRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CommentRepositoryTest {
    
    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() {
        Comment comment = new Comment();
        comment.setComment("spring data jpa");
        commentRepository.save(comment);

        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("Spring");
        assertThat(comments.size()).isEqualTo(1);

    }
}
