package com.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@ResponseBody  // 这个类的所有方法返回的数据直接写给浏览器,如果返回的是对象则转为 json 的数据
@Controller
//@RestController // 就是 controller 和 responsebody 的组合注解
public class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!!";
    }


}
