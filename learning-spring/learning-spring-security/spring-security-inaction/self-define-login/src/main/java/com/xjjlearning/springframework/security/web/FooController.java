package com.xjjlearning.springframework.security.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/foo")
@Slf4j
public class FooController {


    /**
     * Test string.
     *
     * @return the string
     */
    @GetMapping("/test")
    public String test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("current authentication: 【 {} 】", authentication);
        return "success";
    }


    /**
     * 基于 SecurityExpressionOperations 接口的表达式
     * 注释的相关注解都是可用的.
     *
     * @return the string
     */
    @GetMapping("/test2")
    @PreAuthorize("hasAnyRole('ADMIN')")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @PreAuthorize("isAnonymous()")
    public String test2() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("current authentication: 【 {} 】", authentication);
        return "success";
    }

    /**
     * 基于 `UserDetails` 的表达式，此表达式用以对当前用户的一些额外的限定操作.
     *
     * @return the string
     */
    @GetMapping("/bar")
//    @PreAuthorize("principal.username.startsWith('elordcn')")
    @PreAuthorize("principal.username.startsWith('xjj') or hasAnyRole('ADMIN')")
    public String self() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("current authentication: 【 {} 】", authentication);
        return "bar";
    }

    /**
     * Param string.
     *
     * @param id the id
     * @return the string
     */
    @GetMapping("/param/{id}")
    @PreAuthorize("#id.equals(principal.username)")
    public String param(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("current authentication: 【 {} 】", authentication);

        return id;
    }

//
//     /**
//      * Pre filter collection.
//      * 如果用户有角色AD则全部返回 否则 只返回以x开头的用户
//      * @param ids the ids
//      * @return the collection
//      */
//     @PostMapping("/prefilter")
// //    @PreFilter(value = "filterObject.startsWith('F')",filterTarget = "ids")
//     @PreFilter(value = "hasRole('AD') or filterObject.startsWith('x')", filterTarget = "ids")
//     public Collection<String> preFilter(@RequestBody Collection<String> ids){
//         return ids;
//     }


    /**
     * Postfilter collection.
     *
     * @return the collection
     */
    @GetMapping("/postfilter")
//    @PreFilter(value = "filterObject.startsWith('F')",filterTarget = "ids")
    @PostFilter("hasRole('AD') or filterObject.startsWith('x')")
    public Collection<String> postfilter(){
        List<String> list = new ArrayList<>();
        list.add("Felordcn");
        list.add("felord");
        list.add("jetty");
        list.add("xjj");
        return list;
    }


    /**
     * 测试 securedEnabled.
     *
     * @return the string
     */
    @GetMapping("/secure")
    @Secured({"ROLE_ADMIN1","ROLE_APP2","ROLE_POP"})
    public String  secure(){
        return "success";
    }

}