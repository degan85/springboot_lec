package com.degan.jpatest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.degan.jpatest.domain.Post;
import com.degan.jpatest.repository.PostRepository;

@RestController
public class PostController {
    
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id) {
        Optional<Post> byId = postRepository.findById(id);
        Post post = byId.get();
        return post.getTitle();
    }

    // DomainClassConverter
    // ToEntityConverter에서 findById 동작
    // id가 없으면 converted to null error 발생
    @GetMapping("/cposts/{id}")
    public String getUstConverterPost(@PathVariable("id") Post post) {
        return post.getTitle();
    }

    @GetMapping("/posts/")
    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}

