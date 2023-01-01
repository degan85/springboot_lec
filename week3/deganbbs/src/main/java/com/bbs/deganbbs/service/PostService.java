package com.bbs.deganbbs.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbs.deganbbs.domain.Account;
import com.bbs.deganbbs.domain.Post;
import com.bbs.deganbbs.dto.PostDto;
import com.bbs.deganbbs.repository.AccountRepository;
import com.bbs.deganbbs.repository.PostRepository;

import lombok.RequiredArgsConstructor;

// 생성자 주입
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final AccountRepository accountRepository;


    @Transactional
    public Long save(PostDto dto) {

        Account user = getUser();
        dto.setUser(user);

        Post post = dto.toEntity();
        postRepository.save(post);

        return post.getId();
    }

    @Transactional(readOnly = true)
    private Account getUser() {
        return accountRepository.findById(1l).get();
    }

    @Transactional(readOnly = true)
    public Page<Post> pageList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public PostDto findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));
        return new PostDto().toDto(post);

    }

    @Transactional
    public void update(PostDto dto) {
        Post post = postRepository.findById(dto.getId()).orElseThrow(() -> 
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + dto.getId()));

        post.update(dto.getTitle(), dto.getContent());
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public Page<Post> search(String keyword, Pageable pageable) {
        Page<Post> postsList = postRepository.findByTitleContaining(keyword, pageable);
        return postsList;
    }

    
}
