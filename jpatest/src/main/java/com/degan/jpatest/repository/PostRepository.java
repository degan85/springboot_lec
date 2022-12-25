package com.degan.jpatest.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.degan.jpatest.domain.Post;

// Repository 어노테이션 필요 없음
// @EnableJpaRepositories 참조
public interface PostRepository extends JpaRepository<Post, Long>{
    
    // page는 sort도 포함(getSort)
    Page<Post> findByTitleContains(String titile, PageRequest pageRequest);

    // sort만 따로 줄 수 있음
    // List<Post> findByTitleContains(String titile, Sort sort);

    @Query(value = "SELECT c FROM POST as p", nativeQuery = true)
    List<Post> selectPost(String keyword);
}
