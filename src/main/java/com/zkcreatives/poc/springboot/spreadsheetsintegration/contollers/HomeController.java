package com.zkcreatives.poc.springboot.spreadsheetsintegration.contollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HomeController {

    @GetMapping
    String index() {
        return "Hello World!";
    }
}
