package org.lagou.controller;

import org.lagou.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("test")
    public String testHello() {
        System.out.println("进入了test请求接口");
        return "hello";
    }
}
