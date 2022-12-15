package com.degan.degan2.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.degan.degan2.dto.SampleDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class BoardController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/hello")
    public String helloView(Model model) {

		logger.debug("logger");

        List<String> strList = Arrays.asList("a", "b","c");
        model.addAttribute("message", "message ok");
        model.addAttribute("url", "33");
        model.addAttribute("list", strList);
        model.addAttribute("sampleDTO", new SampleDTO());
        return "hello";
    }

    @GetMapping("/hello/{id}")
    public String helloView(@PathVariable("id") int id, Model model) {
        model.addAttribute("id", id);
        return "hellod";
    }

    @GetMapping("/link")
    public String linkView(Model model) {
        return "link";
    }

    @PostMapping("/testDTO")
    public String formTest(@ModelAttribute("sampleDTO") SampleDTO sampleDTO) {
        return "redirect:/";
    }
    
}
