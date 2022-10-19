package com.wj.web.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @GetMapping("/")
    public String toLoginHtml() {
        return "index";
    }

    @GetMapping("/main")
    public String toIndex2Html() {
        return "main";
    }

    @GetMapping("/list")
    public String toList(){
        return "list";
    }

    @GetMapping("/change")
    public String toChange(){
        return "change";
    }

    @GetMapping("/tutorial")
    public String totutorial(){
        return "tutorial";
    }

}
