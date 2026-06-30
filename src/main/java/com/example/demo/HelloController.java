package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Hello from Spring Boot running on Kubernetes!";
    }
    @GetMapping("/demo1")
    public String demo(){
        return "demo1";
    }
}
