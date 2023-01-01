package com.bbs.deganbbs.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bbs.deganbbs.domain.Post;
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

        model.addAttribute("posts", list);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());

        return "postlist";
    }
    
}
