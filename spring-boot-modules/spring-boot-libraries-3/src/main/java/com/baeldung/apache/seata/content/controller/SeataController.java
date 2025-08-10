package com.baeldung.apache.seata.content.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeataController {

    @GetMapping("/seata")
    public String getDummyString() {
        return "Welcome to Apache Seata!";
    }
}
