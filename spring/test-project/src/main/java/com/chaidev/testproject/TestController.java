package com.chaidev.testproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private int counter;

    @GetMapping("/api/test1")
    public String testApi1() {
        counter++;
        return String.format("<h1> Response from Test API 1....%d</h1>", counter);
    }

    @GetMapping("/api/test2")
    public String testApi2() {
        counter++;
        return String.format("<h1> Response from Test API 2....%d</h1>", counter);
    }
}
