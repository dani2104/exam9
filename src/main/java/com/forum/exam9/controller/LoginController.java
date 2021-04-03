package com.forum.exam9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "fancy-login";
    }

    @PostMapping("exer/{exer}")
    public String show(@PathVariable String exer){
        var a=exer;
        return a;
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";

    }

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

}









