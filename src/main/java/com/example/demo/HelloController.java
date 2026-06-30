package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@RestContoller
public class HelloController{
    @GetMapping("/hello")
    public String hello(){
        return "hello from java backend";
    }
    @GetMapping("/home")
    public String home(){
        return "java spring-boot application";
    }

}