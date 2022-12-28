package com.degan.jpatest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import com.degan.jpatest.domain.Post;
import com.degan.jpatest.repository.PostRepository;

// @SpringBootTest
@DataJpaTest
public class PostRepositoryTest {

    @PersistenceContext
    EntityManager entityManager;
    
    @Autowired
    PostRepository postRepository;

    @Test
    @Rollback(false)
    public void crudRepository() {
        Post post = new Post();
        post.setTitle("hello spring boot");
        assertThat(post.getId()).isNull();

        // When
        Post newPost = postRepository.save(post);

        // Then
        assertThat(newPost.getId()).isNotNull();

        // When
        List<Post> posts = postRepository.findAll();
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts.contains(newPost));

        // When
        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);

        // When
        postRepository.findByTitleContains("spring", PageRequest.of(0,10));
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);

    }

    @Test
    @Rollback(false)
    public void persistenceTest() {
        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = postRepository.save(post);

        Post postUpdate = new Post();
        postUpdate.setId(post.getId());
        postUpdate.setTitle("jpa2");
        Post updated = postRepository.save(postUpdate);

        postUpdate.setTitle("jpa3");
        updated.setTitle("hh");
        System.out.println("@@@"+postUpdate.toString());
    }

    @Test
    public void saveTest() {

        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = postRepository.save(post);

        assertThat(entityManager.contains(savedPost)).isTrue();
        assertThat(entityManager.contains(post)).isTrue();

        Post postUpdate = new Post();
        postUpdate.setId(post.getId());
        postUpdate.setTitle("jpa2");
        Post updatedPost = postRepository.save(postUpdate);

        assertThat(entityManager.contains(updatedPost)).isTrue();
        assertThat(entityManager.contains(postUpdate)).isFalse();

        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

}

