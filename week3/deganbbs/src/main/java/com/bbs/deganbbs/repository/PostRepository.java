package com.bbs.deganbbs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bbs.deganbbs.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

    Page<Post> findByTitleContaining(String keyword, Pageable pageable);
    
}
