package com.degan.jpatest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.degan.jpatest.domain.Post;
import com.degan.jpatest.repository.PostRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @Test
    public void getPost() throws Exception {
        Post post = new Post();
        post.setTitle("jpa");
        postRepository.save(post);


        mockMvc.perform(get("/posts/1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("jpa"));
    }

    @Test
    public void getUseConverterPost() throws Exception {
        Post post = new Post();
        post.setTitle("jpa2");
        postRepository.save(post);


        mockMvc.perform(get("/cposts/1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("jpa2"));
    }
    
    @Test
    public void getPosts() throws Exception {
        Post post = new Post();
        post.setTitle("jpa");
        postRepository.save(post);

        mockMvc.perform(get("/posts/")
                    .param("page", "0")
                    .param("size", "10")
                    .param("sort", "title"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("jpa"));
    }
}
