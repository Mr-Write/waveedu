package com.zhulang.waveedu.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 狐狸半面添
 * @create 2023-01-15 22:18
 */
@RestController
@RequestMapping("/login")
public class HelloController {
    @GetMapping("/test")
    public String test(@PathVariable("param")String name){
        return "逐浪教育：hello，"+name;
    }

    @GetMapping("/phone")
    public String hello(){
        return "hello world";
    }
}
