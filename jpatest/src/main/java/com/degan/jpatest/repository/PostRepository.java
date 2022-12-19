package com.degan.jpatest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.degan.jpatest.domain.Post;

// Repository 어노테이션 필요 없음
// @EnableJpaRepositories 참조
public interface PostRepository extends JpaRepository<Post, Long>{
    
    Page<Post> findByTitleContains(String titile, Pageable pageable);
}
