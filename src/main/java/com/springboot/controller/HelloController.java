package com.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Component
public class HelloController {
    @Value("${person.last-name}")
    private String name;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!!";
    }

    @ResponseBody
    @RequestMapping("/sayHello")
    public String sayHello() {
        return "Hello World " + name;
    }
}
