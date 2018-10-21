package com.dxb.ssoclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class HelloController {
    @GetMapping("/")
    public String hello(HttpSession session) {
        return session.getId();
    }
}
