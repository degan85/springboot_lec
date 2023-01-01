package com.bbs.deganbbs.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bbs.deganbbs.domain.Post;
import com.bbs.deganbbs.dto.PostDto;
import com.bbs.deganbbs.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    private String indexView() {
        return "redirect:/postlist";
    }

    @GetMapping("/postlist")                 /* default page = 0, size = 10  */
    public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable ) {
        Page<Post> list = postService.pageList(pageable);

        model.addAttribute("postList", list);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());

        return "postlist";
    }

    @GetMapping("/postinsert")                 /* default page = 0, size = 10  */
    public String postInsert(Model model) {

        model.addAttribute("postDto", new PostDto());
        return "postinsert";
    }

    @PostMapping("/insert") 
    public String insertPost(@ModelAttribute("postDto") PostDto postDto) {
        postService.save(postDto);
        return "redirect:/postlist";
    }

    @GetMapping("/postlist/{id}")
    public String postDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "postdetail";
    }
    @PostMapping("/postedit")
    public String postUpdate(@ModelAttribute("post") PostDto postDto, Model model) {
        // System.out.println("test");
        model.addAttribute("postDto", postService.findById(postDto.getId()));
        return "postinsert";
    }

    @PostMapping("/update") 
    public String updatePost(@ModelAttribute("postDto") PostDto postDto) {
        postService.update(postDto);
        return "redirect:/postlist";
    }

    @PostMapping("/delete") 
    public String deletePost(@ModelAttribute("postDto") PostDto postDto) {
        postService.delete(postDto.getId());
        return "redirect:/postlist";
    }


    
}
