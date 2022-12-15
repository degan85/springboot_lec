package com.degan.degan2.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.degan.degan2.domain.TestTable;
import com.degan.degan2.dto.SampleDTO;
import com.degan.degan2.service.TestaaService;

@RestController
@RequestMapping("/api")
public class BoardRestController {
   
    @Autowired
    TestaaService tts;

    @GetMapping("/hello")
    public String resstHello() {
        return "rest hello";
    }

    @PostMapping("/axiostest")
    public List<Integer> axiosTest(@RequestBody HashMap<String,String> param) {

        param.entrySet()
            .forEach(i->System.out.println("key : "+i.getKey()+", value : "+i.getValue()));

        // HashMap<String, Object> map = new HashMap<>();

        List<Integer> intList = Arrays.asList(1,3,4);
        // map.put("intList", intList);
        // map.put("intList2", intList);
        // List<Integer> intList =null;

        return intList;
    }

    @PostMapping("/dtotest")
    public void dtoTest(@RequestBody SampleDTO sampleDTO) {
        System.out.println(sampleDTO.toString());
    }

    @PostMapping("/saveTest")
    public void dtoTest(@RequestBody TestTable tt) {
        tts.save(tt);
    }

    @GetMapping("/getTest")
    public List<TestTable> getTest() {
        return tts.fildAll();
    }
}
