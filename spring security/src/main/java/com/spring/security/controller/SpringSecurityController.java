package com.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityController {

    @GetMapping("/hello")
public String get(){
    return "hello";
}

@GetMapping("/bye")
public String bye(){
        return "Bye from Boot";
}
}
