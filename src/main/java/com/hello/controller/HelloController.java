package com.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

//@ResponseBody  // 这个类的所有方法返回的数据直接写给浏览器,如果返回的是对象则转为 json 的数据
@Controller
//@RestController // 就是 controller 和 responsebody 的组合注解
public class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!!";
    }

    @ResponseBody
    @RequestMapping("/hello.jsp")
    public String hello1() {
        return "hello.jsp!!";
    }

    @ResponseBody
    @RequestMapping("/session")
    public String initSession(HttpServletRequest request, HttpServletResponse response) {
        String s = request.getSession().getAttribute("userid") == null ? "null" : (String) request.getSession().getAttribute("userid");
        request.getSession().setAttribute("userid", "123");
        request.getSession().setMaxInactiveInterval(10);
        return "userid: " + s;
    }

    @ResponseBody
    @RequestMapping("/destroy")
    public String destroySession(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("userid", "123");
        request.getSession().setMaxInactiveInterval(10);
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
//        while (attributeNames.hasMoreElements()) {
//            request.getSession().removeAttribute(attributeNames.nextElement());
//        }
        request.getSession().invalidate();
        String s = request.getSession().getAttribute("userid") == null ? "null" : (String) request.getSession().getAttribute("userid");
        return "userid: " + s;
    }

    @ResponseBody
    @RequestMapping("/logout")
    public void sALogOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String redirectUri = "http://10.120.102.101:8107/api/v2/sa/auth/logout";
        response.sendRedirect(redirectUri);
    }

    @ResponseBody
    @RequestMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("", "");
        try {
            request.getRequestDispatcher("http://").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "userid: ";
    }


}
