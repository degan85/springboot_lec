package com.bbs.deganbbs.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbs.deganbbs.domain.Post;
import com.bbs.deganbbs.dto.PostDto;
import com.bbs.deganbbs.repository.PostRepository;

import lombok.RequiredArgsConstructor;

// 생성자 주입
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;


    @Transactional
    public Long save(PostDto.Request dto, String nickname) {
        /* User 정보를 가져와 dto에 담아준다. */
        Post post = dto.toEntity();
        postRepository.save(post);

        return post.getId();
    }

    @Transactional(readOnly = true)
    public Page<Post> pageList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }


    /* READ 게시글 리스트 조회 readOnly 속성으로 조회속도 개선 */
    @Transactional(readOnly = true)
    public PostDto.Response findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        return new PostDto.Response(post);
    }

    /* UPDATE (dirty checking 영속성 컨텍스트)
     *  User 객체를 영속화시키고, 영속화된 User 객체를 가져와 데이터를 변경하면
     * 트랜잭션이 끝날 때 자동으로 DB에 저장해준다. */
    @Transactional
    public void update(Long id, PostDto.Request dto) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        post.update(dto.getTitle(), dto.getContent());
    }

    /* DELETE */
    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        postRepository.delete(post);
    }


    /* search */
    @Transactional(readOnly = true)
    public Page<Post> search(String keyword, Pageable pageable) {
        Page<Post> postsList = postRepository.findByTitleContaining(keyword, pageable);
        return postsList;
    }

    
}
