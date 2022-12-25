package com.degan.jpatest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.degan.jpatest.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

    List<Comment> findByCommentContainsIgnoreCase(String keyword);
    
}
