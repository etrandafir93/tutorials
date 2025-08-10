package com.baeldung.apache.seata.statistics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    @GetMapping("/api/stats")
    public String getStats() {
        return "Statistics are not available yet.";
    }
}
