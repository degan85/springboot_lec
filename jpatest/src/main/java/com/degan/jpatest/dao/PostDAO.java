package com.degan.jpatest.dao;

import java.util.Optional;

import com.degan.jpatest.domain.Post;

public interface PostDAO {
    
    Post insertPost(Post post);

    Optional<Post> selectPost(Long id);

    Post updatePostTitle(Long id, String title) throws Exception;

    void deletePost(Long id) throws Exception;
}
