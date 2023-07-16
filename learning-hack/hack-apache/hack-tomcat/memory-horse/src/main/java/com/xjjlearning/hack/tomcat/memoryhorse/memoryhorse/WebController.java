package com.xjjlearning.hack.tomcat.memoryhorse.memoryhorse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xjj on 2023/6/20.
 */
@Controller
public class WebController {
    @RequestMapping("/")
    @ResponseBody
    void f() {}

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
    @RequestMapping("/servlet-api")
    public String servletApi() {
        return "servlet-api";
    }
}
