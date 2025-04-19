package com.chengfu.springBootSample.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;




@RestController
@RequestMapping ("/api")
@Tag (name = "這是個簡單的Sample")
public class ThisSampleController {
    
    @GetMapping ("/hello")
    public String Hello () {
        return "HelloWorld";
    }
}
