package com.bbs.deganbbs.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import com.bbs.deganbbs.domain.Account;
import com.bbs.deganbbs.domain.Post;

@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    public void containTest() {
        Account user = new Account().builder()
                            .id(1l)
                            .lastname("cho")
                            .username("degan")
                            .email("degan@degan.com")
                            .build();

        Post post = new Post().builder()
                        .title("test title")
                        .content("test content")
                        .user(user)
                        .build();
        
        postRepository.save(post);

        List<Post> list = postRepository.findByTitleContaining("title", PageRequest.of(0, 10)).getContent();
        assertThat(list.size()).isEqualTo(1);

    }
    @Test
    public void selectTest() {
        List<Post> list = postRepository.findAll();
        assertThat(list).isNotNull();
        assertThat(list.size()).isZero();
    }
}
