package com.bbs.deganbbs.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BbsController {

    @GetMapping("/")
    private String indexView() {
        return "redirect:/bbslist";
    }

    @GetMapping("/bbslist")
    private String viewList(Model model) {
        System.out.println("@@@ tset");
        List<String> strList = Arrays.asList("a", "b", "c");
        model.addAttribute("list", strList);
        return "bbslist";
    }
    
}
