package com.xjjlearning.hack.springboot.thymeleafdemo;

import com.sun.tracing.dtrace.ModuleAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/test/{path}")
    @ResponseBody
    public Map testsell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("path") String path) {
        Map<String, Object> map = new HashMap<>();
        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }
    @GetMapping("/testmetrix/{path}")
    @ResponseBody
    public String testMetrix(@PathVariable("path") String path,
                             @RequestParam String param) {
        return path + "?" + "param=" + param;
    }
}
