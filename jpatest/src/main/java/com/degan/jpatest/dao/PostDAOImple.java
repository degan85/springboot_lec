package com.degan.jpatest.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.degan.jpatest.domain.Post;
import com.degan.jpatest.repository.PostRepository;

@Component
public class PostDAOImple implements PostDAO {

    @Autowired
    private final PostRepository postRepository;

    public PostDAOImple(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post insertPost(Post post) {
        Post savePost = postRepository.save(post);
        return savePost;
    }

    @Override
    public Optional<Post> selectPost(Long id) {
        Optional<Post> selectedPost = postRepository.findById(id);
        return selectedPost;
    }

    @Override
    public Post updatePostTitle(Long id, String title) throws Exception {
        Optional<Post> selectedPost = postRepository.findById(id);

        Post updatedPost;
        if(selectedPost.isPresent()) {
            Post post = selectedPost.get();

            post.setTitle(title);

            updatedPost = postRepository.save(post);
        } else {
            throw new Exception();
        }
        return updatedPost;
    }
    
    @Override
    public void deletePost(Long id) throws Exception {
        Optional<Post> selectedPost = postRepository.findById(id);

        if(selectedPost.isPresent()) {
            Post post = selectedPost.get();
            postRepository.delete(post);
        } else {
            throw new Exception();
        }
    }
}
