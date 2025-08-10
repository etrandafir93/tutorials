package com.baeldung.apache.seata.statistics.controller;

import com.baeldung.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("seata")
class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenGetStats_thenReturnsWelcomeMessage() throws Exception {
        mockMvc.perform(get("/api/stats"))
            .andExpect(status().isOk())
            .andExpect(content().string("Statistics are not available yet."));
    }
}
