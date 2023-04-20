package com.xjjlearning.hack.springboot.thymeleafdemo;

import com.sun.tracing.dtrace.ModuleAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
/**
 * Created by xjj on 2023/4/18.
 */
@Controller
@RequestMapping("/thymeleafdemo")
public class DemoController {
    final String prefix = "thymeleafdemo";

    @RequestMapping("/")
    public String index() {
        return prefix + "/index";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(prefix + "/user-data");

        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
